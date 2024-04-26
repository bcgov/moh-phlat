package com.moh.phlat.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="code", length=30, nullable = false, unique = true)
    @NotNull(message="Code is required.")
    @NotEmpty(message="Code must not be null or empty.")
    private String code;
    
    @Column(name="description",  length=500, nullable = false)
    @NotNull(message="Description is required.")
    @NotEmpty(message="Description must not be null or empty.")
    private String description;

	@Column(name="is_deleted")
    private Boolean isDeleted;


}
