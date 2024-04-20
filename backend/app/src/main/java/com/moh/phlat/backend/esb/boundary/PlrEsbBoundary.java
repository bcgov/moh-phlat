package com.moh.phlat.backend.esb.boundary;

import java.net.http.HttpClient;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ssl.NoSuchSslBundleException;
import org.springframework.boot.ssl.SslBundle;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moh.phlat.backend.esb.json.MaintainFacilityRequest;
import com.moh.phlat.backend.esb.json.MaintainFacilityResponse;

@Component
public class PlrEsbBoundary {
	
	private static final Logger logger = LoggerFactory.getLogger(PlrEsbBoundary.class);
	private WebClient webClient;
	
	private SSLContext sslContext;
	@Autowired
    public void initSslContext(SslBundles sslBundles) throws NoSuchSslBundleException {
        SslBundle sslBundle = sslBundles.getBundle("client");
        sslContext = sslBundle.createSslContext();
    }
	
	@Value("${plr.boundary.host}")
	private String plrBoundaryHost;
	@Value("${plr.boundary.org-id}")
	private String plrBoundaryOrgId;
	@Value("${plr.boundary.role}")
	private String plrBoundaryRole;
	
	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	private String keyCloakUrl;
	@Value("${plr.boundary.keycloak.client-id}")
	private String plrBoundaryClientId;
	@Value("${plr.boundary.keycloak.client-secret}")
	private String plrBoundaryClientSecret;
	
	@PostConstruct
	public void initPlrEsbBoundary() {
		try {
			HttpClient httpClient = HttpClient.newBuilder()
					// *TEMPORARY SSL DISABLER*
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
	
	public MaintainFacilityResponse loadPlrViaEsb(Control control, ProcessData processData) {
		
		MaintainFacilityRequest maintainFacilityRequest = new MaintainFacilityRequest(control);
		String jsonRequest = maintainFacilityRequest.processDataToPlrJson(processData);
		
		MaintainFacilityResponse facilityResponse = new MaintainFacilityResponse(control);
		
		PlrToken token = getPlrKeyCloakDetails();
		if (StringUtils.hasText(token.getAccessToken()) && !StringUtils.hasText(token.getError())) {
			
			try {
				Mono<String> mono = webClient.post()
						.uri(plrBoundaryHost + "/HSA-web/rs/passthrough/maintain")
						.contentType(MediaType.APPLICATION_JSON)
						.headers(h -> {
							h.setBearerAuth(token.getAccessToken());
							h.add("userID", control.getUserId());
							h.add("OrganizationId", plrBoundaryOrgId);
							h.add("PLR_Role", plrBoundaryRole);
						})
						.bodyValue(jsonRequest)
						.retrieve()
						.bodyToMono(String.class);
				String jsonResponse = mono.block();
				
				facilityResponse.plrJsonToProcessData(jsonResponse, processData);
				
			} catch (Exception ex) {
				logger.error("Load Facility request failed: ", ex);
				facilityResponse.handlePlrError(ex);
			}
			
		} else {
			facilityResponse.handleKeyCloakError(token);
		}
		return facilityResponse;
	}
	
	private PlrToken getPlrKeyCloakDetails() {
		WebClient webClient = WebClient.builder()
				.baseUrl(keyCloakUrl)
				.clientConnector(new JdkClientHttpConnector(HttpClient.newHttpClient()))
				.build();
		
		PlrToken token = new PlrToken();
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
			while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
				if (StringUtils.hasText(jsonParser.getCurrentName())) {
					switch (jsonParser.getCurrentName()) {
						case "access_token":
							token.setAccessToken(jsonParser.getValueAsString());
							break;
						case "expires_in":
							token.setExpiry(jsonParser.getValueAsInt());
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
			token.setError("Token request failed");
			token.setErrorDesc(ex.getMessage());
		}
		return token;
	}
}
