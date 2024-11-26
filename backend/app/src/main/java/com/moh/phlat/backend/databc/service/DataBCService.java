package com.moh.phlat.backend.databc.service;

import java.net.http.HttpClient;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.JdkClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.google.gson.Gson;
import com.moh.phlat.backend.databc.dto.DataBCAddress;

import ca.bc.gov.health.plr.dto.facility.esb.CivicAddressDto;
import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBException;
import reactor.core.publisher.Mono;

@Component
public class DataBCService {
    
    private static final Logger logger = LoggerFactory.getLogger(DataBCService.class.getSimpleName());
    private WebClient webClient;
    
    // URL Strings
    private static final String DATABC_BASE_URI_1 = "https://geocoder.api.gov.bc.ca/addresses.json?outputSRS=";
    private static final String DATABC_BASE_URI_2 = "&echo=true&brief=false&addressString=";
    private static final String OPEN_MAPS_BASE_URI_1 = "https://openmaps.gov.bc.ca/geo/pub/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=pub%3AWHSE_ADMIN_BOUNDARIES.BCHA_CMNTY_HEALTH_SERV_AREA_SP&srsname=EPSG:4326&cql_filter=INTERSECTS(SHAPE,POINT(";
    private static final String OPEN_MAPS_BASE_URI_2 = "))&propertyName=CMNTY_HLTH_SERV_AREA_CODE,CMNTY_HLTH_SERV_AREA_NAME,LOCAL_HLTH_AREA_NAME,LOCAL_HLTH_AREA_CODE,HLTH_SERVICE_DLVR_AREA_NAME,HLTH_AUTHORITY_NAME&outputFormat=application%2Fjson";
	
	@Value("${databc.timeout}")
	private int timeout;
	
	@PostConstruct
	public void initDataBCService() throws JAXBException {
		HttpClient httpClient = HttpClient.newBuilder()
				.connectTimeout(Duration.ofSeconds(timeout))
				.build();
		
		webClient = WebClient.builder()
				.clientConnector(new JdkClientHttpConnector(httpClient))
				.build();
	}
    
	public DataBCAddress callDataBC(String srsCode, CivicAddressDto address) {
		
		String dataBCURL = buildDataBCURL(srsCode, address);
		
		Mono<String> mono = webClient.get()
				.uri(dataBCURL)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.doOnError(WebClientRequestException.class, ex -> {
                    logger.error("HTTP Request failed due to an error: ", ex);
                })
				.doOnError(WebClientResponseException.class, ex -> {
                    logger.error("HTTP Response Status: {}, Response Body: {}", ex.getStatusCode(), ex.getResponseBodyAsString());
                });
		
		String jsonResponse = mono.block();
		return new Gson().fromJson(jsonResponse, DataBCAddress.class);
	}
    
	public String callOpenMaps(String srsCode, CivicAddressDto address) {
		
		String openMapsUrl = buildOpenMapsURL(srsCode, address);
		
		Mono<String> mono = webClient.get()
				.uri(openMapsUrl)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.doOnError(WebClientRequestException.class, ex -> {
                    logger.error("HTTP Request failed due to an error: ", ex);
                })
				.doOnError(WebClientResponseException.class, ex -> {
                    logger.error("HTTP Response Status: {}, Response Body: {}", ex.getStatusCode(), ex.getResponseBodyAsString());
                });
		
		String jsonResponse = mono.block();
		return jsonResponse;
	}
    
    public String buildDataBCURL(String srsCode, CivicAddressDto address){
        return DATABC_BASE_URI_1 + srsCode + DATABC_BASE_URI_2 + address.getAddressLineOne().replaceAll(" ", "%20").replaceAll(",", "");
    }
    
    public String buildOpenMapsURL(String srsCode, CivicAddressDto address){
        return OPEN_MAPS_BASE_URI_1 + srsCode + OPEN_MAPS_BASE_URI_2 + address.getAddressLineOne().replaceAll(" ", "%20").replaceAll(",", "");
    }
}
