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
public class Geometry {
    
    private String type;
    private Crs crs;
    private String[] coordinates;

    /**
    * No args constructor for use in serialization
    *
    */
    public Geometry() {
    }

    /**
    *
    * @param crs
    * @param coordinates
    * @param type
    */
    public Geometry(String type, Crs crs, String[] coordinates) {
    super();
    this.type = type;
    this.crs = crs;
    this.coordinates = coordinates;
    }
}
