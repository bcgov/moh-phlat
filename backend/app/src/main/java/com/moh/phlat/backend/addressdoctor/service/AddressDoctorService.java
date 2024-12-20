package com.moh.phlat.backend.addressdoctor.service;

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

import com.moh.phlat.backend.addressdoctor.soap.SOAPEnvelopeInput;
import com.moh.phlat.backend.addressdoctor.soap.SOAPEnvelopeOutput;
import com.moh.phlat.backend.model.Control;

import io.github.resilience4j.circuitbreaker.CircuitBreaker.State;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBException;
import reactor.core.publisher.Mono;

@Component
public class AddressDoctorService {
	
	private static final Logger logger = LoggerFactory.getLogger(AddressDoctorService.class);
	private WebClient webClient;
	
	private SSLContext sslContext;
	
	@Autowired
	private AddressDoctorXmlTransformer xmlTransformer;
	
	@Autowired
	private CircuitBreakerRegistry circuitBreakerRegistry;

	@Autowired
    private void initSslContext(SslBundles sslBundles) throws NoSuchSslBundleException {
		SslBundle sslBundle = sslBundles.getBundle("addressdoctor");
        sslContext = sslBundle.createSslContext();
    }
	
	@Value("${addressdoctor.host}")
	private String addressDoctorHost;
	
	@Value("${addressdoctor.timeout}")
	private int timeout;
	
	@PostConstruct
	public void initAddressDoctorService() throws JAXBException {
		HttpClient httpClient = HttpClient.newBuilder()
				.sslContext(sslContext)
				.connectTimeout(Duration.ofSeconds(timeout))
				.build();
		
		webClient = WebClient.builder()
				.clientConnector(new JdkClientHttpConnector(httpClient))
				.build();
	}
	
	@CircuitBreaker(name="addressDoctorCircuitBreaker", fallbackMethod="fallback")
	@Retry(name="addressDoctorRetry")
	public SOAPEnvelopeOutput validateAddress(Control control, SOAPEnvelopeInput addressDoctorRequest) {
		return callAddressDoctor(control, addressDoctorRequest);
	}
	
	private SOAPEnvelopeOutput callAddressDoctor(Control control, SOAPEnvelopeInput addressDoctorRequest) {
		
		String xmlRequest = xmlTransformer.toXml(addressDoctorRequest);
		
		Mono<String> mono = webClient.post()
				.uri(addressDoctorHost)
				.contentType(MediaType.TEXT_XML)
				.bodyValue(xmlRequest)
				.retrieve()
				.bodyToMono(String.class)
				.doOnError(WebClientRequestException.class, ex -> {
                    logger.error("HTTP Request failed due to an error: ", ex);
                })
				.doOnError(WebClientResponseException.class, ex -> {
                    logger.error("HTTP Response Status: {}, Response Body: {}", ex.getStatusCode(), ex.getResponseBodyAsString());
                });
		
		String xmlResponse = mono.block();
		return xmlTransformer.fromXml(xmlResponse);
	}
	
	private SOAPEnvelopeOutput fallback(Control control, SOAPEnvelopeInput addressDoctorRequest, Exception exception) {
		logger.error("Fallback triggered on call to {} due to: ", exception, addressDoctorHost);
		return null;
	}
	
	public boolean hasPersistentIssue() {
		State state = circuitBreakerRegistry.circuitBreaker("addressDoctorCircuitBreaker").getState();
		return !(state.equals(State.CLOSED) || state.equals(State.HALF_OPEN));
	}
}
