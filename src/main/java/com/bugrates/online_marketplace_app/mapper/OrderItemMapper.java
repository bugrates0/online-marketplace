package com.bugrates.online_marketplace_app.mapper;

import com.bugrates.online_marketplace_app.model.dto.response.OrderItemResponse;

import com.bugrates.online_marketplace_app.model.entity.OrderItem;

public class OrderItemMapper {

	public static OrderItemResponse toDTO(OrderItem orderItem) {
		
		OrderItemResponse response = new OrderItemResponse();
		
		response.setBrand(orderItem.getListedProduct().getBrand());
		response.setListedProductId(orderItem.getListedProduct().getListedProductId());
		response.setOrderItemId(orderItem.getOrderItemId());
		response.setProductName(orderItem.getListedProduct().getProduct().getProductName());
		response.setQuantity(orderItem.getQuantity());
		response.setSellerId(orderItem.getSeller().getUserId());
		response.setStoreName(orderItem.getSeller().getStoreName());
		response.setUnitPrice(orderItem.getUnitPrice());
		
		return response;
	}
	
}
