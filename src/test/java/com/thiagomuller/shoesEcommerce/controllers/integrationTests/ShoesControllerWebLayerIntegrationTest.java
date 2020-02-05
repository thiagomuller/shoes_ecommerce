package com.thiagomuller.shoesEcommerce.controllers.integrationTests;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.thiagomuller.shoesEcommerce.ShoesEcommerceApplication;
import com.thiagomuller.shoesEcommerce.TestUtils;
import com.thiagomuller.shoesEcommerce.models.Beak;
import com.thiagomuller.shoesEcommerce.models.Shoe;
import com.thiagomuller.shoesEcommerce.service.ShoesService;

@RunWith(SpringRunner.class)
//There's a difference between tutorial and mine here, cuz I'm not passing webEnvironment attribute on the annotation, cuz I coundn't make it work
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = ShoesEcommerceApplication.class,
		properties = ("spring.h2.console.enabled=true"))
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-integrationtest.properties")
public class ShoesControllerWebLayerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ShoesService service;
	
	private Shoe firstTestShoe;
	private Shoe secondTestShoe;
	private Shoe thirdTestShoe;

	@Before
	public void createShoesForTestingOnDatabase(){
		firstTestShoe = new Shoe(Long.valueOf(1),38,"Dafiti","https://teste.com.br","Purple",//
				200.45,"Brazil","Boot",Beak.ROUND,"Syntetic");
		secondTestShoe = new Shoe(Long.valueOf(2),40, "Nike", "https://teste.com.br", "Blue", //
				400.50, "China", "Social", Beak.POINTED, "Syntetic");
		service.save(firstTestShoe);
		service.save(secondTestShoe);
	}

	@After
	public void deleteTestShoesData(){
		service.deleteAllShoes();
	}
	
	@Test()
	public void verifyShoesGetRouteReturnsShoesTestData() throws Exception{
		mvc.perform(get("/shoes/1")
		.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.brand", is("Dafiti")))
				.andExpect(jsonPath("$.size", is(38)))
				.andExpect(jsonPath("$.imageUrl", is("https://teste.com.br")))
				.andExpect(jsonPath("$.color", is("Purple")))
				.andExpect(jsonPath("$.price", is(200.45)))
				.andExpect(jsonPath("$.madeIn", is("Brazil")))
				.andExpect(jsonPath("$.shoesType", is("Boot")))
				.andExpect(jsonPath("$.beak", is("ROUND")))
				.andExpect(jsonPath("$.material", is("Syntetic")));

		mvc.perform(get("/shoes/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.brand", is("Nike")))
				.andExpect(jsonPath("$.size", is(40)))
				.andExpect(jsonPath("$.imageUrl", is("https://teste.com.br")))
				.andExpect(jsonPath("$.color", is("Blue")))
				.andExpect(jsonPath("$.price", is(400.50)))
				.andExpect(jsonPath("$.madeIn", is("China")))
				.andExpect(jsonPath("$.shoesType", is("Social")))
				.andExpect(jsonPath("$.beak", is("POINTED")))
				.andExpect(jsonPath("$.material", is("Syntetic")));
	}
	
	@Test()
	public void verifyShoesGetRouteReturnsShoesList() throws Exception{
		mvc.perform(get("/shoes")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].brand", is("Dafiti")))
				.andExpect(jsonPath("$[0].size", is(38)))
				.andExpect(jsonPath("$[0].imageUrl", is("https://teste.com.br")))
				.andExpect(jsonPath("$[0].color", is("Purple")))
				.andExpect(jsonPath("$[0].price", is(200.45)))
				.andExpect(jsonPath("$[0].madeIn", is("Brazil")))
				.andExpect(jsonPath("$[0].shoesType", is("Boot")))
				.andExpect(jsonPath("$[0].beak", is("ROUND")))
				.andExpect(jsonPath("$[0].material", is("Syntetic")))
				.andExpect(jsonPath("$[1].brand", is("Nike")))
				.andExpect(jsonPath("$[1].size", is(40)))
				.andExpect(jsonPath("$[1].imageUrl", is("https://teste.com.br")))
				.andExpect(jsonPath("$[1].color", is("Blue")))
				.andExpect(jsonPath("$[1].price", is(400.50)))
				.andExpect(jsonPath("$[1].madeIn", is("China")))
				.andExpect(jsonPath("$[1].shoesType", is("Social")))
				.andExpect(jsonPath("$[1].beak", is("POINTED")))
				.andExpect(jsonPath("$[1].material", is("Syntetic")));
	}
	
	@Test
	public void verifyShoesPostRouteCreatesANewShoe() throws Exception{
		thirdTestShoe = new Shoe(Long.valueOf(1),43, "All-Star", "https://teste.com.br", "Black", //
				230.00, "USA", "Casual", Beak.ROUND, "Syntetic");
		mvc.perform(post("/shoes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.asJsonString(thirdTestShoe).getBytes()))
				.andExpect(status().isCreated())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

		mvc.perform(get("/shoes/5")
		.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.brand", is("All-Star")))
				.andExpect(jsonPath("$.size", is(43)))
				.andExpect(jsonPath("$.imageUrl", is("https://teste.com.br")))
				.andExpect(jsonPath("$.color", is("Black")))
				.andExpect(jsonPath("$.price", is(230.00)))
				.andExpect(jsonPath("$.madeIn", is("USA")))
				.andExpect(jsonPath("$.shoesType", is("Casual")))
				.andExpect(jsonPath("$.beak", is("ROUND")))
				.andExpect(jsonPath("$.material", is("Syntetic")));
	}


	

}
