package com.bugrates.online_marketplace_app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.model.entity.Admin;
import com.bugrates.online_marketplace_app.model.entity.Customer;
import com.bugrates.online_marketplace_app.model.entity.Seller;
import com.bugrates.online_marketplace_app.model.entity.User;
import com.bugrates.online_marketplace_app.repo.AdminRepository;
import com.bugrates.online_marketplace_app.repo.CustomerRepository;
import com.bugrates.online_marketplace_app.repo.SellerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private CustomerRepository customerRepository;
	private SellerRepository sellerRepository;
	private AdminRepository adminRepository;
	
	public MyUserDetailsService(CustomerRepository customerRepository, SellerRepository sellerRepository, AdminRepository adminRepository) {
		this.customerRepository = customerRepository;
		this.sellerRepository = sellerRepository;
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String eMailAddress) throws UsernameNotFoundException { //TODO better logging
		
		Customer customer = customerRepository.findByEMailAddress(eMailAddress);

		if(customer == null) {
			System.out.println("Customer not found.");
		}else {
			return customer;
		}
		
		Seller seller = sellerRepository.findByEMailAddress(eMailAddress);

		if(seller == null) {
			System.out.println("Seller not found.");
		}else {
			return seller;
		}
		
		Admin admin = adminRepository.findByEMailAddress(eMailAddress);
		
		if(admin == null) {
			System.out.println("Admin not found");
		}else {
			return admin;
		}
		
		
		throw new UsernameNotFoundException("User not found.");
		
	}

}
