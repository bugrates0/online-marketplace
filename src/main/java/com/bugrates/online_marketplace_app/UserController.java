package com.bugrates.online_marketplace_app;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bugrates.online_marketplace_app.model.Customer;
import com.bugrates.online_marketplace_app.model.Role;
import com.bugrates.online_marketplace_app.model.Seller;
import com.bugrates.online_marketplace_app.model.User;
import com.bugrates.online_marketplace_app.repo.CustomerRepository;
import com.bugrates.online_marketplace_app.repo.SellerRepository;

@RestController
public class UserController {
	
	private CustomerRepository customerRepository;
	private SellerRepository sellerRepository;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public UserController(CustomerRepository customerRepository, SellerRepository sellerRepository) {
		this.customerRepository = customerRepository;
		this.sellerRepository = sellerRepository;
	}
	
	@PostMapping("/registerAsCustomer")
	public Customer newCustomer(@RequestBody Customer customer) {
		
		customer.setCreatedAt(LocalDateTime.now());
		customer.setRole(Role.CUSTOMER);
		customer.setPassword(encoder.encode(customer.getPassword()));
		
		customerRepository.save(customer);
		return customer;
	}
	
	@PostMapping("/registerAsSeller")
	public Seller newSeller(@RequestBody Seller seller) {
		
		seller.setCreatedAt(LocalDateTime.now());
		seller.setRole(Role.SELLER);
		seller.setPassword(encoder.encode(seller.getPassword()));
		
		sellerRepository.save(seller);
		return seller;
	}
	
	
}
