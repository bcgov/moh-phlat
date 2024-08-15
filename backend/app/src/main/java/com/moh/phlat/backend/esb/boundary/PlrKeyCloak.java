package com.moh.phlat.backend.esb.boundary;

import java.net.http.HttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Component
public class PlrKeyCloak {
	
	private static final Logger logger = LoggerFactory.getLogger(PlrKeyCloak.class);
	private WebClient webClient;
	
	private PlrToken token;

	@Value("${plr.boundary.keycloak.provider-url}")
	private String keyCloakUrl;
	@Value("${plr.boundary.keycloak.client-id}")
	private String plrBoundaryClientId;
	@Value("${plr.boundary.keycloak.client-secret}")
	private String plrBoundaryClientSecret;
	
	@PostConstruct
	public void initPlrKeyCloak() {
		HttpClient httpClient = HttpClient.newBuilder()
				.build();
		
		webClient = WebClient.builder()
				.baseUrl(keyCloakUrl)
				.clientConnector(new JdkClientHttpConnector(httpClient))
				.build();
	}
	
	private void requestToken() {		
		try {
			String jsonResponse = webClient.post()
					.uri("/protocol/openid-connect/token")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.bodyValue("grant_type=client_credentials" +
								"&scope=openid" +
								"&client_id=" + plrBoundaryClientId +
								"&client_secret=" + plrBoundaryClientSecret)
					.retrieve()
					.onStatus(status -> status.value() != 200, error -> Mono.empty())
					.bodyToMono(String.class)
					.block();
			
			JsonNode root = new ObjectMapper().readTree(jsonResponse);
			token = new PlrToken();
			if (root.has("access_token")) {
				token.setAccessToken(root.get("access_token").asText());
			}
			if (root.has("expires_in")) {
				token.setExpiry(root.get("expires_in").asLong());
			}
			if (root.has("refresh_token")) {
				token.setRefreshToken(root.get("refresh_token").asText());
			}
			if (root.has("error")) {
				token.setError(root.get("error").asText());
			}
			if (root.has("error_description")) {
				token.setErrorDesc(root.get("error_description").asText());
			}
		} catch (Exception ex) {
			logger.error("Token request failed: ", ex);
			token = null;
		}
	}
	
	public PlrToken getToken() {
		if (token == null || token.isExpired()) {
			requestToken();
		}
		return token;
	}
	
}
