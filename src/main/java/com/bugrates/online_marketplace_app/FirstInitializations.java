package com.bugrates.online_marketplace_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bugrates.online_marketplace_app.model.dto.request.NewAdminRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewStaffDepartmentRequest;
import com.bugrates.online_marketplace_app.model.entity.StaffDepartment;
import com.bugrates.online_marketplace_app.service.StaffDepartmentService;
import com.bugrates.online_marketplace_app.service.UserService;

@Component
public class FirstInitializations implements CommandLineRunner{

	
	private UserService userService;
	private StaffDepartmentService staffDepartmentService;
	
	
	public FirstInitializations(UserService userService, StaffDepartmentService staffDepartmentService) {
		this.userService = userService;
		this.staffDepartmentService = staffDepartmentService;
	}
	
	private void initFirstDepartment() throws Exception {
		
		NewStaffDepartmentRequest newStaffDepartmentRequest = new NewStaffDepartmentRequest();
		
		newStaffDepartmentRequest.setDepartmentName("Founder");

		staffDepartmentService.createNewDepartment(newStaffDepartmentRequest);
		
	}
	
	private void initFirstAdmin() throws Exception {
		NewAdminRequest newAdminRequest = new NewAdminRequest();
		
		newAdminRequest.setDepartmentId(1);
		newAdminRequest.seteMailAddress("admin@marketplace.com");
		newAdminRequest.setFirstName("admin");
		newAdminRequest.setLastName("admin");
		newAdminRequest.setPassword("admin");
		
		userService.registerNewAdmin(newAdminRequest);
	}
	
	@Override
	public void run(String... args) throws Exception {
		try {
			
			initFirstDepartment();
			
			initFirstAdmin();
			
		} catch (Exception e) { //TODO
			System.out.println(e.getMessage());
		}
		
		
	}

}
