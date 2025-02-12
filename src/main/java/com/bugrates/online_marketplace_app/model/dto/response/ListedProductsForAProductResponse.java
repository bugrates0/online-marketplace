package com.bugrates.online_marketplace_app.model.dto.response;

import java.util.List;

public class ListedProductsForAProductResponse {

	private int productId;

	private String productName;

	private List<ListedProductResponse> listedProducts;

	public ListedProductsForAProductResponse() {
		// TODO Auto-generated constructor stub
	}

	public ListedProductsForAProductResponse(int productId, String productName,
			List<ListedProductResponse> listedProducts) {
		this.productId = productId;
		this.productName = productName;
		this.listedProducts = listedProducts;
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

	public List<ListedProductResponse> getListedProducts() {
		return listedProducts;
	}

	public void setListedProducts(List<ListedProductResponse> listedProducts) {
		this.listedProducts = listedProducts;
	}

}
