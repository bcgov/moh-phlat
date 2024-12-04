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
public class Crs {

    private String type;
    private CRSProperties properties;

    /**
     * No args constructor for use in serialization
     *
     */
    public Crs() {
    }

    /**
     *
     * @param type
     * @param properties
     */
    public Crs(String type, CRSProperties properties) {
        super();
        this.type = type;
        this.properties = properties;
    }
    
    @Getter
    @Setter
    public class CRSProperties {

        public CRSProperties(String code) {
            super();
            this.code = code;
        }
        
        String code;
    }
}
