package com.thiagomuller.shoesEcommerce.controllers;

import java.util.List;

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

import com.thiagomuller.shoesEcommerce.models.Shoes;
import com.thiagomuller.shoesEcommerce.service.ShoesService;

@RestController
@RequestMapping("/shoes")
public class ShoesController {
	
	@Autowired
	private ShoesService service;
	
	@GetMapping()
	public List<Shoes> findAll(){
		return service.getAllShoes();
	}
	
	@GetMapping(value = "/{id}")
	public Shoes findById(@PathVariable("id") Long id) {
		return service.getById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long create(@RequestBody Shoes resource) {
		return service.createShoes(resource);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable("id") Long id, @RequestBody Shoes resource) {
		service.updateShoes(resource);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.deleteShoes(id);
	}
	
}
