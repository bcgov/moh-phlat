/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moh.phlat.backend.databc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Feature {
    
    private String type;
    private Geometry geometry;
    private Properties properties;
    
    /**
    * No args constructor for use in serialization
    *
    */
    public Feature() {
    }

    /**
    *
    * @param geometry
    * @param type
    * @param properties
    */
    public Feature(String type, Geometry geometry, Properties properties) {
    super();
    this.type = type;
    this.geometry = geometry;
    this.properties = properties;
    }
}
