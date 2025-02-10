package com.bugrates.online_marketplace_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.model.dto.request.NewProductCategoryRequest;
import com.bugrates.online_marketplace_app.model.dto.response.ProductCategoryResponse;
import com.bugrates.online_marketplace_app.model.entity.ProductCategory;
import com.bugrates.online_marketplace_app.repo.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	private ProductCategoryRepository productCategoryRepository;
	
	
	public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
		this.productCategoryRepository = productCategoryRepository;
	}
	
	
	public NewProductCategoryRequest createNewProductCategory(NewProductCategoryRequest newProductCategoryRequest) throws Exception {
		
		ProductCategory newProductCategory = new ProductCategory();
		
		newProductCategory.setProductCategoryName(newProductCategoryRequest.getProductCategoryName());
		
		try {
			productCategoryRepository.save(newProductCategory);
			return newProductCategoryRequest;
		} catch (Exception e) {
			throw new Exception(e.getMessage()); //TODO
		}
		
	}
	
	
	public List<ProductCategoryResponse> getAllProductCategories(){
		
		List<ProductCategory> productCategories = productCategoryRepository.findAll();
		
		return productCategories.stream()
								.map(category -> new ProductCategoryResponse(category.getProductCategoryId(), category.getProductCategoryName()))
								.collect(Collectors.toList());
	}
	
	public void deleteProductCategory(int id) throws Exception {
		try {
			productCategoryRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage()); //TODO
		}
		
	}
	
	
	
}
