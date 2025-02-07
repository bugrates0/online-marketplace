package com.bugrates.online_marketplace_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="admins")
public class Admin extends User{

	@ManyToOne
    @JoinColumn(name="department_id")
	private StaffDepartment staffDepartment;

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public Admin(StaffDepartment staffDepartment) {
		super();
		this.staffDepartment = staffDepartment;
	}

	public StaffDepartment getStaffDepartment() {
		return staffDepartment;
	}

	public void setStaffDepartment(StaffDepartment staffDepartment) {
		this.staffDepartment = staffDepartment;
	}
	
	
	
	
	
}
