package com.thiagomuller.shoesEcommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.thiagomuller.shoesEcommerce.models.Shoe;

@Repository
public interface ShoesRepository extends CrudRepository<Shoe,Long>{}
