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
import com.moh.phlat.backend.databc.dto.DataBCAddress;

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
    
	public DataBCAddress callDataBC(String srsCode, String address) {
		
		String dataBCURL = buildDataBCURL(srsCode, address);
		
		Mono<String> mono = webClient.get()
				.uri(dataBCURL)
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
			logger.error("DataBC lookup with srsCode {} returned the following error: {}", srsCode, jsonResponse);
			return null;
		}
		return new Gson().fromJson(jsonResponse, DataBCAddress.class);
	}
    
    public String buildDataBCURL(String srsCode, String address){
        return DATABC_BASE_URI_1 + srsCode + DATABC_BASE_URI_2 + address.replaceAll(",", "");
    }
}
