package com.moh.phlat.backend.controller;

import com.moh.phlat.backend.repository.StatusRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@WebMvcTest(StatusController.class)
public class StatusControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StatusRepository statusRepository;

	@Test
	public void addNewStatus() throws Exception {
		this.mockMvc.perform(post("/status/add").content("abc").contentType(MediaType.APPLICATION_JSON_VALUE)).
		  andExpect(status().isOk()).
		  andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).
		  andExpect(jsonPath("$.status").value("<value>")).
		  andExpect(jsonPath("$.statusCode").value("<value>")).
		  andExpect(jsonPath("$.message").value("<value>")).
		  andExpect(jsonPath("$.data").value("<value>"));
	}

	@Test
	public void getAllStatus() throws Exception {
		this.mockMvc.perform(get("/status/view/all")).
		  andExpect(status().isOk()).
		  andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).
		  andExpect(jsonPath("$.status").value("<value>")).
		  andExpect(jsonPath("$.statusCode").value("<value>")).
		  andExpect(jsonPath("$.message").value("<value>")).
		  andExpect(jsonPath("$.data").value("<value>"));
	}

	@Test
	public void getStatusById() throws Exception {
		this.mockMvc.perform(get("/status/view/{id}", 123L)).
		  andExpect(status().isOk()).
		  andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).
		  andExpect(jsonPath("$.status").value("<value>")).
		  andExpect(jsonPath("$.statusCode").value("<value>")).
		  andExpect(jsonPath("$.message").value("<value>")).
		  andExpect(jsonPath("$.data").value("<value>"));
	}

	@Test
	public void updateStatusById() throws Exception {
		this.mockMvc.perform(put("/status/update/{id}", 123L).content("abc").contentType(MediaType.APPLICATION_JSON_VALUE)).
		  andExpect(status().isOk()).
		  andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).
		  andExpect(jsonPath("$.status").value("<value>")).
		  andExpect(jsonPath("$.statusCode").value("<value>")).
		  andExpect(jsonPath("$.message").value("<value>")).
		  andExpect(jsonPath("$.data").value("<value>"));
	}

	@Test
	public void deleteStatusById() throws Exception {
		this.mockMvc.perform(delete("/status/delete/{id}", 123L))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.status").value("<value>"))
			.andExpect(jsonPath("$.statusCode").value("<value>"))
			.andExpect(jsonPath("$.message").value("<value>"))
			.andExpect(jsonPath("$.data").value("<value>"));
	}
}
