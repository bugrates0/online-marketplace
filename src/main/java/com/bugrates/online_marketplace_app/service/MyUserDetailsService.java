package com.bugrates.online_marketplace_app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.model.entity.Admin;
import com.bugrates.online_marketplace_app.model.entity.Customer;
import com.bugrates.online_marketplace_app.model.entity.Seller;
import com.bugrates.online_marketplace_app.repo.AdminRepository;
import com.bugrates.online_marketplace_app.repo.CustomerRepository;
import com.bugrates.online_marketplace_app.repo.SellerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	private CustomerRepository customerRepository;
	private SellerRepository sellerRepository;
	private AdminRepository adminRepository;
	
	public MyUserDetailsService(CustomerRepository customerRepository, SellerRepository sellerRepository, AdminRepository adminRepository) {
		this.customerRepository = customerRepository;
		this.sellerRepository = sellerRepository;
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String eMailAddress) throws UsernameNotFoundException {
		
		logger.debug("Loading user details for email: {}", eMailAddress);
		
		Customer customer = customerRepository.findByEMailAddress(eMailAddress);

		if(customer == null) {
			logger.debug("Customer not found for email: {}", eMailAddress);
		}else {
			logger.debug("Found customer for email: {}", eMailAddress);
			return customer;
		}
		
		Seller seller = sellerRepository.findByEMailAddress(eMailAddress);

		if(seller == null) {
			logger.debug("Seller not found for email: {}", eMailAddress);
		}else {
			logger.debug("Found seller for email: {}", eMailAddress);
			return seller;
		}
		
		Admin admin = adminRepository.findByEMailAddress(eMailAddress);
		
		if(admin == null) {
			logger.debug("Admin not found for email: {}", eMailAddress);
		}else {
			logger.debug("Found admin for email: {}", eMailAddress);
			return admin;
		}
		
		logger.error("No user found with email: {}", eMailAddress);
		throw new UsernameNotFoundException("User not found.");
		
	}

}
