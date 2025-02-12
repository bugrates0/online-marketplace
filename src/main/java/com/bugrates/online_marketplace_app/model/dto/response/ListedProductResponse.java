package com.bugrates.online_marketplace_app.model.dto.response;

public class ListedProductResponse {

	private int listedProductId;

	private String brand;

	private String description;

	private int price;

	private int stockQuantity;

	public ListedProductResponse() {
		// TODO Auto-generated constructor stub
	}

	public ListedProductResponse(int listedProductId, String brand, String description, int price, int stockQuantity) {
		this.listedProductId = listedProductId;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public int getListedProductId() {
		return listedProductId;
	}

	public void setListedProductId(int listedProductId) {
		this.listedProductId = listedProductId;
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
