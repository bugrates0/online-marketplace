package com.bugrates.online_marketplace_app.mapper;

import com.bugrates.online_marketplace_app.model.dto.response.IncomingOrderResponse;
import com.bugrates.online_marketplace_app.model.entity.OrderItem;

public class IncomingOrderMapper {

	public static IncomingOrderResponse toIncomingOrderResponse(OrderItem orderItem) {
		
		IncomingOrderResponse response = new IncomingOrderResponse();
		
		response.setCustomerAddress(orderItem.getOrder().getCustomer().getAddress());
		response.setCustomerCity(orderItem.getOrder().getCustomer().getCity());
		response.setCustomerId(orderItem.getOrder().getCustomer().getUserId());
		response.setCustomerMailAddress(orderItem.getOrder().getCustomer().geteMailAddress());
		response.setCustomerName(orderItem.getOrder().getCustomer().getFirstName() +" "+ orderItem.getOrder().getCustomer().getLastName());
		response.setCustomerPhoneNumber(orderItem.getOrder().getCustomer().getPhoneNumber());
		response.setOrderedAt(orderItem.getOrder().getOrderedAt());
		response.setOrderItemId(orderItem.getOrderItemId());
		response.setQuantity(orderItem.getQuantity());
		response.setUnitPrice(orderItem.getUnitPrice());
		
		return response;
	}
	
	
	
}
