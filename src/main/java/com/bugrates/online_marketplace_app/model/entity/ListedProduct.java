package com.bugrates.online_marketplace_app.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "listedproducts", uniqueConstraints = {  @UniqueConstraint(name = "Unique_Seller-Product-Brand", columnNames = {"seller_id", "product_id", "brand"})  })
public class ListedProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listedProductId;
	
	@Column(nullable = false)
	private String brand;
	
	@Lob
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private int stockQuantity;
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name="seller_id")
	private Seller seller;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@OneToMany(mappedBy = "listedProduct") //we can find how many carts have this product. 
	private List<CartItem> cartItems;

	public ListedProduct() {
		// TODO Auto-generated constructor stub
	}

	public ListedProduct(int listedProductId, String brand, String description, int price, int stockQuantity,
			LocalDateTime createdAt, Seller seller, Product product, List<CartItem> cartItems) {
		this.listedProductId = listedProductId;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.createdAt = createdAt;
		this.seller = seller;
		this.product = product;
		this.cartItems = cartItems;
	}

	public int getListedProductId() {
		return listedProductId;
	}

	public void setListedProductId(int listedProductId) {
		this.listedProductId = listedProductId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	
	
	
	
}
