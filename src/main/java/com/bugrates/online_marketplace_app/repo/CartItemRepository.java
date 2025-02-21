package com.bugrates.online_marketplace_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugrates.online_marketplace_app.model.entity.CartItem;
import com.bugrates.online_marketplace_app.model.entity.Customer;
import com.bugrates.online_marketplace_app.model.entity.ListedProduct;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	public void deleteByCustomer_UserIdAndListedProduct_ListedProductId(int customerId, int listedProductId);
	
	public List<CartItem> findAllByCustomer_UserId(int customerId);

	public void deleteAllByCustomer_UserId(int customerId);
}
