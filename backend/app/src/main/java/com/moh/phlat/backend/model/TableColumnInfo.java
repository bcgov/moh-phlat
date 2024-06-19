package com.moh.phlat.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "TABLE_COLUMN_INFO")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TableColumnInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="TABLE_NAME")    
    private String tableName;

    @Column(name="COLUMN_NAME")    
    private String columnName;

    @Column(name="HEADER_NAME")    
    private String headerName;

    @Column(name="VARIABLE_NAME")    
    private String variableName;

    @Column(name="TITLE")    
    private String title;
}
