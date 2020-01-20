package com.thiagomuller.shoesEcommerce.controllers.integrationTests;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thiagomuller.shoesEcommerce.ShoesEcommerceApplication;
import com.thiagomuller.shoesEcommerce.TestUtils;
import com.thiagomuller.shoesEcommerce.models.Beak;
import com.thiagomuller.shoesEcommerce.models.Shoe;
import com.thiagomuller.shoesEcommerce.repositories.ShoesRepository;
import com.thiagomuller.shoesEcommerce.service.ShoesService;


@RunWith(SpringRunner.class)
//There's a difference between tutorial and mine here, cuz I'm not passing webEnvironment attribute on the annotation, cuz I coundn't make it work
@SpringBootTest(classes = ShoesEcommerceApplication.class, properties = ("spring.h2.console.enabled=true"))
@AutoConfigureMockMvc
//I'm also not using @TestPropertySource annotation, cuz I'm using H2 from both running my application so far, and  the tests
public class ShoesControllerWebLayerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ShoesRepository repository;
	
	private Shoe firstTestShoe;
	private Shoe secondTestShoe;
	private Shoe postTestShoe;
	
	
	@Before
	public void setUpTestDataOnDatabase() throws Exception{
		this.firstTestShoe = new Shoe(Long.valueOf(1000),38,"Dafiti","https://teste.com.br","Purple",200.45,"Brazil","Boot",Beak.ROUND,"Syntetic");
		this.secondTestShoe = new Shoe(Long.valueOf(1001),40, "Nike", "https://teste.com.br", "Blue", 400.50, "China", "Social", Beak.POINTED, "Syntetic"); 
		this.repository.save(this.firstTestShoe);
		this.repository.save(this.secondTestShoe);
		this.firstTestShoe = new Shoe(Long.valueOf(1000),38,"Dafiti","https://teste.com.br","Purple",200.45,"Brazil","Boot",Beak.ROUND,"Syntetic");
		this.secondTestShoe = new Shoe(Long.valueOf(1001),40, "Nike", "https://teste.com.br", "Blue", 400.50, "China", "Social", Beak.POINTED, "Syntetic");
		mvc.perform(post("/shoes")
				.content(TestUtils.asJsonString(this.firstTestShoe))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
		
		mvc.perform(post("/shoes")
				.content(TestUtils.asJsonString(this.secondTestShoe))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	@Test()
	public void verifyShoesGetRouteReturnsShoesTestData() throws Exception{
		mvc.perform(get("/shoes/" + this.firstTestShoe.getId())//
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].size", is(this.firstTestShoe.getSize())))
		.andExpect(jsonPath("$[0].brand", is(this.firstTestShoe.getBrand())))
		.andExpect(jsonPath("$[0].imageUrl", is(this.firstTestShoe.getImageUrl())))
		.andExpect(jsonPath("$[0].color", is(this.firstTestShoe.getColor())))
		.andExpect(jsonPath("$[0].price", is(this.firstTestShoe.getPrice())))
		.andExpect(jsonPath("$[0].madeIn", is(this.firstTestShoe.getMadeIn())))
		.andExpect(jsonPath("$[0].shoesType", is(this.firstTestShoe.getShoesType())))
		.andExpect(jsonPath("$[0].beak", is(this.firstTestShoe.getBeak())))
		.andExpect(jsonPath("$[0].material", is(this.firstTestShoe.getMaterial())));
	}
	
	@Test()
	public void verifyShoesGetRouteReturnsShoesList() throws Exception{
		mvc.perform(get("/shoes")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())//
		.andExpect(jsonPath("$[0].brand", is(this.firstTestShoe.getBrand())))
		.andExpect(jsonPath("$[1].brand", is(this.secondTestShoe.getBrand())));
	}
	
	@Test
	public void verifyShoesPostRouteCreatesANewShoe() throws Exception{
		this.postTestShoe  = new Shoe(Long.valueOf(1002), 36, "All-Star", "https://teste.com.br", "Purple", 120.30, "USA", "Sandal", Beak.ROUND, "Syntetic");
		mvc.perform(post("/shoes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.asJsonString(this.postTestShoe)))
		.andExpect(status().isCreated());
		
		mvc.perform(get("/shoes/" + this.postTestShoe.getId())//
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].size", is(this.postTestShoe.getSize())))
				.andExpect(jsonPath("$[0].brand", is(this.postTestShoe.getBrand())))
				.andExpect(jsonPath("$[0].imageUrl", is(this.postTestShoe.getImageUrl())))
				.andExpect(jsonPath("$[0].color", is(this.postTestShoe.getColor())))
				.andExpect(jsonPath("$[0].price", is(this.postTestShoe.getPrice())))
				.andExpect(jsonPath("$[0].madeIn", is(this.postTestShoe.getMadeIn())))
				.andExpect(jsonPath("$[0].shoesType", is(this.postTestShoe.getShoesType())))
				.andExpect(jsonPath("$[0].beak", is(this.postTestShoe.getBeak())))
				.andExpect(jsonPath("$[0].material", is(this.postTestShoe.getMaterial())));		
	}
	

}
