package com.bugrates.online_marketplace_app.model.dto.response;

public class ProductResponse {

	private int productId;

	private String productName;

	public ProductResponse() {
		// TODO Auto-generated constructor stub
	}

	public ProductResponse(int productId, String productName) {
		this.productId = productId;
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
