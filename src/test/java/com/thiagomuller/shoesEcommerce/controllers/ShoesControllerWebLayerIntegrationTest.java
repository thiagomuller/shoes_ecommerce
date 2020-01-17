package com.thiagomuller.shoesEcommerce.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

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
	private Shoe secondTestShoe;
	
	
	@Before
	public void setUpTestDataOnDatabase() throws Exception{
		this.testShoe = new Shoe(38,"Dafiti","https://teste.com.br","Purple",200.45,"Brazil","Boot",Beak.ROUND,"Syntetic");
		this.secondTestShoe = new Shoe(40, "Nike", "https://teste.com.br", "Blue", 400.50, "China", "Social", Beak.POINTED, "Syntetic");
		mvc.perform(post("/shoes")
				.content(asJsonString(this.testShoe))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
		
		mvc.perform(post("/shoes")
				.content(asJsonString(this.secondTestShoe))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	@Test()
	public void verifyShoesGetRouteReturnsShoesTestData() throws Exception{
		mvc.perform(get("/shoes/" + this.testShoe.getId())//
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].size", is(this.testShoe.getSize())))
		.andExpect(jsonPath("$[0].brand", is(this.testShoe.getBrand())))
		.andExpect(jsonPath("$[0].imageUrl", is(this.testShoe.getImageUrl())))
		.andExpect(jsonPath("$[0].color", is(this.testShoe.getColor())))
		.andExpect(jsonPath("$[0].price", is(this.testShoe.getPrice())))
		.andExpect(jsonPath("$[0].madeIn", is(this.testShoe.getMadeIn())))
		.andExpect(jsonPath("$[0].shoesType", is(this.testShoe.getShoesType())))
		.andExpect(jsonPath("$[0].beak", is(this.testShoe.getBeak())))
		.andExpect(jsonPath("$[0].material", is(this.testShoe.getMaterial())));
	}
	
	
	@Test()
	public void verifyShoesGetRouteReturnsShoesList() throws Exception{
		mvc.perform(get("/shoes")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())//
		.andExpect(jsonPath("$[0].brand", is(this.testShoe.getBrand())))
		.andExpect(jsonPath("$[1].brand", is(this.secondTestShoe.getBrand())));
	}
	
	@Test
	public void verifyShoesPostRouteCreatesANewShoe() throws Exception{
		Shoe shoe  = new Shoe(36, "All-Star", "https://teste.com.br", "Purple", 120.30, "USA", "Sandal", Beak.ROUND, "Syntetic");
		mvc.perform(post("/shoes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(shoe)))
		.andExpect(status().isCreated());
		
		mvc.perform(get("/shoes/" + shoe.getId())//
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].size", is(shoe.getSize())))
				.andExpect(jsonPath("$[0].brand", is(shoe.getBrand())))
				.andExpect(jsonPath("$[0].imageUrl", is(shoe.getImageUrl())))
				.andExpect(jsonPath("$[0].color", is(shoe.getColor())))
				.andExpect(jsonPath("$[0].price", is(shoe.getPrice())))
				.andExpect(jsonPath("$[0].madeIn", is(shoe.getMadeIn())))
				.andExpect(jsonPath("$[0].shoesType", is(shoe.getShoesType())))
				.andExpect(jsonPath("$[0].beak", is(shoe.getBeak())))
				.andExpect(jsonPath("$[0].material", is(shoe.getMaterial())));		
	}
	
	static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
