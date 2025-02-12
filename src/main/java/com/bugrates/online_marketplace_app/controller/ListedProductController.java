package com.bugrates.online_marketplace_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugrates.online_marketplace_app.model.dto.request.NewListedProductRequest;
import com.bugrates.online_marketplace_app.model.dto.response.ListedProductsForAProductResponse;
import com.bugrates.online_marketplace_app.service.ListedProductService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1")
public class ListedProductController {

	private ListedProductService listedProductService;

	public ListedProductController(ListedProductService listedProductService) {
		this.listedProductService = listedProductService;
	}

	@PostMapping("/products/{productId}/listed-products")
	public ResponseEntity<NewListedProductRequest> newListedProduct(@PathVariable("productId") int productId,
			@Valid @RequestBody NewListedProductRequest newListedProductRequest) throws Exception {

		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(listedProductService.createNewListedProduct(newListedProductRequest, productId)); // TODO
	}
	
	@GetMapping("/products/listed-products")
	public ResponseEntity<List<ListedProductsForAProductResponse>> getAllListedProducts() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listedProductService.getAllListedProducts());
	}

	// bir ürünü kaldırma //SELLER ve kendisine ait olması lazım POST
	// /api/v1/products/{productId}/listed-products/{listedProductId}
	// status ile yapılacak
	/*
	 * @DeleteMapping("/listed-products/{listedProductId}") public ResponseEntity<?>
	 * deleteListedProduct(@PathVariable("listedProductId") int listedProductId)
	 * throws Exception{ listedProductService.deleteListedProduct(listedProductId);
	 * return ResponseEntity.ok(HttpStatus.ACCEPTED); //TODO }
	 */

	@GetMapping("/products/{productId}/listed-products")
	public ResponseEntity<ListedProductsForAProductResponse> getMyListedProductsForAProduct(@PathVariable("productId") int productId) throws Exception {
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listedProductService.getMyListedProductsForAProduct(productId));
	}
	
	//kendisine ait tüm listed productları çekme product product ayır //SELLER ve kendisine ait
	/*
	@GetMapping("/products/listed-products")
	public ResponseEntity<> getAllMyListedProducts()
	*/

	
	
}
