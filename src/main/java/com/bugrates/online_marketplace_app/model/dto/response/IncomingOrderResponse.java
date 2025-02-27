package com.bugrates.online_marketplace_app.model.dto.response;

import java.time.LocalDateTime;

public class IncomingOrderResponse {

	private int orderItemId;
	
	private int quantity;
	
	private int unitPrice;
	
	private LocalDateTime orderedAt;
	
	private int customerId;
	
	private String customerName;
	
	private String customerMailAddress;
	
	private String customerPhoneNumber;
	
	private String customerCity;
	
	private String customerAddress;
	
	private String productName;
	
	private String brand;
	
	public IncomingOrderResponse() {
		// TODO Auto-generated constructor stub
	}

	public IncomingOrderResponse(int orderItemId, int quantity, int unitPrice, LocalDateTime orderedAt, int customerId,
			String customerName, String customerMailAddress, String customerPhoneNumber, String customerCity,
			String customerAddress, String productName, String brand) {
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.orderedAt = orderedAt;
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerMailAddress = customerMailAddress;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerCity = customerCity;
		this.customerAddress = customerAddress;
		this.productName = productName;
		this.brand = brand;
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

	public LocalDateTime getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(LocalDateTime orderedAt) {
		this.orderedAt = orderedAt;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMailAddress() {
		return customerMailAddress;
	}

	public void setCustomerMailAddress(String customerMailAddress) {
		this.customerMailAddress = customerMailAddress;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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
	
	
	
}
