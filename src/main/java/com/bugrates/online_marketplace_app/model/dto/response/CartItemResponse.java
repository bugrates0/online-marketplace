package com.bugrates.online_marketplace_app.model.dto.response;

public class CartItemResponse {

	private int cartItemId;
	
	private int listedProductId;
	
	private int sellerId;
	
	private String storeName;
	
	private String productName;
	
	private String brand;
	
	private int quantity;
	
	private int totalPrice;
	
	
	public CartItemResponse() {
		// TODO Auto-generated constructor stub
	}


	public CartItemResponse(int cartItemId, int listedProductId, int sellerId, String storeName, String productName,
			String brand, int quantity, int totalPrice) {
		this.cartItemId = cartItemId;
		this.listedProductId = listedProductId;
		this.sellerId = sellerId;
		this.storeName = storeName;
		this.productName = productName;
		this.brand = brand;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}


	public int getCartItemId() {
		return cartItemId;
	}


	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}


	public int getListedProductId() {
		return listedProductId;
	}


	public void setListedProductId(int listedProductId) {
		this.listedProductId = listedProductId;
	}


	public int getSellerId() {
		return sellerId;
	}


	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
