package com.bugrates.online_marketplace_app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugrates.online_marketplace_app.model.entity.Product;
import com.bugrates.online_marketplace_app.model.entity.ProductCategory;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByProductCategory(ProductCategory productCategory);
	
}
