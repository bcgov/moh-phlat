package com.moh.phlat.backend.esb.boundary;

import java.net.http.HttpClient;

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

import com.moh.phlat.backend.esb.json.PlrRequest;
import com.moh.phlat.backend.esb.json.PlrResponse;
import com.moh.phlat.backend.model.Control;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Component
public class PlrEsbBoundary {
	
	private static final Logger logger = LoggerFactory.getLogger(PlrEsbBoundary.class);
	private WebClient webClient;
	
	private SSLContext sslContext;
	
	@Autowired
	private PlrKeyCloak keyCloak;

	@Autowired
    private void initSslContext(SslBundles sslBundles) throws NoSuchSslBundleException {
		SslBundle sslBundle = sslBundles.getBundle("client");
        sslContext = sslBundle.createSslContext();
    }
	
	@Value("${plr.boundary.host}")
	private String plrBoundaryHost;
	
	private static final String MAINTAIN_ENDPOINT = "/HSA-web/rs/passthrough/maintain";
	private static final String VALIDATE_ENDPOINT = "/HSA-web/rs/passthrough/validate";
	
	@PostConstruct
	public void initPlrEsbBoundary() {
		try {
			HttpClient httpClient = HttpClient.newBuilder()
					.sslContext(sslContext)
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
	
	public void maintainProvider(Control control, PlrRequest plrRequest, PlrResponse plrResponse) {
		callPlr(control, plrRequest, plrResponse, MAINTAIN_ENDPOINT);
	}
	
	public void validateProvider(Control control, PlrRequest plrRequest, PlrResponse plrResponse) {
		callPlr(control, plrRequest, plrResponse, VALIDATE_ENDPOINT);
	}
	
	private void callPlr(Control control, PlrRequest plrRequest, PlrResponse plrResponse, String endpoint) {
		String jsonRequest = plrRequest.processDataToPlrJson();
		
		try {
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
					.bodyToMono(String.class);
			String jsonResponse = mono.block();
			
			plrResponse.plrJsonToProcessData(jsonResponse);
			
		} catch (Exception ex) {
			logger.error("PLR request failed with error: ", ex);
		}
	}
}
