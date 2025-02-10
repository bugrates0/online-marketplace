package com.bugrates.online_marketplace_app.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.model.dto.request.NewAdminRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewCustomerRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewSellerRequest;
import com.bugrates.online_marketplace_app.model.dto.request.UserLoginRequest;
import com.bugrates.online_marketplace_app.model.entity.Admin;
import com.bugrates.online_marketplace_app.model.entity.Customer;
import com.bugrates.online_marketplace_app.model.entity.Seller;
import com.bugrates.online_marketplace_app.model.entity.StaffDepartment;
import com.bugrates.online_marketplace_app.model.entity.User;
import com.bugrates.online_marketplace_app.model.enums.Role;
import com.bugrates.online_marketplace_app.repo.AdminRepository;
import com.bugrates.online_marketplace_app.repo.CustomerRepository;
import com.bugrates.online_marketplace_app.repo.SellerRepository;
import com.bugrates.online_marketplace_app.repo.StaffDepartmentRepository;

@Service
public class UserService {

	private CustomerRepository customerRepository;
	private SellerRepository sellerRepository;
	private AdminRepository adminRepository;
	private StaffDepartmentRepository staffDepartmentRepository;
	private AuthenticationManager authenticationManager;
	private JWTService jwtService;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public UserService(CustomerRepository customerRepository, SellerRepository sellerRepository, AuthenticationManager authenticationManager, JWTService jwtService, AdminRepository adminRepository, StaffDepartmentRepository staffDepartmentRepository) {
		this.customerRepository = customerRepository;
		this.sellerRepository = sellerRepository;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.adminRepository = adminRepository;
		this.staffDepartmentRepository = staffDepartmentRepository;
	}

	public NewCustomerRequest registerAsCustomer(NewCustomerRequest newCustomerRequest) throws Exception {

		Customer newCustomer = new Customer();

		newCustomer.setAddress(newCustomerRequest.getAddress());
		newCustomer.setCity(newCustomerRequest.getCity());
		newCustomer.setCreatedAt(LocalDateTime.now());
		newCustomer.seteMailAddress(newCustomerRequest.geteMailAddress());
		newCustomer.setFirstName(newCustomerRequest.getFirstName());
		newCustomer.setLastName(newCustomerRequest.getLastName());
		newCustomer.setPassword(encoder.encode(newCustomerRequest.getPassword()));
		newCustomer.setPhoneNumber(newCustomerRequest.getPhoneNumber());
		newCustomer.setRole(Role.CUSTOMER);

		try {
			customerRepository.save(newCustomer);
			return newCustomerRequest;
		} catch (Exception e) {
			throw new Exception(e.getMessage()); //TODO
		}

	}

	public NewSellerRequest registerAsSeller(NewSellerRequest newSellerRequest) throws Exception {

		Seller newSeller = new Seller();

		newSeller.setCity(newSellerRequest.getCity());
		newSeller.setCreatedAt(LocalDateTime.now());
		newSeller.seteMailAddress(newSellerRequest.geteMailAddress());
		newSeller.setFirstName(newSellerRequest.getFirstName());
		newSeller.setLastName(newSellerRequest.getLastName());
		newSeller.setPassword(encoder.encode(newSellerRequest.getPassword()));
		newSeller.setPhoneNumber(newSellerRequest.getPhoneNumber());
		newSeller.setRole(Role.SELLER);
		newSeller.setStoreAddress(newSellerRequest.getStoreAddress());
		newSeller.setStoreName(newSellerRequest.getStoreName());

		try {
			sellerRepository.save(newSeller);
			return newSellerRequest;
		} catch (Exception e) {
			throw new Exception(e.getMessage()); //TODO
		}

	}
	
	public NewAdminRequest registerNewAdmin(NewAdminRequest newAdminRequest) throws Exception {
		
		StaffDepartment department = staffDepartmentRepository.findById(newAdminRequest.getDepartmentId()).orElseThrow(() -> new Exception("Department not found")); //TODO
		
		Admin newAdmin = new Admin();
		
		newAdmin.setCreatedAt(LocalDateTime.now());
		newAdmin.seteMailAddress(newAdminRequest.geteMailAddress());
		newAdmin.setFirstName(newAdminRequest.getFirstName());
		newAdmin.setLastName(newAdminRequest.getLastName());
		newAdmin.setPassword(encoder.encode(newAdminRequest.getPassword()));
		newAdmin.setRole(Role.ADMIN);
		newAdmin.setStaffDepartment(department);
		
		try {
			adminRepository.save(newAdmin);
			return newAdminRequest;
		} catch (Exception e) {
			throw new Exception(e.getMessage()); //TODO
		}
		
	}

	public String verifyUser(UserLoginRequest userLoginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.geteMailAddress(), userLoginRequest.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(userLoginRequest.geteMailAddress());
		}
		
		
		
		return "fail";
	}

}
