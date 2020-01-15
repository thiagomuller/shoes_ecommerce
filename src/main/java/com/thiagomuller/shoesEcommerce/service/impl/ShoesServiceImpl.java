package com.thiagomuller.shoesEcommerce.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.thiagomuller.shoesEcommerce.models.Shoe;
import com.thiagomuller.shoesEcommerce.repositories.ShoesRepository;
import com.thiagomuller.shoesEcommerce.service.ShoesService;

@Service
public class ShoesServiceImpl implements ShoesService{
	
	@Autowired
	private ShoesRepository shoesRepository;

	@Override
	public Optional<Shoe> getById(Long id) {
		return shoesRepository.findById(id);
	}

	@Override
	public Iterable<Shoe> getAllShoes() {
		return shoesRepository.findAll();
	}

	@Override
	public Long createShoe(Shoe shoe) {
		return shoesRepository.save(shoe).getId();
	}

	@Override
	public Shoe updateShoe(Shoe shoe) {
		return shoesRepository.save(shoe);
	}

	@Override
	public void deleteShoe(Long id) {
		shoesRepository.deleteById(id);;
	}

}
