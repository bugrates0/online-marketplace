package com.bugrates.online_marketplace_app.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	@Column(nullable = false, unique = true)
	private String productName;
	
	@ManyToOne
    @JoinColumn(name="category_id")
	private ProductCategory productCategory;
	
	@OneToMany(mappedBy = "product")
	private List<ListedProduct> listedProducts;
	
	public Product() {

	}


	public Product(int productId, String productName, ProductCategory productCategory, List<ListedProduct> listedProducts) {
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.listedProducts = listedProducts;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public ProductCategory getProductCategory() {
		return productCategory;
	}


	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}


	public List<ListedProduct> getListedProducts() {
		return listedProducts;
	}


	public void setListedProducts(List<ListedProduct> listedProducts) {
		this.listedProducts = listedProducts;
	}
	
	
	
	
}
