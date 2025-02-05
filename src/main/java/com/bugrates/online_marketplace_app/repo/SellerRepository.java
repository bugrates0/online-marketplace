package com.bugrates.online_marketplace_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugrates.online_marketplace_app.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

	public Seller findByEMailAddress(String eMailAddress);

}
