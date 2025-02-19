package com.bugrates.online_marketplace_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="cartitems", uniqueConstraints = { @UniqueConstraint(name = "UniqueListedProductInCart", columnNames = {"customer_id", "listed_product_id" }) } )
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartItemId;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="listed_product_id")
	private ListedProduct listedProduct;
	
	@Min(1)
	private int quantity;

	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public CartItem(int cartItemId, Customer customer, ListedProduct listedProduct, int quantity) {
		this.cartItemId = cartItemId;
		this.customer = customer;
		this.listedProduct = listedProduct;
		this.quantity = quantity;
	}

	public int getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ListedProduct getListedProduct() {
		return listedProduct;
	}

	public void setListedProduct(ListedProduct listedProduct) {
		this.listedProduct = listedProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
