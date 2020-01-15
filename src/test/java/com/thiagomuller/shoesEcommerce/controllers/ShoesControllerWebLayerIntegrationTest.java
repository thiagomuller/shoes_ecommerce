package com.thiagomuller.shoesEcommerce.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.thiagomuller.shoesEcommerce.models.Beak;
import com.thiagomuller.shoesEcommerce.models.Shoe;
import com.thiagomuller.shoesEcommerce.service.ShoesService;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoesController.class)
public class ShoesControllerWebLayerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ShoesService service;
	
	private Shoe testShoe;
	
	
	@Before
	public void setUpTestDataOnDatabase() throws Exception{
		this.testShoe = new Shoe(38,"Dafiti","https://teste.com.br","Purple",200.45,"Brazil","Boot",Beak.ROUND,"Syntetic");
		mvc.perform(post("/shoes")
				.content(asJsonString(this.testShoe))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
		
		//TODO more verifications here
	}
	
	@Test()
	public void verifyShoesGetRouteReturnsShoesTestData() {
		/*
		mvc.perform(get("/shoes/" + this.testShoe.getId())//
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		*/
		//TODO make more verifications here
	}
	
	@Test()
	public void verifyShoesGetRouteReturnsShoesList() throws Exception{
		mvc.perform(get("/shoes").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())//
		.andExpect(content().json("{[]}"));

	}
	
	static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
