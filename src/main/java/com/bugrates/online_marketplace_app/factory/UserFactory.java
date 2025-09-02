package com.bugrates.online_marketplace_app.factory;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bugrates.online_marketplace_app.model.dto.request.NewAdminRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewCustomerRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewSellerRequest;
import com.bugrates.online_marketplace_app.model.entity.Admin;
import com.bugrates.online_marketplace_app.model.entity.Customer;
import com.bugrates.online_marketplace_app.model.entity.Seller;
import com.bugrates.online_marketplace_app.model.entity.StaffDepartment;
import com.bugrates.online_marketplace_app.model.enums.Role;

public class UserFactory {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public static Customer createCustomer(NewCustomerRequest req) {
        Customer customer = new Customer();
        
        // Set common User fields
        customer.setFirstName(req.getFirstName());
        customer.setLastName(req.getLastName());
        customer.seteMailAddress(req.geteMailAddress());
        customer.setPassword(encoder.encode(req.getPassword()));
        customer.setCreatedAt(LocalDateTime.now());
        customer.setRole(Role.CUSTOMER);
        
        // Set Customer-specific fields
        customer.setAddress(req.getAddress());
        customer.setCity(req.getCity());
        customer.setPhoneNumber(req.getPhoneNumber());
        
        return customer;
    }

    public static Seller createSeller(NewSellerRequest req) {
        Seller seller = new Seller();
        
        // Set common User fields
        seller.setFirstName(req.getFirstName());
        seller.setLastName(req.getLastName());
        seller.seteMailAddress(req.geteMailAddress());
        seller.setPassword(encoder.encode(req.getPassword()));
        seller.setCreatedAt(LocalDateTime.now());
        seller.setRole(Role.SELLER);
        
        // Set Seller-specific fields
        seller.setCity(req.getCity());
        seller.setPhoneNumber(req.getPhoneNumber());
        seller.setStoreName(req.getStoreName());
        seller.setStoreAddress(req.getStoreAddress());
        
        return seller;
    }

    public static Admin createAdmin(NewAdminRequest req, StaffDepartment department) {
        Admin admin = new Admin();
        
        // Set common User fields
        admin.setFirstName(req.getFirstName());
        admin.setLastName(req.getLastName());
        admin.seteMailAddress(req.geteMailAddress());
        admin.setPassword(encoder.encode(req.getPassword()));
        admin.setCreatedAt(LocalDateTime.now());
        admin.setRole(Role.ADMIN);
        
        // Set Admin-specific fields
        admin.setStaffDepartment(department);
        
        return admin;
    }
}
