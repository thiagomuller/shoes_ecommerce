package com.thiagomuller.shoesEcommerce.models;

import javax.persistence.Entity;

@Entity
public class Shoes {
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
	
	
	public Shoes() {}


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
