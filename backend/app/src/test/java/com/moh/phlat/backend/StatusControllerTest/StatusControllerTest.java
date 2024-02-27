package com.moh.phlat.backend.controller;

import com.moh.phlat.backend.repository.ControlRepository;
import com.moh.phlat.backend.repository.SourceDataRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SourceDataController.class)
public class SourceDataControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ControlRepository controlRepository;

	@MockBean
	private SourceDataRepository sourceDataRepository;

	@Test
	public void uploadFile() throws Exception {
		this.mockMvc.perform(post("/sourcedata/upload")
				.param("id", "<value>")
				.param("fileName", "<value>")
				.param("userId", "<value>")
				.param("fileExtractedDate", "<value>")
				.param("processStartDate", "<value>")
				.param("processEndDate", "<value>")
				.param("batchLabelName", "<value>")
				.param("loadTypeFacility", "<value>")
				.param("loadTypeHds", "<value>")
				.param("loadTypeOrg", "<value>")
				.param("loadTypeOFRelationship", "<value>")
				.param("loadTypeOORelationship", "<value>")
				.param("loadTypeIORelationship", "<value>")
				.param("loadTypeWOXref", "<value>")
				.param("loadTypeWPIXref", "<value>")
				.param("statusCode", "<value>")
				.param("createdAt", "<value>")
				.param("createdBy", "<value>")
				.param("updatedAt", "<value>")
				.param("updatedBy", "<value>")
				.param("file", "<value>"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.status").value("<value>"))
			.andExpect(jsonPath("$.statusCode").value("<value>"))
			.andExpect(jsonPath("$.message").value("<value>"))
			.andExpect(jsonPath("$.data").value("<value>"));
	}
}
