package com.bugrates.online_marketplace_app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "orderitems")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;

	@Column(nullable = false)
	@Min(1)
	private int quantity;

	@Column(nullable = false)
	private int unitPrice;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "listed_product_id")
	private ListedProduct listedProduct;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;
	
	
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}

	public OrderItem(int orderItemId, int quantity, int unitPrice, Order order, ListedProduct listedProduct, Seller seller) {
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.order = order;
		this.listedProduct = listedProduct;
		this.seller = seller;
	}
	
	public OrderItem(int quantity, int unitPrice, Order order, ListedProduct listedProduct, Seller seller) {
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.order = order;
		this.listedProduct = listedProduct;
		this.seller = seller;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ListedProduct getListedProduct() {
		return listedProduct;
	}

	public void setListedProduct(ListedProduct listedProduct) {
		this.listedProduct = listedProduct;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	
	
}
