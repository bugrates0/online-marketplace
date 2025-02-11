package com.bugrates.online_marketplace_app.model.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewProductRequest {

	@Pattern(regexp = "^([A-Z][a-z]*(\\s[A-Z][a-z]*){0,4})$", message = "The first letters should be capitalized")
	@NotBlank(message = "Enter a valid product name")
	private String productName;

	public NewProductRequest() {
		// TODO Auto-generated constructor stub
	}

	public NewProductRequest(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
