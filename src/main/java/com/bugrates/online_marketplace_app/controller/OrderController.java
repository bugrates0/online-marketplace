package com.bugrates.online_marketplace_app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugrates.online_marketplace_app.model.dto.response.IncomingOrderResponse;
import com.bugrates.online_marketplace_app.model.dto.response.OrderResponse;
import com.bugrates.online_marketplace_app.service.OrderService;

@RestController
@Validated
@RequestMapping("/api/v1")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/orders")
	public ResponseEntity<OrderResponse> newOrder() {

		logger.info("Received request to place new order");
		try {
			OrderResponse response = orderService.placeOrder();
			logger.info("Order placed successfully");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		} catch (Exception e) {
			logger.error("Failed to place order: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/orders")
	public ResponseEntity<List<OrderResponse>> getMyOrders() {

		logger.info("Received request to get user orders");
		try {
			List<OrderResponse> orders = orderService.getMyOrders();
			logger.info("Retrieved {} orders", orders.size());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(orders);
		} catch (Exception e) {
			logger.error("Failed to retrieve orders: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/incoming-orders")
	public ResponseEntity<List<IncomingOrderResponse>> getMyIncomingOrders() {

		logger.info("Received request to get incoming orders");
		try {
			List<IncomingOrderResponse> incomingOrders = orderService.getMyIncomingOrders();
			logger.info("Retrieved {} incoming orders", incomingOrders.size());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(incomingOrders);
		} catch (Exception e) {
			logger.error("Failed to retrieve incoming orders: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
