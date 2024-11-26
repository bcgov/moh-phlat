/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moh.phlat.backend.databc.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Properties {
    
    private String fullAddress;
    private String score;
    private String matchPrecision;
    private String precisionPoints;
    private List<Object> faults = new ArrayList<Object>();
    private String unitDesignator;
    private String unitNumber;
    private String unitNumberSuffix;
    private String civicNumber;
    private String civicNumberSuffix;
    private String streetName;
    private String streetType;
    private boolean isStreetTypePrefix;   
    private String streetDirection;
    private boolean isStreetDirectionPrefix; 
    private String streetQualifier;
    private String localityName;
    private String provinceCode;        
    
    private String localityType;
    private String electoralArea;
    private String locationPositionalAccuracy;
    private String locationDescriptor;
    private String siteID;
    private String blockID;
    private String accessNotes;
    private String siteStatus;

    /**
    * No args constructor for use in serialization
    *
    */
    public Properties() {
    }

    /**
    *
    * @param locationPositionalAccuracy
    * @param precisionPoints
    * @param siteStatus
    * @param faults
    * @param electoralArea
    * @param blockID
    * @param score
    * @param fullAddress
    * @param accessNotes
    * @param locationDescriptor
    * @param siteID
    * @param matchPrecision
    * @param localityType
    */
    public Properties(String fullAddress, String score, String matchPrecision, String precisionPoints, List<Object> faults, String localityType, String electoralArea, String locationPositionalAccuracy, String locationDescriptor, String siteID, String blockID, String accessNotes, String siteStatus) {
    this.fullAddress = fullAddress;
    this.score = score;
    this.matchPrecision = matchPrecision;
    this.precisionPoints = precisionPoints;
    this.faults = faults;
    this.localityType = localityType;
    this.electoralArea = electoralArea;
    this.locationPositionalAccuracy = locationPositionalAccuracy;
    this.locationDescriptor = locationDescriptor;
    this.siteID = siteID;
    this.blockID = blockID;
    this.accessNotes = accessNotes;
    this.siteStatus = siteStatus;
    }
}
