package com.moh.phlat.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moh.phlat.backend.validation.DisplayColumnCreateGroup;
import com.moh.phlat.backend.validation.DisplayColumnsUpdateGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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

    //validate only during creation not update
    @NotBlank(message = "Page Name cannot be blank", groups = DisplayColumnCreateGroup.class)
    @Pattern(regexp = "^(file-task-management|status-code-management)$"
            , message = "Allowed values are 'file-task-management' or 'status-code-management'")
    private String pageName;

    //validate both during create and update
    @NotEmpty(message = "There must be at least one value of columns to display",
            groups = {DisplayColumnCreateGroup.class, DisplayColumnsUpdateGroup.class})
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
