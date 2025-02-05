package com.bugrates.online_marketplace_app;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.model.Customer;
import com.bugrates.online_marketplace_app.model.Seller;
import com.bugrates.online_marketplace_app.model.User;
import com.bugrates.online_marketplace_app.repo.CustomerRepository;
import com.bugrates.online_marketplace_app.repo.SellerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private CustomerRepository customerRepository;
	private SellerRepository sellerRepository;
	
	public MyUserDetailsService(CustomerRepository customerRepository, SellerRepository sellerRepository) {
		this.customerRepository = customerRepository;
		this.sellerRepository = sellerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String eMailAddress) throws UsernameNotFoundException {
		
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
		
		throw new UsernameNotFoundException("User not found.");
		
	}

}
