package com.bugrates.online_marketplace_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugrates.online_marketplace_app.model.entity.Admin;
import com.bugrates.online_marketplace_app.model.entity.Customer;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
 
	public Admin findByEMailAddress(String eMailAddress);
	
}
