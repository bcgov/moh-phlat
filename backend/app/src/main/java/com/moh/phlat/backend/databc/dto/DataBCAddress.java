package com.moh.phlat.backend.databc.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBCAddress {
    private String type;
    private String queryAddress;
    private String searchTimestamp;
    private String executionTime;
    private String version;
    private String baseDataDate;
    private Crs crs;
    private String interpolation;
    private String echo;
    private String locationDescriptor;
    private String setBack;
    private String minScore;
    private String maxResults;
    private String disclaimer;
    private String privacyStatement;
    private String copyrightNotice;
    private String copyrightLicense;
    private List<Feature> features = new ArrayList<Feature>();

    /**
    * No args constructor for use in serialization
    *
    */
    public DataBCAddress() {
    }

    /**
    *
    * @param setBack
    * @param minScore
    * @param privacyStatement
    * @param crs
    * @param echo
    * @param type
    * @param copyrightLicense
    * @param queryAddress
    * @param version
    * @param executionTime
    * @param features
    * @param interpolation
    * @param baseDataDate
    * @param maxResults
    * @param copyrightNotice
    * @param locationDescriptor
    * @param searchTimestamp
    * @param disclaimer
    */
    public DataBCAddress(String type, String queryAddress, String searchTimestamp, String executionTime, String version, String baseDataDate, Crs crs, String interpolation, String echo, String locationDescriptor, String setBack, String minScore, String maxResults, String disclaimer, String privacyStatement, String copyrightNotice, String copyrightLicense, List<Feature> features) {
    super();
    this.type = type;
    this.queryAddress = queryAddress;
    this.searchTimestamp = searchTimestamp;
    this.executionTime = executionTime;
    this.version = version;
    this.baseDataDate = baseDataDate;
    this.crs = crs;
    this.interpolation = interpolation;
    this.echo = echo;
    this.locationDescriptor = locationDescriptor;
    this.setBack = setBack;
    this.minScore = minScore;
    this.maxResults = maxResults;
    this.disclaimer = disclaimer;
    this.privacyStatement = privacyStatement;
    this.copyrightNotice = copyrightNotice;
    this.copyrightLicense = copyrightLicense;
    this.features = features;
    }
}
