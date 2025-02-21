package com.bugrates.online_marketplace_app.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.bugrates.online_marketplace_app.model.entity.OrderItem;

public class OrderResponse {
	
	private int orderId;
	
	private int totalAmount;
	
	private LocalDateTime orderedAt;
	
	private List<OrderItemResponse> orderItemResponses;

	public OrderResponse() {
		// TODO Auto-generated constructor stub
	}

	public OrderResponse(int orderId, int totalAmount, LocalDateTime orderedAt, List<OrderItemResponse> orderItemResponses) {
		this.orderId = orderId;
		this.totalAmount = totalAmount;
		this.orderItemResponses = orderItemResponses;
		this.orderedAt = orderedAt;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(LocalDateTime orderedAt) {
		this.orderedAt = orderedAt;
	}

	public List<OrderItemResponse> getOrderItemResponses() {
		return orderItemResponses;
	}

	public void setOrderItemResponses(List<OrderItemResponse> orderItemResponses) {
		this.orderItemResponses = orderItemResponses;
	}


	
	
	
}
