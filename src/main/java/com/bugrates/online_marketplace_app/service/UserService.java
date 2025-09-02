package com.bugrates.online_marketplace_app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.factory.UserFactory;
import com.bugrates.online_marketplace_app.model.dto.request.NewAdminRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewCustomerRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewSellerRequest;
import com.bugrates.online_marketplace_app.model.dto.request.UserLoginRequest;
import com.bugrates.online_marketplace_app.model.entity.Admin;
import com.bugrates.online_marketplace_app.model.entity.Customer;
import com.bugrates.online_marketplace_app.model.entity.Seller;
import com.bugrates.online_marketplace_app.model.entity.StaffDepartment;
import com.bugrates.online_marketplace_app.model.entity.User;
import com.bugrates.online_marketplace_app.repo.AdminRepository;
import com.bugrates.online_marketplace_app.repo.CustomerRepository;
import com.bugrates.online_marketplace_app.repo.SellerRepository;
import com.bugrates.online_marketplace_app.repo.StaffDepartmentRepository;
import com.bugrates.online_marketplace_app.repo.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private CustomerRepository customerRepository;
	private SellerRepository sellerRepository;
	private AdminRepository adminRepository;
	private UserRepository userRepository;
	private StaffDepartmentRepository staffDepartmentRepository;
	private AuthenticationManager authenticationManager;
	private JWTService jwtService;

	public UserService(CustomerRepository customerRepository, SellerRepository sellerRepository,
			AuthenticationManager authenticationManager, JWTService jwtService, AdminRepository adminRepository,
			StaffDepartmentRepository staffDepartmentRepository, UserRepository userRepository) {
		this.customerRepository = customerRepository;
		this.sellerRepository = sellerRepository;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.adminRepository = adminRepository;
		this.staffDepartmentRepository = staffDepartmentRepository;
		this.userRepository = userRepository;
	}

	public NewCustomerRequest registerAsCustomer(NewCustomerRequest newCustomerRequest) throws Exception {

		logger.info("Registering new customer with email: {}", newCustomerRequest.geteMailAddress());

		Customer newCustomer = UserFactory.createCustomer(newCustomerRequest);

		try {
			customerRepository.save(newCustomer);
			logger.info("Successfully registered customer: {}", newCustomerRequest.geteMailAddress());
			return newCustomerRequest;
		} catch (Exception e) {
			logger.error("Failed to register customer {}: {}", newCustomerRequest.geteMailAddress(), e.getMessage());
			throw new Exception(e.getMessage()); // TODO
		}

	}

	public NewSellerRequest registerAsSeller(NewSellerRequest newSellerRequest) throws Exception {

		logger.info("Registering new seller with email: {}", newSellerRequest.geteMailAddress());

		Seller newSeller = UserFactory.createSeller(newSellerRequest);

		try {
			sellerRepository.save(newSeller);
			logger.info("Successfully registered seller: {}", newSellerRequest.geteMailAddress());
			return newSellerRequest;
		} catch (Exception e) {
			logger.error("Failed to register seller {}: {}", newSellerRequest.geteMailAddress(), e.getMessage());
			throw new Exception(e.getMessage()); // TODO
		}

	}

	public NewAdminRequest registerNewAdmin(NewAdminRequest newAdminRequest) throws Exception {

		logger.info("Registering new admin with email: {}", newAdminRequest.geteMailAddress());

		StaffDepartment department = staffDepartmentRepository.findById(newAdminRequest.getDepartmentId())
				.orElseThrow(() -> {
					logger.error("Department not found with ID: {}", newAdminRequest.getDepartmentId());
					return new Exception("Department not found");
				}); // TODO

		Admin newAdmin = UserFactory.createAdmin(newAdminRequest, department);

		try {
			adminRepository.save(newAdmin);
			logger.info("Successfully registered admin: {}", newAdminRequest.geteMailAddress());
			return newAdminRequest;
		} catch (Exception e) {
			logger.error("Failed to register admin {}: {}", newAdminRequest.geteMailAddress(), e.getMessage());
			throw new Exception(e.getMessage()); // TODO
		}

	}

	public String verifyUser(UserLoginRequest userLoginRequest) throws Exception {
		
		logger.info("Attempting login for user: {}", userLoginRequest.geteMailAddress());
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.geteMailAddress(), userLoginRequest.getPassword()));
		
		if(authentication.isAuthenticated()) {
			
			User user = userRepository.findByeMailAddress(userLoginRequest.geteMailAddress())
				.orElseThrow(() -> {
					logger.error("User not found during verification: {}", userLoginRequest.geteMailAddress());
					return new Exception("User not found!");
				});
			
			logger.info("User {} successfully authenticated with role: {}", userLoginRequest.geteMailAddress(), user.getRole());
			return jwtService.generateToken(userLoginRequest.geteMailAddress(), user.getRole().name());
		}
		
		logger.warn("Authentication failed for user: {}", userLoginRequest.geteMailAddress());
		return "fail";
	}

}
