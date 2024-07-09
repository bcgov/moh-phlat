package com.moh.phlat.backend.esb.boundary;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moh.phlat.backend.esb.json.MaintainFacilityRequest;
import com.moh.phlat.backend.esb.json.MaintainFacilityResponse;
import com.moh.phlat.backend.esb.json.MaintainHdsRequest;
import com.moh.phlat.backend.esb.json.MaintainHdsResponse;
import com.moh.phlat.backend.esb.json.PlrResponse;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.model.ProcessData;
import jakarta.annotation.PostConstruct;
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
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLContext;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlrEsbBoundary {
	
	private static final Logger logger = LoggerFactory.getLogger(PlrEsbBoundary.class);
	private WebClient webClient;

	private PlrToken token;
	
	private SSLContext sslContext;

	@Autowired
    public void initSslContext(SslBundles sslBundles) throws NoSuchSslBundleException {
		SslBundle sslBundle = sslBundles.getBundle("client");
        sslContext = sslBundle.createSslContext();
    }
	
	@Value("${plr.boundary.host}")
	private String plrBoundaryHost;

	@Value("${plr.boundary.keycloak.provider-url}")
	private String keyCloakUrl;
	@Value("${plr.boundary.keycloak.client-id}")
	private String plrBoundaryClientId;
	@Value("${plr.boundary.keycloak.client-secret}")
	private String plrBoundaryClientSecret;
	
	private final String plrBoundaryEndpoint = "/HSA-web/rs/passthrough/maintain";
	
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
	
	public List<PlrResponse> loadPlrViaEsb(Control control, ProcessData processData) {
		List<PlrResponse> plrResponses = new ArrayList();
		token = getPlrKeyCloakDetails();
		if (StringUtils.hasText(token.getAccessToken()) && !StringUtils.hasText(token.getError())) {
			if (control.getLoadTypeFacility()) {
				MaintainFacilityResponse facilityResponse = createFacility(control, processData);
				plrResponses.add(facilityResponse);
			}
			if (control.getLoadTypeHds()) {
				//MaintainHdsResponse hdsResponse = createHdsProvider(control, processData);
				//plrResponses.add(hdsResponse);
			}
			if (control.getLoadTypeOFRelationship()) {
				//MaintainOFResponse ofResponse = createOFRelationship(control, processData);
				//plrResponses.add(ofResponse);
			}
		} 
		return plrResponses;
	}
	
	private MaintainFacilityResponse createFacility(Control control, ProcessData processData) {
		MaintainFacilityRequest maintainFacilityRequest = new MaintainFacilityRequest(control);
		String jsonRequest = maintainFacilityRequest.processDataToPlrJson(processData);
		
		MaintainFacilityResponse facilityResponse = new MaintainFacilityResponse(control);
		
		try {
			Mono<String> mono = webClient.post()
					.uri(plrBoundaryHost + plrBoundaryEndpoint)
					.contentType(MediaType.APPLICATION_JSON)
					.headers(h -> {
						h.setBearerAuth(token.getAccessToken());
						h.add("userID", control.getUserId());
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
		return facilityResponse;
	}
	
	private MaintainHdsResponse createHdsProvider(Control control, ProcessData processData) {
		MaintainHdsRequest maintainHdsRequest = new MaintainHdsRequest(control);
		String jsonRequest = maintainHdsRequest.processDataToPlrJson(processData);
		
		MaintainHdsResponse hdsResponse = new MaintainHdsResponse(control);
		
		try {
			Mono<String> mono = webClient.post()
					.uri(plrBoundaryHost + plrBoundaryEndpoint)
					.contentType(MediaType.APPLICATION_JSON)
					.headers(h -> {
						h.setBearerAuth(token.getAccessToken());
						h.add("userID", control.getUserId());
					})
					.bodyValue(jsonRequest)
					.retrieve()
					.bodyToMono(String.class);
			String jsonResponse = mono.block();
			
			hdsResponse.plrJsonToProcessData(jsonResponse, processData);
			
		} catch (Exception ex) {
			logger.error("Load Facility request failed: ", ex);
			hdsResponse.handlePlrError(ex);
		}
		return hdsResponse;
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
