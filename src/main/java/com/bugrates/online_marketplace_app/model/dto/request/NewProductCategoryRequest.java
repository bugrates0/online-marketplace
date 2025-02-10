package com.bugrates.online_marketplace_app.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewProductCategoryRequest {

	@Pattern(regexp = "^[A-ZÇÖŞÜĞİ][a-zçöşüğı]*$", message = "The first letter should be capitalized, all others should be lowercases")
	@NotBlank(message = "Enter a valid product category name")
	private String productCategoryName;

	public NewProductCategoryRequest() {
		// TODO Auto-generated constructor stub
	}

	public NewProductCategoryRequest(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

}
