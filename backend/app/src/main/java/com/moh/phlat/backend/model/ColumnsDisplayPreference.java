package com.moh.phlat.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "columns_display_preference")
public class ColumnsDisplayPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonIgnore
    private String userId;

    private String viewName;


    @NotEmpty(message = "At least one value for display column is needed." )
    private List<String> displayColumns;

    @JsonIgnore
    private Date createdAt;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private Date updatedAt;

    @JsonIgnore
    private String updatedBy;


}
