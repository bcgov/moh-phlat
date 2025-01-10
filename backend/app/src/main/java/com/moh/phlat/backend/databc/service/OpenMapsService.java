package com.moh.phlat.backend.databc.service;

import java.net.http.HttpClient;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.moh.phlat.backend.databc.dto.CHSAResults;
import com.moh.phlat.backend.databc.util.Constants;

import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBException;
import reactor.core.publisher.Mono;

@Component
public class OpenMapsService {
    
    private static final Logger logger = LoggerFactory.getLogger(OpenMapsService.class.getSimpleName());
    private WebClient webClient;
    
    // URL Strings
    private static final String OPEN_MAPS_BASE_URI_1 = "https://openmaps.gov.bc.ca/geo/pub/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=pub:WHSE_ADMIN_BOUNDARIES.BCHA_CMNTY_HEALTH_SERV_AREA_SP&srsname=EPSG:4326&cql_filter=INTERSECTS(SHAPE,POINT(";
    private static final String OPEN_MAPS_BASE_URI_2 = "))&propertyName=CMNTY_HLTH_SERV_AREA_CODE,CMNTY_HLTH_SERV_AREA_NAME,LOCAL_HLTH_AREA_NAME,LOCAL_HLTH_AREA_CODE,HLTH_SERVICE_DLVR_AREA_NAME,HLTH_AUTHORITY_NAME&outputFormat=application/json";
	
	@Value("${openmaps.timeout}")
	private int timeout;
	
	@PostConstruct
	public void initOpenMapsService() throws JAXBException {
		HttpClient httpClient = HttpClient.newBuilder()
				.connectTimeout(Duration.ofSeconds(timeout))
				.build();
		
		webClient = WebClient.builder()
				.clientConnector(new JdkClientHttpConnector(httpClient))
				.build();
	}
    
	public CHSAResults callOpenMapsCHSA(String chsaLongitude, String chsaLatitude) {
		
		String openMapsUrl = buildOpenMapsURL(chsaLongitude, chsaLatitude);
		
		Mono<String> mono = webClient.get()
				.uri(openMapsUrl)
				.retrieve()
				.bodyToMono(String.class)
				.doOnError(WebClientRequestException.class, ex -> {
                    logger.error("HTTP Request failed due to an error: ", ex);
                })
				.doOnError(WebClientResponseException.class, ex -> {
                    logger.error("HTTP Response Status: {}, Response Body: {}", ex.getStatusCode(), ex.getResponseBodyAsString());
                })
				.onErrorComplete();
		
		String jsonResponse = mono.block();
		if (jsonResponse != null && jsonResponse.contains("<?xml")) {
			logger.error("CSHA Lookup returned the follwoing error: {}", jsonResponse);
			return null;
		}
		JsonObject root = new Gson().fromJson(jsonResponse, JsonObject.class);
		
		if (root.has("features") && !root.getAsJsonArray("features").isEmpty()
				&& root.getAsJsonArray("features").get(0).getAsJsonObject().has("properties")) {
			JsonObject properties = root.getAsJsonArray("features").get(0).getAsJsonObject().getAsJsonObject("properties");
			CHSAResults chsaResults = new CHSAResults();
			if (properties.has(Constants.CHSA_CODE)) {
				chsaResults.setChsaAreaCode(properties.get(Constants.CHSA_CODE).getAsString());
			}
			if (properties.has(Constants.CHSA_NAME)) {
				chsaResults.setChsaAreaName(properties.get(Constants.CHSA_NAME).getAsString());
			}
			if (properties.has(Constants.LHA_NAME)) {
				chsaResults.setLhaName(properties.get(Constants.LHA_NAME).getAsString());
			}
			if (properties.has(Constants.HSDA_NAME)) {
				chsaResults.setHsdaName(properties.get(Constants.HSDA_NAME).getAsString());
			}
			if (properties.has(Constants.HA_NAME)) {
				chsaResults.setHaName(properties.get(Constants.HA_NAME).getAsString());
			}
			return chsaResults;
		}
		return null;
	}
    
    public String buildOpenMapsURL(String chsaLongitude, String chsaLatitude){
        return OPEN_MAPS_BASE_URI_1 + chsaLongitude + " " + chsaLatitude + OPEN_MAPS_BASE_URI_2;
    }
}
