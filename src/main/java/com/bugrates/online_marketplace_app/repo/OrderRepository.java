package com.bugrates.online_marketplace_app.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugrates.online_marketplace_app.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

	public List<Order> findAllByCustomer_UserId(int customerId);
	
}
