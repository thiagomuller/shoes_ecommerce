package com.thiagomuller.shoesEcommerce.service;

import java.util.List;

import com.thiagomuller.shoesEcommerce.models.Shoes;

public interface ShoesService {
	public Shoes getById(Long id);
	public List<Shoes> getAllShoes();
	public Long createShoes(Shoes shoes);
	public boolean updateShoes(Shoes shoes);
	public boolean deleteShoes(Long id);
}
