package com.bugrates.online_marketplace_app.mapper;

import java.util.List;

import com.bugrates.online_marketplace_app.model.dto.response.OrderItemResponse;
import com.bugrates.online_marketplace_app.model.dto.response.OrderResponse;
import com.bugrates.online_marketplace_app.model.entity.Order;

public class OrderMapper {

public static OrderResponse toDTO(Order order, List<OrderItemResponse> orderItemResponses) {
		
		OrderResponse response = new OrderResponse();
		
		response.setOrderedAt(order.getOrderedAt());
		response.setOrderId(order.getOrderId());
		response.setOrderItemResponses(orderItemResponses);
		response.setTotalAmount(order.getTotalAmount());
		
		return response;
	}
	
}
