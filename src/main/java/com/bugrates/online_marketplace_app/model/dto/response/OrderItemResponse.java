package com.bugrates.online_marketplace_app.model.dto.response;

public class OrderItemResponse {

	private int orderItemId;
	
	private int quantity;
	
	private int unitPrice;
	
	private int listedProductId;
	
	private String brand;
	
	private String productName;
	
	private int sellerId;
	
	private String storeName;
	
	public OrderItemResponse() {
		// TODO Auto-generated constructor stub
	}

	public OrderItemResponse(int orderItemId, int quantity, int unitPrice, int listedProductId, String brand,
			String productName, int sellerId, String storeName) {
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.listedProductId = listedProductId;
		this.brand = brand;
		this.productName = productName;
		this.sellerId = sellerId;
		this.storeName = storeName;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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
	
	
	
}
