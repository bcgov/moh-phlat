package com.moh.phlat.backend.esb.boundary;

import java.net.http.HttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
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
		try {
			HttpClient httpClient = HttpClient.newBuilder()
					.build();
			
			webClient = WebClient.builder()
					.baseUrl(keyCloakUrl)
					.clientConnector(new JdkClientHttpConnector(httpClient))
					.build();
			
		} catch (Exception ex) {
			logger.error("Could not build PlrKeyCloak Connection: ", ex);
		}
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
			
			JsonParser jsonParser = new ObjectMapper().createParser(jsonResponse);
			token = new PlrToken();
			while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
				if (StringUtils.hasText(jsonParser.getCurrentName())) {
					switch (jsonParser.getCurrentName()) {
						case "access_token":
							token.setAccessToken(jsonParser.getValueAsString());
							break;
						case "expires_in":
							token.setExpiry(jsonParser.getValueAsLong());
							break;
						case "refresh_token":
							token.setRefreshToken(jsonParser.getValueAsString());
							break;
						case "error":
							token.setError(jsonParser.getValueAsString());
							break;
						case "error_description":
							token.setErrorDesc(jsonParser.getValueAsString());
							break;
						default:
							break;
					}
				}
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
