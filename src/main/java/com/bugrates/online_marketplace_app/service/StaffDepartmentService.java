package com.bugrates.online_marketplace_app.service;

import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.model.dto.request.NewStaffDepartmentRequest;
import com.bugrates.online_marketplace_app.model.entity.StaffDepartment;
import com.bugrates.online_marketplace_app.repo.StaffDepartmentRepository;

@Service
public class StaffDepartmentService {

	private StaffDepartmentRepository staffDepartmentRepository;
	
	public StaffDepartmentService(StaffDepartmentRepository staffDepartmentRepository) {
		this.staffDepartmentRepository = staffDepartmentRepository;
	}
	
	public NewStaffDepartmentRequest createNewDepartment(NewStaffDepartmentRequest newStaffDepartmentRequest) throws Exception {
		
		StaffDepartment newStaffDepartment = new StaffDepartment();
		
		newStaffDepartment.setDepartmentName(newStaffDepartmentRequest.getDepartmentName());
		
		try {
			staffDepartmentRepository.save(newStaffDepartment);
			return newStaffDepartmentRequest;
		} catch (Exception e) {
			throw new Exception(e.getMessage()); //TODO
		}
		
	}
	
	/*
	
	public StaffDepartment findById(int departmentId) throws Exception {
		
		StaffDepartment department = staffDepartmentRepository.findById(departmentId).orElseThrow(() -> new Exception("Department not found"));
		
		return department;
	}
	
	*/
}
