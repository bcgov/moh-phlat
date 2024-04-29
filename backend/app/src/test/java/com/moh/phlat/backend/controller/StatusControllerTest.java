package com.moh.phlat.backend.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.moh.phlat.backend.model.Status;
import com.moh.phlat.backend.repository.StatusRepository;
import com.moh.phlat.backend.testsupport.factories.StatusFactory;
import com.moh.phlat.backend.testsupport.factories.UserRoles;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        String jsonResponse = mockMvc.perform(get("/statuses").with(csrf()))
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
        assertThat(documentContext.read("$.data[0].description", String.class)).isEqualTo(
                "Set status for all records when the record is initially loaded into the phat table");
        assertThat(documentContext.read("$.data[0].isDeleted", Boolean.class)).isEqualTo(Boolean.FALSE);

        //check mocked method calls
        verify(statusRepository, times(1)).findAll();

    }


}



