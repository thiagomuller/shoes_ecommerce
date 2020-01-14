package com.thiagomuller.shoesEcommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thiagomuller.shoesEcommerce.models.Shoes;
import com.thiagomuller.shoesEcommerce.repositories.ShoesRepository;
import com.thiagomuller.shoesEcommerce.service.ShoesService;

public class ShoesServiceImpl implements ShoesService{
	
	@Autowired
	private ShoesRepository shoesRepository;

	@Override
	public Shoes getById(Long id) {
		return shoesRepository.findById(id);
	}

	@Override
	public List<Shoes> getAllShoes() {
		return shoesRepository.findAll();
	}

	@Override
	public Long createShoes(Shoes shoes) {
		return shoesRepository.insertShoes(shoes);
	}

	@Override
	public boolean updateShoes(Shoes shoes) {
		return shoesRepository.updateShoes(shoes);
	}

	@Override
	public boolean deleteShoes(Long id) {
		return shoesRepository.deleteShoes(id);
	}

}
