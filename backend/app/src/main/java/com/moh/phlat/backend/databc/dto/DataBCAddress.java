package com.moh.phlat.backend.databc.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
