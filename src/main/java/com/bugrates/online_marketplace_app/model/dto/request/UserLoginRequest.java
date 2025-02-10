package com.bugrates.online_marketplace_app.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserLoginRequest {

	@Email(message = "Enter a valid email address")
	@NotBlank(message = "E-Mail address is mandatory")
	private String eMailAddress;
	
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	public UserLoginRequest() {
		// TODO Auto-generated constructor stub
	}

	public UserLoginRequest(
			@Email(message = "Enter a valid email address") @NotBlank(message = "E-Mail address is mandatory") String eMailAddress,
			@NotBlank(message = "Password is mandatory") String password) {
		this.eMailAddress = eMailAddress;
		this.password = password;
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
	
	
	
	

}
