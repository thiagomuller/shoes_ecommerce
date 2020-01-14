package com.thiagomuller.shoesEcommerce.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.thiagomuller.shoesEcommerce.service.ShoesService;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoesController.class)
public class ShoesControllerWebLayerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ShoesService service;
	
	@Test()
	public void veiryShoesGetRouteControllerReturnsStatus200() throws Exception{
		mvc.perform(get("/shoes")).andExpect(status().isOk());
	}
	
	@Test()
	public void verifyShoesGetRouteReturnsShoesList() throws Exception{
		mvc.perform(get("/shoes").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())//
		.andExpect(content().json("{[]}"));

	}

}
