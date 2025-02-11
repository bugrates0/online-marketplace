package com.bugrates.online_marketplace_app.model.dto.response;

import java.util.List;

public class ProductsOfCategoryResponse {

	private int productCategoryId;
	
	private String productCategoryName;
	
	private List<ProductResponse> products;
	
	public ProductsOfCategoryResponse() {
		// TODO Auto-generated constructor stub
	}

	public ProductsOfCategoryResponse(int productCategoryId, String productCategoryName,
			List<ProductResponse> products) {
		this.productCategoryId = productCategoryId;
		this.productCategoryName = productCategoryName;
		this.products = products;
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

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}
	
	
	
}
