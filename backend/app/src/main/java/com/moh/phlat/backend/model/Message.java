package com.moh.phlat.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "message_detail")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_code", nullable = false)
    private String messageCode;

    @Column(name = "message_type", nullable = false)
    private String messageType;

    @Column(name = "message_desc", nullable = false)
    private String messageDesc;

    @JsonIgnore
    @Column(name = "source_system_name", nullable = false)
    private String sourceSystemName;

    @JsonIgnore
    @Column(name = "source_system_message_code")
    private String sourceSystemMessageCode;

    @JsonIgnore
    @Column(name = "source_system_message_type")
    private String sourceSystemMessageType;

    @JsonIgnore
    @Column(name = "source_system_message_desc")
    private String sourceSystemMessageDesc;


}
