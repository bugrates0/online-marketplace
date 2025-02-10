package com.bugrates.online_marketplace_app.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="productcategories")
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productCategoryId;
	
	@Column(nullable = false, unique = true)
	private String productCategoryName;

	@OneToMany(mappedBy = "productCategory")
	private List<Product> products;
	
	
	public ProductCategory() {
		// TODO Auto-generated constructor stub
	}


	public ProductCategory(int productCategoryId, String productCategoryName, List<Product> products) {
		this.productCategoryId = productCategoryId;
		this.productCategoryName = productCategoryName;
		this.products = products;
	}


	public int getProductCategoryId() {
		return productCategoryId;
	}


	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}


	public String getProductCategoryName() {
		return productCategoryName;
	}


	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	
	
	
	
	
}
