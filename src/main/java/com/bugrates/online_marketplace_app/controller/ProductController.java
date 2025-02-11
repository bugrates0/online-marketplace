package com.bugrates.online_marketplace_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugrates.online_marketplace_app.model.dto.request.NewProductRequest;
import com.bugrates.online_marketplace_app.model.dto.response.ProductResponse;
import com.bugrates.online_marketplace_app.model.dto.response.ProductsOfCategoryResponse;
import com.bugrates.online_marketplace_app.service.ProductService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/product-categories/{categoryId}/products")
	public ResponseEntity<NewProductRequest> newProduct(@PathVariable("categoryId") int categoryId,
			@Valid @RequestBody NewProductRequest newProductRequest) throws Exception {

		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(productService.createNewProduct(newProductRequest, categoryId)); // TODO
	}

	@GetMapping("/product-categories/{categoryId}/products")
	public ResponseEntity<ProductsOfCategoryResponse> getAllProductsOfACategory(
			@PathVariable("categoryId") int categoryId) throws Exception {

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.getAllProductsOfACategory(categoryId));
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable("productId") int productId) throws Exception {
		productService.deleteProduct(productId);
		return ResponseEntity.ok(HttpStatus.ACCEPTED); // TODO
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") int productId) throws Exception {

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productService.getProduct(productId));
	}

}
