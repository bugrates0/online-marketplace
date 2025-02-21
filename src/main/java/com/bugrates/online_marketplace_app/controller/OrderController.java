package com.bugrates.online_marketplace_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugrates.online_marketplace_app.model.dto.response.OrderResponse;
import com.bugrates.online_marketplace_app.model.entity.Order;
import com.bugrates.online_marketplace_app.service.OrderService;


@RestController
@Validated
@RequestMapping("/api/v1")
public class OrderController {

	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
	@PostMapping("/orders")
	public ResponseEntity<OrderResponse> newOrder(){

		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(orderService.placeOrder());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // TODO
		return null;
	}
	
}
