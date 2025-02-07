package com.bugrates.online_marketplace_app.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewStaffDepartmentRequest {
	
	@Pattern(regexp = "^[A-ZÇÖŞÜĞİ][a-zçöşüğı]*$", message = "The first letter should be capitalized, all others should be lowercases")
	@NotBlank(message = "Enter a valid department name")
	private String departmentName;
	
	public NewStaffDepartmentRequest() {
		// TODO Auto-generated constructor stub
	}

	public NewStaffDepartmentRequest(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	
	
}
