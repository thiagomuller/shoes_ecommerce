package com.thiagomuller.shoesEcommerce.controllers.unitTests;


import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.thiagomuller.shoesEcommerce.TestUtils;
import com.thiagomuller.shoesEcommerce.controllers.ShoesController;
import com.thiagomuller.shoesEcommerce.models.Beak;
import com.thiagomuller.shoesEcommerce.models.Shoe;
import com.thiagomuller.shoesEcommerce.service.ShoesService;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ShoesController.class)
public class ShoeControllerUnitTests {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ShoesService service;
	
	@Test
	public void verifyIfGetRouteReturnsAllShoes() throws Exception{
		Shoe firstShoe = new Shoe(Long.valueOf(1),38,"Dafiti","https://teste.com.br","Purple",200.45,"Brazil","Boot",Beak.ROUND,"Syntetic");
		Shoe secondShoe = new Shoe(Long.valueOf(2),40, "Nike", "https://teste.com.br", "Blue", 400.50, "China", "Social", Beak.POINTED, "Syntetic");
		
		List<Shoe> shoes = new ArrayList<>();
		shoes.add(firstShoe);
		shoes.add(secondShoe);
		
		given(service.getAllShoes()).willReturn(shoes);
		
		mvc.perform(get("/shoes")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].brand", is(firstShoe.getBrand())))
		.andExpect(jsonPath("$[1].brand", is(secondShoe.getBrand())));
	}
	
	@Test
	public void verifyIfGetRouteReturnsSpecificShoeForGivenId() throws Exception{
		Optional<Shoe> firstShoe = Optional.of(new Shoe(Long.valueOf(1),38,"Dafiti","https://teste.com.br","Purple",200.45,"Brazil","Boot",Beak.ROUND,"Syntetic"));
		
		given(service.findShoeById(Long.valueOf(1))).willReturn(firstShoe);
		
		mvc.perform(get("/shoes/" + firstShoe.get().getId())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.brand", is(firstShoe.get().getBrand())));
		
		
	}

	@Test
	public void verifyIfPostRouteCreatesAShoeWithGivenInformation() throws Exception{
		Optional<Shoe> firstShoe = Optional.of(new Shoe(Long.valueOf(1),38,"Dafiti","https://teste.com.br",//
				"Purple",200.45,"Brazil","Boot",Beak.ROUND,"Syntetic"));
		given(service.save(firstShoe.get())).willReturn(firstShoe.get());
		String json = TestUtils.asJsonString(firstShoe.get());
		mvc.perform(post("/shoes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.characterEncoding("utf-8"))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.brand", is(firstShoe.get().getBrand())));
	}

	@Test
	public void verifyIfUpdateRouteUpdatesShoesWithGivenInformation() throws Exception{
		Optional<Shoe> updatedShoe = Optional.of(new Shoe(Long.valueOf(1),42,"Nike","https://teste.com.br",//
				"Purple",200.45,"Brazil","Boot",Beak.ROUND,"Syntetic"));
		given(service.save(updatedShoe.get())).willReturn(updatedShoe.get());		
		mvc.perform(put("/shoes/" + updatedShoe.get().getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.asJsonString(updatedShoe.get()))
				.characterEncoding("utf-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.brand", is(updatedShoe.get().getBrand())));
	}
}
