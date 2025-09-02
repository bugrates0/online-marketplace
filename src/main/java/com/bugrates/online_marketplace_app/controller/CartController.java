package com.bugrates.online_marketplace_app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugrates.online_marketplace_app.model.dto.response.CartItemResponse;
import com.bugrates.online_marketplace_app.service.CartService;

@RestController
@Validated
@RequestMapping("/api/v1/cart")
public class CartController { // better to use Cart entity for optimized sql queries

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	private CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping("/{listedProductId}")
	public ResponseEntity<?> addProductToCart(@PathVariable("listedProductId") int listedProductId) throws Exception {

		logger.info("Received request to add product {} to cart", listedProductId);
		try {
			cartService.addProductToCart(listedProductId);
			logger.info("Successfully added product {} to cart", listedProductId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Added to cart");
		} catch (Exception e) {
			logger.error("Failed to add product {} to cart: {}", listedProductId, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add to cart");
		}
	}

	@DeleteMapping("/{listedProductId}")
	public ResponseEntity<?> removeProductFromCart(@PathVariable("listedProductId") int listedProductId) {
		
		logger.info("Received request to remove product {} from cart", listedProductId);
		try {
			cartService.removeProductFromCart(listedProductId);
			logger.info("Successfully removed product {} from cart", listedProductId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Removed from cart");
		} catch (Exception e) {
			logger.error("Failed to remove product {} from cart: {}", listedProductId, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove from cart");
		}
	}

	/*
	
	public ResponseEntity<> updateProductQuantity(){
		
	}

	*/

	@GetMapping
	public ResponseEntity<List<CartItemResponse>> getMyCartItems() {
		
		logger.info("Received request to get cart items");
		try {
			List<CartItemResponse> cartItems = cartService.getMyCartItems();
			logger.info("Retrieved {} cart items", cartItems.size());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartItems);
		} catch (Exception e) {
			logger.error("Failed to retrieve cart items: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
