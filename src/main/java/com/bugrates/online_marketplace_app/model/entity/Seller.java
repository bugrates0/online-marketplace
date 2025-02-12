package com.bugrates.online_marketplace_app.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="sellers")
public class Seller extends User{

	@Column(unique = true, nullable = false)
	private String storeName;
	
	// private storeLogo
	
	@Column(unique = true, nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String city;
	
	@Column(unique = true, nullable = false)
	private String storeAddress;
	
	@OneToMany(mappedBy = "seller")
	private List<ListedProduct> listedProducts;
	
	public Seller() {
	
	}

	public Seller(String storeName, String phoneNumber, String city, String storeAddress, List<ListedProduct> listedProducts) {
		super();
		this.storeName = storeName;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.storeAddress = storeAddress;
		this.listedProducts = listedProducts;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public List<ListedProduct> getListedProducts() {
		return listedProducts;
	}

	public void setListedProducts(List<ListedProduct> listedProducts) {
		this.listedProducts = listedProducts;
	}
	
	
	
	
}
