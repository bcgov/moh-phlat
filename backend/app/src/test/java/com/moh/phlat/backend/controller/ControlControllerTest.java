package com.moh.phlat.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.moh.phlat.backend.model.Control;
import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.testsupport.factories.ControlTableFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    List<Control> controls = Collections.unmodifiableList(ControlTableFactory.createControlList());

    @Test
    public void testGetAllControls() throws Exception {

        Mockito.when(controlRepository.findAll()).thenReturn(controls);

        // Performing the GET request and get result in json string
        String jsonResponse = mockMvc.perform(get("/controltable/view/all"))
                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();

        DocumentContext documentContext = JsonPath.parse(jsonResponse);

        //using common function to check the response.
        checkResponseJsonElements(documentContext);

        verify(controlRepository, times(1)).findAll();


    }


    @Test
    public void testGetControlById() throws Exception {

        Mockito.when(controlRepository.findById(anyLong())).thenReturn(Optional.of(controls.get(0)));

        String jsonResponse = mockMvc.perform(get("/controltable/view/1"))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();

        DocumentContext documentContext = JsonPath.parse(jsonResponse);

        //checking main elements
        assertThat(documentContext.read("$.status", String.class)).isEqualTo("success");
        assertThat(documentContext.read("$.statusCode", String.class)).isEqualTo("200");
        assertThat(documentContext.read("$.message", String.class)).isBlank();

        assertThat(documentContext.read("$.data.id", Long.class)).isEqualTo(1);
        assertThat(documentContext.read("$.data.fileExtractedDate", String.class)).isEqualTo(
                "2024-01-01T00:00:00.000+00:00");

        assertThat(documentContext.read("$.data.userId", String.class)).isEqualTo("user1");
        assertThat(documentContext.read("$.data.fileName", String.class)).isEqualTo("File1.txt");
        assertThat(documentContext.read("$.data.batchLabelName", String.class)).isEqualTo("Label1");
        assertThat(documentContext.read("$.data.loadTypeFacility", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data.loadTypeHds", Boolean.class)).isEqualTo(Boolean.FALSE);
        assertThat(documentContext.read("$.data.loadTypeOrg", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data.createdBy", String.class)).isEqualTo("Admin1");

        //verifying the behaviour
        verify(controlRepository, times(2)).findById(anyLong());

    }

    @Test
    public void testGetControlByFileName() throws Exception {

        Mockito.when(controlRepository.findByFileName(anyString())).thenReturn(controls);

        String jsonResponse = mockMvc.perform(get("/controltable/view/filename/file1"))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();

        DocumentContext documentContext = JsonPath.parse(jsonResponse);


        checkResponseJsonElements(documentContext);

        //verifying the behaviour
        verify(controlRepository, times(1)).findByFileName(anyString());


    }

    @Test
    public void testUpdateControl() throws Exception {

        Mockito.when(controlRepository.findById(anyLong())).thenReturn(Optional.of(controls.get(0)));
        Mockito.when(controlRepository.save(Mockito.any(Control.class))).thenReturn(controls.get(0));

        ObjectMapper objectMapper = new ObjectMapper();

        // convert input to json.
        String jsonContent = objectMapper.writeValueAsString(controls.get(1));


        String jsonResponse = mockMvc.perform(put("/controltable/update/1")
                                                      .content(jsonContent)
                                                      .contentType(MediaType.APPLICATION_JSON))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();

        DocumentContext documentContext = JsonPath.parse(jsonResponse);

        //checking main elements
        assertThat(documentContext.read("$.status", String.class)).isEqualTo("success");
        assertThat(documentContext.read("$.statusCode", String.class)).isEqualTo("200");
        assertThat(documentContext.read("$.message", String.class)).isBlank();


        assertThat(documentContext.read("$.data.fileExtractedDate", String.class)).isEqualTo(
                "2024-02-01T00:00:00.000+00:00");

        assertThat(documentContext.read("$.data.userId", String.class)).isEqualTo("user2");
        assertThat(documentContext.read("$.data.fileName", String.class)).isEqualTo("File2.txt");
        assertThat(documentContext.read("$.data.batchLabelName", String.class)).isEqualTo("Label2");
        assertThat(documentContext.read("$.data.loadTypeFacility", Boolean.class)).isEqualTo(Boolean.FALSE);
        assertThat(documentContext.read("$.data.loadTypeHds", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data.loadTypeOrg", Boolean.class)).isEqualTo(Boolean.FALSE);
        assertThat(documentContext.read("$.data.createdBy", String.class)).isEqualTo("Admin1");
        assertThat(documentContext.read("$.data.updatedBy", String.class)).isEqualTo("Admin2");

        //verifying the behaviour
        verify(controlRepository, times(1)).findById(anyLong());
        verify(controlRepository, times(1)).save(Mockito.any(Control.class));

    }

    @Test
    public void testApproveToLoadToPLR() throws Exception {
        Mockito.when(controlRepository.findById(anyLong())).thenReturn(Optional.of(controls.get(0)));
        //save return is ignored in the code as of now.
        Mockito.when(controlRepository.save(Mockito.any(Control.class))).thenReturn(controls.get(0));

        String jsonResponse = mockMvc.perform(put("/controltable/approve/1"))
                                     .andExpect(status().isOk())
                                     .andReturn().getResponse().getContentAsString();

        DocumentContext documentContext = JsonPath.parse(jsonResponse);

        //checking main elements
        assertThat(documentContext.read("$.status", String.class)).isEqualTo("success");
        assertThat(documentContext.read("$.statusCode", String.class)).isEqualTo("200");
        assertThat(documentContext.read("$.data.id", Long.class)).isEqualTo(1);
        assertThat(documentContext.read("$.data.fileExtractedDate", String.class)).isEqualTo(
                "2024-01-01T00:00:00.000+00:00");

        assertThat(documentContext.read("$.data.userId", String.class)).isEqualTo("user1");
        assertThat(documentContext.read("$.data.fileName", String.class)).isEqualTo("File1.txt");
        assertThat(documentContext.read("$.data.batchLabelName", String.class)).isEqualTo("Label1");
        assertThat(documentContext.read("$.data.loadTypeFacility", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data.loadTypeHds", Boolean.class)).isEqualTo(Boolean.FALSE);
        assertThat(documentContext.read("$.data.loadTypeOrg", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data.createdBy", String.class)).isEqualTo("Admin1");


        //these are the values updated for approving in Control entity
        assertThat(documentContext.read("$.data.statusCode", String.class)).isEqualTo("APPROVED");
        assertThat(documentContext.read("$.data.updatedBy", String.class)).isEqualTo("SYSTEM");
        assertThat(documentContext.read("$.message", String.class)).isEqualTo("Record approved successfully.");


        //verifying the behaviour
        verify(controlRepository, times(1)).findById(anyLong());
        verify(controlRepository, times(1)).save(Mockito.any(Control.class));


    }


    private static void checkResponseJsonElements(DocumentContext documentContext) {
        //checking main elements
        assertThat(documentContext.read("$.status", String.class)).isEqualTo("success");
        assertThat(documentContext.read("$.statusCode", String.class)).isEqualTo("200");

        //checking contents of 1st control
        assertThat(documentContext.read("$.data[0].id", Long.class)).isEqualTo(1);
        assertThat(documentContext.read("$.data[0].fileExtractedDate", String.class)).isEqualTo(
                "2024-01-01T00:00:00.000+00:00");

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
        assertThat(documentContext.read("$.data[2].fileExtractedDate", String.class)).isNull();

        assertThat(documentContext.read("$.data[2].batchLabelName", String.class)).isEqualTo("Label3");
        assertThat(documentContext.read("$.data[2].loadTypeFacility", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data[2].loadTypeHds", Boolean.class)).isEqualTo(Boolean.TRUE);
        assertThat(documentContext.read("$.data[2].loadTypeOrg", Boolean.class)).isEqualTo(Boolean.FALSE);
        assertThat(documentContext.read("$.data[2].createdBy", String.class)).isEqualTo("Admin3");
    }


}
