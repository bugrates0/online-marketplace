package com.bugrates.online_marketplace_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bugrates.online_marketplace_app.model.dto.request.NewCustomerRequest;
import com.bugrates.online_marketplace_app.model.dto.request.NewProductCategoryRequest;
import com.bugrates.online_marketplace_app.model.dto.response.ProductCategoryResponse;
import com.bugrates.online_marketplace_app.service.ProductCategoryService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1/product-categories")
public class ProductCategoryController {
	
	private ProductCategoryService productCategoryService;
	
	public ProductCategoryController(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}
	
	
	@PostMapping
	public ResponseEntity<NewProductCategoryRequest> newProductCategory(@Valid @RequestBody NewProductCategoryRequest newProductCategoryRequest) throws Exception{

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productCategoryService.createNewProductCategory(newProductCategoryRequest)); //TODO
	}
	
	@GetMapping
	public ResponseEntity<List<ProductCategoryResponse>> getAllProductCategories(){
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(productCategoryService.getAllProductCategories());
	}


	@DeleteMapping("/{productCategoryId}")
	public ResponseEntity<?> deleteProductCategory(@PathVariable("productCategoryId") int id) throws Exception{
		productCategoryService.deleteProductCategory(id);
		return ResponseEntity.ok(HttpStatus.ACCEPTED); //TODO
	}

}
