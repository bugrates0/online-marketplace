package com.bugrates.online_marketplace_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.model.dto.request.NewListedProductRequest;
import com.bugrates.online_marketplace_app.model.dto.response.ListedProductResponse;
import com.bugrates.online_marketplace_app.model.dto.response.ListedProductsForAProductResponse;
import com.bugrates.online_marketplace_app.model.entity.ListedProduct;
import com.bugrates.online_marketplace_app.model.entity.Product;
import com.bugrates.online_marketplace_app.model.entity.Seller;
import com.bugrates.online_marketplace_app.repo.ListedProductRepository;

@Service
public class ListedProductService {

	private ListedProductRepository listedProductRepository;
	private ProductService productService;

	public ListedProductService(ListedProductRepository listedProductRepository, ProductService productService) {
		this.listedProductRepository = listedProductRepository;
		this.productService = productService;
	}

	public NewListedProductRequest createNewListedProduct(NewListedProductRequest newListedProductRequest,
			int productId) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Seller currentUser = (Seller) authentication.getPrincipal();

		Product product = productService.findById(productId);

		ListedProduct newListedProduct = new ListedProduct();

		newListedProduct.setBrand(newListedProductRequest.getBrand());
		newListedProduct.setCreatedAt(LocalDateTime.now());
		newListedProduct.setDescription(newListedProductRequest.getDescription());
		newListedProduct.setPrice(newListedProductRequest.getPrice());
		newListedProduct.setProduct(product);
		newListedProduct.setSeller(currentUser);
		newListedProduct.setStockQuantity(newListedProductRequest.getStockQuantity());

		listedProductRepository.save(newListedProduct);

		return newListedProductRequest;
	}

	/*
	 * //implement with status public void deleteListedProduct(int listedProductId)
	 * throws Exception {
	 * 
	 * 
	 * 
	 * }
	 * 
	 */

	public ListedProductsForAProductResponse getMyListedProductsForAProduct(int productId) throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // TODO

		Seller currentUser = (Seller) authentication.getPrincipal();

		List<ListedProduct> myListedProducts = listedProductRepository
				.findByProductAndSeller(productService.findById(productId), currentUser);

		List<ListedProductResponse> myListedProductResponse = myListedProducts.stream()
				.map(myListedProduct -> new ListedProductResponse(myListedProduct.getListedProductId(),
						myListedProduct.getBrand(), myListedProduct.getDescription(), myListedProduct.getPrice(),
						myListedProduct.getStockQuantity()))
				.collect(Collectors.toList());

		ListedProductsForAProductResponse response = new ListedProductsForAProductResponse();

		response.setProductId(productId);
		response.setProductName(productService.findById(productId).getProductName());
		response.setListedProducts(myListedProductResponse);

		return response;

	}

	public List<ListedProductsForAProductResponse> getAllListedProducts() {
		System.out.println("deneme1");
		List<Product> allProducts = productService.getAllProducts();
		System.out.println("deneme2");
		List<ListedProductsForAProductResponse> response = allProducts.stream()
				.map(product -> new ListedProductsForAProductResponse(product.getProductId(), product.getProductName(),
						listedProductRepository.findByProduct(product).stream()
								.map(listedProduct -> new ListedProductResponse(listedProduct.getListedProductId(),
										listedProduct.getBrand(), listedProduct.getDescription(),
										listedProduct.getPrice(), listedProduct.getStockQuantity()))
								.collect(Collectors.toList())))
				.collect(Collectors.toList());
		System.out.println("deneme3");
		
		return response;
	}
}
