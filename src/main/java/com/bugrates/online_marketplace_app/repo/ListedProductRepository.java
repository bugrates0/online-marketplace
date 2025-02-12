package com.bugrates.online_marketplace_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugrates.online_marketplace_app.model.entity.ListedProduct;
import com.bugrates.online_marketplace_app.model.entity.Product;
import com.bugrates.online_marketplace_app.model.entity.Seller;

@Repository
public interface ListedProductRepository extends JpaRepository<ListedProduct, Integer>{

	public List<ListedProduct> findByProductAndSeller(Product product, Seller seller);
	
	List<ListedProduct> findByProduct(Product product);
	
}
