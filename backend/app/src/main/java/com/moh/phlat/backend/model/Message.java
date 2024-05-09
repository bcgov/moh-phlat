package com.moh.phlat.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="process_data_id", referencedColumnName="id", nullable = false)
    private ProcessData processData;


}
