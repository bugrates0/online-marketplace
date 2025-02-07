package com.bugrates.online_marketplace_app.controller;



import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bugrates.online_marketplace_app.model.dto.NewAdminRequest;
import com.bugrates.online_marketplace_app.model.dto.NewCustomerRequest;
import com.bugrates.online_marketplace_app.model.dto.NewSellerRequest;
import com.bugrates.online_marketplace_app.model.dto.UserLoginRequest;
import com.bugrates.online_marketplace_app.model.entity.Seller;
import com.bugrates.online_marketplace_app.model.entity.User;
import com.bugrates.online_marketplace_app.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/registerAsCustomer")
	public ResponseEntity<NewCustomerRequest> newCustomer(@Valid @RequestBody NewCustomerRequest newCustomerRequest) throws Exception {

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.registerAsCustomer(newCustomerRequest)); //TODO
	}

	@PostMapping("/registerAsSeller")
	public ResponseEntity<NewSellerRequest> newSeller(@Valid @RequestBody NewSellerRequest newSellerRequest) throws Exception {

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.registerAsSeller(newSellerRequest)); //TODO
	}

	@PostMapping("/registerNewAdmin")
	public ResponseEntity<NewAdminRequest> newAdmin(@Valid @RequestBody NewAdminRequest newAdminRequest) throws Exception {

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.registerNewAdmin(newAdminRequest)); //TODO
	}
	
	
	@PostMapping("/login")
	public String login(@Valid @RequestBody UserLoginRequest userLoginRequest) {

		return userService.verifyUser(userLoginRequest);
	}

}
