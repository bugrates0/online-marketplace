package com.bugrates.online_marketplace_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerExample {
	
	@GetMapping("/helloCustomer")
	String sayHelloc() {
		return "Hello Customer";
	}
	
	
	@GetMapping("/helloSeller")
	String sayHellos() {
		return "Hello Seller";
	}
	

}
