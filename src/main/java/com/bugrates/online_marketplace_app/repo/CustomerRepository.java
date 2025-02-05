package com.bugrates.online_marketplace_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.bugrates.online_marketplace_app.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

	public Customer findByEMailAddress(String eMailAddress);

}
