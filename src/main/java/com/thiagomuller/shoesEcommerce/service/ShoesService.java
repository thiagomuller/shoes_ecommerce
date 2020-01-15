package com.thiagomuller.shoesEcommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thiagomuller.shoesEcommerce.models.Shoe;


public interface ShoesService {
	public Optional<Shoe> getById(Long id);
	public Iterable<Shoe> getAllShoes();
	public Long createShoe(Shoe shoes);
	public Shoe updateShoe(Shoe shoes);
	public void deleteShoe(Long id);
}
