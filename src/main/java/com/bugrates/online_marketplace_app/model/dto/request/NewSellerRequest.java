package com.bugrates.online_marketplace_app.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewSellerRequest {

	@Pattern(regexp = "^[A-ZÇÖŞÜĞİ][a-zçöşüğı]*$", message = "The first letter should be capitalized, all others should be lowercases")
	@NotBlank(message = "First name is mandatory")
	private String firstName;

	@Pattern(regexp = "^[A-ZÇÖŞÜĞİ][a-zçöşüğı]*$", message = "The first letter should be capitalized, all others should be lowercases")
	@NotBlank(message = "First name is mandatory")
	private String lastName;
	
	@Email(message = "Enter a valid email address")
	@NotBlank(message = "E-Mail address is mandatory")
	private String eMailAddress;
	
	@NotBlank(message = "Password is mandatory")
	private String password;		
	
	@Pattern(regexp = "^[A-Za-z0-9\\s&.'-]{3,100}$", message = "Enter a valid store name")
	@NotBlank(message = "Store name is mandatory")
	private String storeName;
	
	@Pattern(regexp = "^05\\d{9}$", message = "Enter phone number starting with 05")
	@NotBlank(message = "Phone number is mandatory")
	private String phoneNumber;
	
	@Pattern(regexp = "^[A-ZÇÖŞÜĞİ][a-zçöşüğı]*$", message = "The first letter should be capitalized, all others should be lowercases")
	@NotBlank(message = "City is mandatory")
	private String city;
	
	@NotBlank(message = "Address is mandatory")
	private String storeAddress;
	
	public NewSellerRequest() {
		
	}

	public NewSellerRequest(String firstName, String lastName, String eMailAddress, String password, String storeName,
			String phoneNumber, String city, String storeAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMailAddress = eMailAddress;
		this.password = password;
		this.storeName = storeName;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.storeAddress = storeAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String geteMailAddress() {
		return eMailAddress;
	}

	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	
	
	
}
