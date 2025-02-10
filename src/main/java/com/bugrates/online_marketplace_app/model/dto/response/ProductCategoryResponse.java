package com.bugrates.online_marketplace_app.model.dto.response;

public class ProductCategoryResponse {

	private int productCategoryId;

	private String productCategoryName;

	public ProductCategoryResponse() {
		// TODO Auto-generated constructor stub
	}

	public ProductCategoryResponse(int productCategoryId, String productCategoryName) {
		this.productCategoryId = productCategoryId;
		this.productCategoryName = productCategoryName;
	}

	public int getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

}
