package com.moh.phlat.backend.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.testsupport.factories.ControlTableFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ControlController.class)
public class ControlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ControlRepository controlRepository;

    @InjectMocks
    private ControlController controlController;

    @BeforeEach
    void setUp() {
        List<Control> controls = ControlTableFactory.createControlList();
        Mockito.when(controlRepository.findAll()).thenReturn(controls);
    }

    @Test
    public void testGetAllControls() throws Exception {


        // Performing the GET request and get result in json string
        String jsonResponse = mockMvc.perform(get("/controltable/view/all"))
                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();


        DocumentContext documentContext = JsonPath.parse(jsonResponse);


        //checking main elements
        assertThat(documentContext.read("$.status", String.class)).isEqualTo("success");
        assertThat(documentContext.read("$.statusCode", String.class)).isEqualTo("200");

        //checking contents of 1st control
        assertThat(documentContext.read("$.data[0].id", Long.class)).isEqualTo(1);
        assertThat(documentContext.read("$.data[0].fileExtractedDate", String.class)).isEqualTo("2024-01-01T00:00:00.000+00:00");

        assertThat(documentContext.read("$.data[0].userId", String.class)).isEqualTo("user1");
        assertThat(documentContext.read("$.data[0].fileName", String.class)).isEqualTo("File1.txt");
        assertThat(documentContext.read("$.data[0].batchLabelName", String.class)).isEqualTo("Label1");
        assertThat(documentContext.read("$.data[0].loadTypeFacility", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data[0].loadTypeHds", Boolean.class)).isEqualTo(Boolean.FALSE);
        assertThat(documentContext.read("$.data[0].loadTypeOrg", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data[0].createdBy", String.class)).isEqualTo("Admin1");


        //checking contents of 3rd control
        assertThat(documentContext.read("$.data[2].id", Long.class)).isEqualTo(3);
        assertThat(documentContext.read("$.data[2].userId", String.class)).isEqualTo("user3");
        assertThat(documentContext.read("$.data[2].fileName", String.class)).isEqualTo("File3.txt");
        assertThat(documentContext.read("$.data[2].fileExtractedDate",String.class)).isNull();

        assertThat(documentContext.read("$.data[2].batchLabelName", String.class)).isEqualTo("Label3");
        assertThat(documentContext.read("$.data[2].loadTypeFacility", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data[2].loadTypeHds", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data[2].loadTypeOrg", Boolean.class)).isEqualTo(Boolean.FALSE);
        assertThat(documentContext.read("$.data[2].createdBy", String.class)).isEqualTo("Admin3");


    }


}
