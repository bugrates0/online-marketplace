package com.bugrates.online_marketplace_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bugrates.online_marketplace_app.model.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	public List<OrderItem> findAllBySeller_UserId(int sellerId);

}
