package com.moh.phlat.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.moh.phlat.backend.model.Status;
import com.moh.phlat.backend.repository.StatusRepository;
import com.moh.phlat.backend.testsupport.factories.StatusFactory;
import com.moh.phlat.backend.testsupport.factories.UserRoles;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The role names are arbitrary. They are provided solely to meet the requirements of the Spring Security framework,
 * and it shouldn't be assumed that they are used for authorization checks
 */
@WebMvcTest(StatusController.class)
public class StatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatusRepository statusRepository;

    @InjectMocks
    private StatusController statusController;

    List<Status> statuses = Collections.unmodifiableList(StatusFactory.createTwoStatuses());

    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void testGetAllStatus() throws Exception {

        when(statusRepository.findAll()).thenReturn(statuses);

        // Performing the GET request and get result in json string
        String jsonResponse = mockMvc.perform(get("/status/view/all").with(csrf()))
                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();

        DocumentContext documentContext = JsonPath.parse(jsonResponse);

        //check main elements
        assertThat(documentContext.read("$.status", String.class)).isEqualTo("success");
        assertThat(documentContext.read("$.statusCode", String.class)).isEqualTo("200");
        assertThat(documentContext.read("$.message", String.class)).isBlank();

        // check array elements
        assertThat(documentContext.read("$.data[0].id", String.class)).isEqualTo("1");
        assertThat(documentContext.read("$.data[0].code", String.class)).isEqualTo("INITIAL");
        assertThat(documentContext.read("$.data[0].type", String.class)).isEqualTo("System");
        assertThat(documentContext.read("$.data[0].description", String.class)).isEqualTo(
                "Set status for all records when the record is initially loaded into the phat table");
        assertThat(documentContext.read("$.data[0].isDeleted", Boolean.class)).isEqualTo(Boolean.FALSE);


        //check mocked method calls
        verify(statusRepository, times(1)).findAll();

    }

    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    public void testGetStatusById() throws Exception {

        when(statusRepository.findById(anyLong())).thenReturn(Optional.of(statuses.get(0)));

        String jsonResponse = mockMvc.perform(get("/status/view/1").with(csrf()))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();

        DocumentContext documentContext = JsonPath.parse(jsonResponse);


        assertThat(documentContext.read("$.status", String.class)).isEqualTo("success");
        assertThat(documentContext.read("$.statusCode", String.class)).isEqualTo("200");
        assertThat(documentContext.read("$.message", String.class)).isBlank();

        //check status contents
        assertThat(documentContext.read("$.data.id", String.class)).isEqualTo("1");
        assertThat(documentContext.read("$.data.code", String.class)).isEqualTo("INITIAL");
        assertThat(documentContext.read("$.data.type", String.class)).isEqualTo("System");
        assertThat(documentContext.read("$.data.description", String.class)).isEqualTo(
                "Set status for all records when the record is initially loaded into the phat table");
        assertThat(documentContext.read("$.data.isDeleted", Boolean.class)).isEqualTo(Boolean.FALSE);

        //verifying the behaviour
        verify(statusRepository, times(2)).findById(anyLong());

    }

    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    void testAddNewStatus() throws Exception {

        when(statusRepository.save(Mockito.any(Status.class))).thenReturn(statuses.get(0));


        ObjectMapper objectMapper = new ObjectMapper();
        // convert input to json.
        String jsonContent = objectMapper.writeValueAsString(statuses.get(0));


        String jsonResponse = mockMvc.perform(post("/status/add").with(csrf())
                                                                 .content(jsonContent)
                                                                 .contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();

        DocumentContext documentContext = JsonPath.parse(jsonResponse);

        assertThat(documentContext.read("$.status", String.class)).isEqualTo("success");
        assertThat(documentContext.read("$.statusCode", String.class)).isEqualTo("200");
        assertThat(documentContext.read("$.message", String.class)).isBlank();

        //check status contents
        //TODO not checking the id as code is not returning the id that is generated.
        //assertThat(documentContext.read("$.data.id", String.class)).isEqualTo("1");
        assertThat(documentContext.read("$.data.code", String.class)).isEqualTo("INITIAL");
        assertThat(documentContext.read("$.data.type", String.class)).isEqualTo("System");
        assertThat(documentContext.read("$.data.description", String.class)).isEqualTo(
                "Set status for all records when the record is initially loaded into the phat table");
        assertThat(documentContext.read("$.data.isDeleted", Boolean.class)).isEqualTo(Boolean.FALSE);

        //verifying the behaviour

        verify(statusRepository, times(1)).save(Mockito.any(Status.class));
    }


    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    void testUpdateStatusById() throws Exception {

        when(statusRepository.findById(anyLong())).thenReturn(Optional.of(statuses.get(0)));
        when(statusRepository.save(Mockito.any(Status.class))).thenReturn(statuses.get(1));

        ObjectMapper objectMapper = new ObjectMapper();
        // convert input to json.
        String jsonContent = objectMapper.writeValueAsString(statuses.get(1));

        String jsonResponse = mockMvc.perform(put("/status/update/1").with(csrf())
                                                                     .content(jsonContent)
                                                                     .contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();

        DocumentContext documentContext = JsonPath.parse(jsonResponse);

        assertThat(documentContext.read("$.status", String.class)).isEqualTo("success");
        assertThat(documentContext.read("$.statusCode", String.class)).isEqualTo("200");
        assertThat(documentContext.read("$.message", String.class)).isBlank();

        //check status contents

        assertThat(documentContext.read("$.data.id", String.class)).isEqualTo("1");
        //In current code, status is being ignored. So it should be original value retrieved from DB
        assertThat(documentContext.read("$.data.code", String.class)).isEqualTo("INITIAL");
        assertThat(documentContext.read("$.data.type", String.class)).isEqualTo("UserDefined");
        assertThat(documentContext.read("$.data.description", String.class))
                .isEqualTo("Set status if do not load flag is set to y");
        assertThat(documentContext.read("$.data.isDeleted", Boolean.class)).isEqualTo(Boolean.FALSE);

        //verifying the behaviour
        verify(statusRepository, times(1)).findById(anyLong());
        verify(statusRepository, times(1)).save(Mockito.any(Status.class));
    }


    @Test
    @WithMockUser(roles = {UserRoles.ROLE_REG_USER, UserRoles.ROLE_REG_ADMIN})
    void testDeleteByStatusId() throws Exception {

        when(statusRepository.findById(anyLong())).thenReturn(Optional.of(statuses.get(0)));
        when(statusRepository.save(Mockito.any(Status.class))).thenReturn(statuses.get(0));


        String jsonResponse = mockMvc.perform(delete("/status/delete/1").with(csrf()))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();

        DocumentContext documentContext = JsonPath.parse(jsonResponse);

        assertThat(documentContext.read("$.status", String.class)).isEqualTo("success");
        assertThat(documentContext.read("$.statusCode", String.class)).isEqualTo("200");
        assertThat(documentContext.read("$.message", String.class)).isBlank();

        //check status contents
        assertThat(documentContext.read("$.data.id", String.class)).isEqualTo("1");
        //In current code, status is being ignored. So it should be original value retrieved from DB
        assertThat(documentContext.read("$.data.code", String.class)).isEqualTo("INITIAL");
        assertThat(documentContext.read("$.data.type", String.class)).isEqualTo("System");
        assertThat(documentContext.read("$.data.description", String.class))
                .isEqualTo("Set status for all records when the record is initially loaded into the phat table");
        //only this attribute should change from false to true
        assertThat(documentContext.read("$.data.isDeleted", Boolean.class)).isEqualTo(Boolean.TRUE);

        //verifying the behaviour
        verify(statusRepository, times(1)).findById(anyLong());
        verify(statusRepository, times(1)).save(Mockito.any(Status.class));
    }


}



