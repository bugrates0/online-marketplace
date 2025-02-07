package com.bugrates.online_marketplace_app.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "staffdepartments")
public class StaffDepartment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int departmentId;

	@Column(nullable = false, unique = true)
	private String departmentName;
	
	@OneToMany(mappedBy = "staffDepartment")
	private List<Admin> admins;

	public StaffDepartment() {

	}

	public StaffDepartment(int departmentId, String departmentName) {
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	
	
}
