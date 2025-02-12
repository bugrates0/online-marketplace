package com.bugrates.online_marketplace_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.model.dto.request.NewProductRequest;
import com.bugrates.online_marketplace_app.model.dto.response.ProductResponse;
import com.bugrates.online_marketplace_app.model.dto.response.ProductsOfCategoryResponse;
import com.bugrates.online_marketplace_app.model.entity.Product;
import com.bugrates.online_marketplace_app.model.entity.ProductCategory;
import com.bugrates.online_marketplace_app.repo.ProductRepository;

@Service
public class ProductService {

	private ProductCategoryService productCategoryService;
	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository, ProductCategoryService productCategoryService) {
		this.productRepository = productRepository;
		this.productCategoryService = productCategoryService;
	}

	public NewProductRequest createNewProduct(NewProductRequest newProductRequest, int categoryId) throws Exception {

		try {
			ProductCategory category = productCategoryService.findById(categoryId);

			Product newProduct = new Product();

			newProduct.setProductCategory(category);
			newProduct.setProductName(newProductRequest.getProductName());

			productRepository.save(newProduct);

			return newProductRequest;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public ProductsOfCategoryResponse getAllProductsOfACategory(int productCategoryId) throws Exception {

		ProductCategory category = productCategoryService.findById(productCategoryId);

		List<Product> products = productRepository.findByProductCategory(category);

		ProductsOfCategoryResponse response = new ProductsOfCategoryResponse();

		List<ProductResponse> productsResponse = products.stream()
				.map(product -> new ProductResponse(product.getProductId(), product.getProductName()))
				.collect(Collectors.toList());

		response.setProductCategoryId(productCategoryId);
		response.setProductCategoryName(category.getProductCategoryName());
		response.setProducts(productsResponse);

		return response;

	}

	public void deleteProduct(int productId) throws Exception {

		try {
			productRepository.deleteById(productId);
		} catch (Exception e) {
			throw new Exception(e.getMessage()); // TODO
		}

	}

	public Product findById(int productId) throws Exception {

		return productRepository.findById(productId).orElseThrow(() -> new Exception("Product not found"));
	}

	public ProductResponse getProduct(int productId) throws Exception {

		Product product = productRepository.findById(productId).orElseThrow(() -> new Exception("No product")); // TODO

		ProductResponse productResponse = new ProductResponse();

		productResponse.setProductId(product.getProductId());
		productResponse.setProductName(product.getProductName());

		return productResponse;
	}

	public List<Product> getAllProducts() {

		List<Product> allProducts = productRepository.findAll();

		return allProducts;

	}

}
