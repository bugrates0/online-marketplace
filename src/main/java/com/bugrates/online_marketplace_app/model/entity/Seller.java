package com.bugrates.online_marketplace_app.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	public Seller() {
	
	}

	public Seller(String storeName, String phoneNumber, String city, String storeAddress) {
		super();
		this.storeName = storeName;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.storeAddress = storeAddress;
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
	
	
	
	
}
