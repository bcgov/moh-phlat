package com.moh.phlat.backend.esb.boundary;

import java.net.http.HttpClient;
import java.time.Duration;

import javax.net.ssl.SSLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ssl.NoSuchSslBundleException;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.moh.phlat.backend.esb.json.PlrRequest;
import com.moh.phlat.backend.esb.json.PlrResponse;
import com.moh.phlat.backend.model.Control;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker.State;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Component
public class PlrEsbBoundary {
	
	private static final Logger logger = LoggerFactory.getLogger(PlrEsbBoundary.class);
	private WebClient webClient;
	
	private SSLContext sslContext;
	
	@Autowired
	private CircuitBreakerRegistry circuitBreakerRegistry;
	
	@Autowired
	private PlrKeyCloak keyCloak;

	@Autowired
    private void initSslContext(SslBundles sslBundles) throws NoSuchSslBundleException {
		SslBundle sslBundle = sslBundles.getBundle("client");
        sslContext = sslBundle.createSslContext();
    }
	
	@Value("${plr.boundary.host}")
	private String plrBoundaryHost;
	
	@Value("${plr.boundary.timeout}")
	private int timeout;
	
	private static final String MAINTAIN_ENDPOINT = "/HSA-web/rs/passthrough/maintain";
	private static final String VALIDATE_ENDPOINT = "/HSA-web/rs/passthrough/validate";
	
	@PostConstruct
	public void initPlrEsbBoundary() {
		try {
			HttpClient httpClient = HttpClient.newBuilder()
					.sslContext(sslContext)
					.connectTimeout(Duration.ofSeconds(timeout))
					.build();
			
			webClient = WebClient.builder()
					.clientConnector(new JdkClientHttpConnector(httpClient))
					.build();
			
		} catch (Exception ex) {
			logger.error("Could not build PlrEsbBoundary Connection: ", ex);
		}
	}
	
	public boolean isReadyToConnect() {
		return webClient != null;
	}
	
	@CircuitBreaker(name="callPlrCircuitBreaker", fallbackMethod="fallback")
	@Retry(name="callPlrRetry")
	public void maintainProvider(Control control, PlrRequest plrRequest, PlrResponse plrResponse) {
		callPlr(control, plrRequest, plrResponse, MAINTAIN_ENDPOINT);
	}
	
	@CircuitBreaker(name="callPlrCircuitBreaker", fallbackMethod="fallback")
	@Retry(name="callPlrRetry")
	public void validateProvider(Control control, PlrRequest plrRequest, PlrResponse plrResponse) {
		callPlr(control, plrRequest, plrResponse, VALIDATE_ENDPOINT);
	}
	
	private void callPlr(Control control, PlrRequest plrRequest, PlrResponse plrResponse, String endpoint) {
		String jsonRequest = plrRequest.processDataToPlrJson();
		
		PlrToken token = keyCloak.getToken();
		Mono<String> mono = webClient.post()
				.uri(plrBoundaryHost + endpoint)
				.contentType(MediaType.APPLICATION_JSON)
				.headers(header -> {
					header.setBearerAuth(token.getAccessToken());
					header.add("userID", control.getUserId());
				})
				.bodyValue(jsonRequest)
				.retrieve()
				.bodyToMono(String.class)
				.doOnError(WebClientRequestException.class, ex -> {
                    logger.error("HTTP Request failed due to an error: {}", ex.getMessage());
                })
				.doOnError(WebClientResponseException.class, ex -> {
                    logger.error("HTTP Response Status: {}, Response Body: {}", ex.getStatusCode(), ex.getResponseBodyAsString());
                });
		
		String jsonResponse = mono.block();
		plrResponse.plrJsonToProcessData(jsonResponse);
	}
	
	private void fallback(Control control, PlrRequest plrRequest, PlrResponse plrResponse, Exception exception) {
		logger.error("Fallback triggered on call to {} due to: {}", plrBoundaryHost, exception.getMessage());
		if (exception instanceof CallNotPermittedException) {
			logger.error("CircuitBreaker has stopped this load run due to too many successive failures");
		}
		plrResponse.handlePlrError(exception);
	}
	
	public boolean hasPersistentIssue() {
		State state = circuitBreakerRegistry.circuitBreaker("callPlrCircuitBreaker").getState();
		return !(state.equals(State.CLOSED) || state.equals(State.HALF_OPEN));
	}
}
