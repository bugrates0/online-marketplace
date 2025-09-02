package com.bugrates.online_marketplace_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bugrates.online_marketplace_app.model.dto.request.NewAdminRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewStaffDepartmentRequest;
import com.bugrates.online_marketplace_app.service.StaffDepartmentService;
import com.bugrates.online_marketplace_app.service.UserService;

@Component
public class FirstInitializations implements CommandLineRunner{

	
	private UserService userService;
	private StaffDepartmentService staffDepartmentService;
	private static final Logger logger = LoggerFactory.getLogger(FirstInitializations.class);
	
	
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
			logger.info("Initialized first department");
			initFirstAdmin();
			logger.info("Initialized first admin");
		} catch (Exception e) { //TODO
			logger.error("Error initializing first admin or department: {}", e.getMessage());
		}
		
		
	}

}
