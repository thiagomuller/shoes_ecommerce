package com.thiagomuller.shoesEcommerce.service;

import java.util.Optional;

import com.thiagomuller.shoesEcommerce.models.Shoe;


public interface ShoesService {
	public Optional<Shoe> findShoeById(Long id);
	public Iterable<Shoe> getAllShoes();
	public Shoe save(Shoe shoe);
	public void deleteShoe(Long id);
}
