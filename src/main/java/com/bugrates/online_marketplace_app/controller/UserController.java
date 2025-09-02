package com.bugrates.online_marketplace_app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugrates.online_marketplace_app.model.dto.request.NewAdminRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewCustomerRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewSellerRequest;
import com.bugrates.online_marketplace_app.model.dto.request.UserLoginRequest;
import com.bugrates.online_marketplace_app.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1/authentication")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register/customer")
	public ResponseEntity<NewCustomerRequest> newCustomer(@Valid @RequestBody NewCustomerRequest newCustomerRequest) throws Exception {

		logger.info("Received customer registration request for email: {}", newCustomerRequest.geteMailAddress());
		try {
			NewCustomerRequest response = userService.registerAsCustomer(newCustomerRequest);
			logger.info("Customer registration successful for email: {}", newCustomerRequest.geteMailAddress());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		} catch (Exception e) {
			logger.error("Customer registration failed for email {}: {}", newCustomerRequest.geteMailAddress(), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/register/seller")
	public ResponseEntity<NewSellerRequest> newSeller(@Valid @RequestBody NewSellerRequest newSellerRequest) throws Exception {

		logger.info("Received seller registration request for email: {}", newSellerRequest.geteMailAddress());
		try {
			NewSellerRequest response = userService.registerAsSeller(newSellerRequest);
			logger.info("Seller registration successful for email: {}", newSellerRequest.geteMailAddress());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		} catch (Exception e) {
			logger.error("Seller registration failed for email {}: {}", newSellerRequest.geteMailAddress(), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/register/admin")
	public ResponseEntity<NewAdminRequest> newAdmin(@Valid @RequestBody NewAdminRequest newAdminRequest) throws Exception {

		logger.info("Received admin registration request for email: {}", newAdminRequest.geteMailAddress());
		try {
			NewAdminRequest response = userService.registerNewAdmin(newAdminRequest);
			logger.info("Admin registration successful for email: {}", newAdminRequest.geteMailAddress());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		} catch (Exception e) {
			logger.error("Admin registration failed for email {}: {}", newAdminRequest.geteMailAddress(), e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@PostMapping("/login")
	public String login(@Valid @RequestBody UserLoginRequest userLoginRequest) throws Exception {

		logger.info("Received login request for email: {}", userLoginRequest.geteMailAddress());
		try {
			String token = userService.verifyUser(userLoginRequest);
			if (!"fail".equals(token)) {
				logger.info("Login successful for email: {}", userLoginRequest.geteMailAddress());
			} else {
				logger.warn("Login failed for email: {}", userLoginRequest.geteMailAddress());
			}
			return token;
		} catch (Exception e) {
			logger.error("Login error for email {}: {}", userLoginRequest.geteMailAddress(), e.getMessage());
			throw e;
		}
	}

}
