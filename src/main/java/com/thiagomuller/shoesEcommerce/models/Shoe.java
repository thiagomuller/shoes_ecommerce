package com.thiagomuller.shoesEcommerce.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="SHOES")
public class Shoe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private int size;
	private String brand;
	private String imageUrl;
	private String color;
	private double price;
	private String madeIn;
	private String shoesType;
	private Beak beak;
	private String material;
	
	
	protected Shoe() {}
	
	public Shoe(Long id, int size, String brand, String imageUrl, String color, double price, String madeIn, String shoesType, Beak beak, String material) {
		this.size = size;
		this.brand = brand;
		this.imageUrl = imageUrl;
		this.color = color;
		this.price = price;
		this.madeIn = madeIn;
		this.shoesType = shoesType;
		this.beak = beak;
		this.material = material;
	}


	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getMadeIn() {
		return madeIn;
	}


	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}


	public String getShoesType() {
		return shoesType;
	}


	public void setShoesType(String shoesType) {
		this.shoesType = shoesType;
	}


	public Beak getBeak() {
		return beak;
	}


	public void setBeak(Beak beak) {
		this.beak = beak;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}		
}
