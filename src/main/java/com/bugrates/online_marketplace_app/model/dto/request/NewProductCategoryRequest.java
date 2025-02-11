package com.bugrates.online_marketplace_app.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewProductCategoryRequest {

	@Pattern(regexp = "^([A-Z][a-z]*(\\s[A-Z][a-z]*){0,4})$", message = "The first letters should be capitalized")
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
