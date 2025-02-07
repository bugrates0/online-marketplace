package com.bugrates.online_marketplace_app.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewAdminRequest {

	@Pattern(regexp = "^[A-ZÇÖŞÜĞİ][a-zçöşüğı]*$", message = "The first letter should be capitalized, all others should be lowercases")
	@NotBlank(message = "First name is mandatory")
	private String firstName;

	@Pattern(regexp = "^[A-ZÇÖŞÜĞİ][a-zçöşüğı]*$", message = "The first letter should be capitalized, all others should be lowercases")
	@NotBlank(message = "Last name is mandatory")
	private String lastName;

	@Email(message = "Enter a valid email address")
	@NotBlank(message = "E-Mail address is mandatory")
	private String eMailAddress;

	@NotBlank(message = "Password is mandatory")
	private String password;

	@Min(value = 1, message = "Department ID must be a positive number")
	private int departmentId;

	public NewAdminRequest() {
		// TODO Auto-generated constructor stub
	}

	public NewAdminRequest(String firstName, String lastName, String eMailAddress, String password, int departmentId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMailAddress = eMailAddress;
		this.password = password;
		this.departmentId = departmentId;
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

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
	
	

}
