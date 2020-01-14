package com.thiagomuller.shoesEcommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thiagomuller.shoesEcommerce.models.Shoes;

public interface ShoesRepository extends JpaRepository<Shoes,Integer>{
	public Shoes findById(long Id);	
	public List<Shoes> findAll();
	public Long insertShoes(Shoes shoes);
	public boolean deleteShoes(Long id);
	public boolean updateShoes(Shoes shoes);
}
