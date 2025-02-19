package com.bugrates.online_marketplace_app.controller;

import java.util.List;

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
import com.bugrates.online_marketplace_app.model.entity.CartItem;
import com.bugrates.online_marketplace_app.service.CartService;

@RestController
@Validated
@RequestMapping("/api/v1/cart")
public class CartController { // better to use Cart entity for optimized sql queries

	private CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping("/{listedProductId}")
	public ResponseEntity<?> addProductToCart(@PathVariable("listedProductId") int listedProductId) throws Exception {

		cartService.addProductToCart(listedProductId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Added to cart"); // TODO
	}

	@DeleteMapping("/{listedProductId}")
	public ResponseEntity<?> removeProductFromCart(@PathVariable("listedProductId") int listedProductId) {
		
		cartService.removeProductFromCart(listedProductId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Removed from cart"); // TODO
	}

	/*
	
	public ResponseEntity<> updateProductQuantity(){
		
	}

	*/

	@GetMapping
	public ResponseEntity<List<CartItemResponse>> getMyCartItems() {
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartService.getMyCartItems()); // TODO
	}
	
}
