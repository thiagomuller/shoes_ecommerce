package com.thiagomuller.shoesEcommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thiagomuller.shoesEcommerce.models.Shoe;
import com.thiagomuller.shoesEcommerce.service.ShoesService;

@RestController
@RequestMapping("/shoes")
public class ShoesController {
	
	@Autowired
	private ShoesService service;
	
	@GetMapping()
	public Iterable<Shoe> findAll(){
		return service.getAllShoes();
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Shoe> findById(@PathVariable("id") Long id) {
		return service.findShoeById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Shoe create(@RequestBody Shoe resource) {
		return service.save(resource);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Shoe update(@RequestBody Shoe resource) {
		return service.save(resource);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.deleteShoe(id);
	}
	
}
