package com.bugrates.online_marketplace_app.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer extends User{
	
	@Column(nullable = false, unique = true)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String address;
	
	@OneToMany(mappedBy = "customer")
	private List<CartItem> myCartItems;
	
	public Customer() {
		
	}

	public Customer(String phoneNumber, String city, String address, List<CartItem> myCartItems) {
		super();
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.address = address;
		this.myCartItems = myCartItems;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<CartItem> getMyCartItems() {
		return myCartItems;
	}

	public void setMyCartItems(List<CartItem> myCartItems) {
		this.myCartItems = myCartItems;
	}
	
	
	
	

}
