package com.bugrates.online_marketplace_app.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewListedProductRequest {

	@Pattern(regexp = "^([A-Z][a-z]*(\\s[A-Z][a-z]*){0,4})$", message = "The first letters should be capitalized")
	@NotBlank(message = "Enter a valid brand")
	private String brand;
	
	@NotBlank(message = "Enter a valid description")
	private String description;
	
	@Min(1)
	private int price;
	
	@Min(10)
	private int stockQuantity;
	
	public NewListedProductRequest() {
		// TODO Auto-generated constructor stub
	}

	public NewListedProductRequest(String brand, String description, int price, int stockQuantity) {
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	
	
}
