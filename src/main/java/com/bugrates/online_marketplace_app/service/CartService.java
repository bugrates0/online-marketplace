package com.bugrates.online_marketplace_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.model.dto.response.CartItemResponse;
import com.bugrates.online_marketplace_app.model.entity.CartItem;
import com.bugrates.online_marketplace_app.model.entity.Customer;
import com.bugrates.online_marketplace_app.model.entity.ListedProduct;
import com.bugrates.online_marketplace_app.repo.CartItemRepository;
import com.bugrates.online_marketplace_app.repo.ListedProductRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	private static final Logger logger = LoggerFactory.getLogger(CartService.class);
	private final int DEFAULT_QUANTITY = 1;

	private CartItemRepository cartItemRepository;
	private ListedProductRepository listedProductRepository;

	public CartService(CartItemRepository cartItemRepository, ListedProductRepository listedProductRepository) {
		this.cartItemRepository = cartItemRepository;
		this.listedProductRepository = listedProductRepository;
	}

	public void addProductToCart(int listedProductId) throws Exception {

		logger.info("Adding product with listed product ID {} to cart", listedProductId);

		ListedProduct listedProduct = listedProductRepository.findById(listedProductId)
				.orElseThrow(() -> {
					logger.error("Listed product not found with ID: {}", listedProductId);
					return new Exception("No listed product");
				}); // TODO getReferenceById() olur muydu ??

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Customer currentUser = (Customer) authentication.getPrincipal();

		logger.debug("Adding product to cart for customer ID: {}", currentUser.getUserId());

		CartItem newCartItem = new CartItem();

		newCartItem.setCustomer(currentUser);
		newCartItem.setListedProduct(listedProduct);
		newCartItem.setQuantity(DEFAULT_QUANTITY);

		cartItemRepository.save(newCartItem);
		logger.info("Successfully added product {} to cart for customer {}", listedProductId, currentUser.getUserId());
	}

	@Transactional
	public void removeProductFromCart(int listedProductId) {

		logger.info("Removing product with listed product ID {} from cart", listedProductId);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Customer currentUser = (Customer) authentication.getPrincipal();

		cartItemRepository.deleteByCustomer_UserIdAndListedProduct_ListedProductId(currentUser.getUserId(),
				listedProductId);
		
		logger.info("Successfully removed product {} from cart for customer {}", listedProductId, currentUser.getUserId());

	}

	public List<CartItemResponse> getMyCartItems() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Customer currentUser = (Customer) authentication.getPrincipal();

		logger.debug("Fetching cart items for customer ID: {}", currentUser.getUserId());

		List<CartItem> myCartItems = cartItemRepository.findAllByCustomer_UserId(currentUser.getUserId());

		List<CartItemResponse> myCartItemsResponse = myCartItems.stream()
				.map(myCartItem -> new CartItemResponse(myCartItem.getCartItemId(),
						myCartItem.getListedProduct().getListedProductId(),
						myCartItem.getListedProduct().getSeller().getUserId(),
						myCartItem.getListedProduct().getSeller().getStoreName(),
						myCartItem.getListedProduct().getProduct().getProductName(),
						myCartItem.getListedProduct().getBrand(), myCartItem.getQuantity(),
						myCartItem.getQuantity() * myCartItem.getListedProduct().getPrice()))
				.collect(Collectors.toList());

		logger.info("Retrieved {} cart items for customer ID: {}", myCartItemsResponse.size(), currentUser.getUserId());
		return myCartItemsResponse;

	}

	/*
	 * update quantity function //TODO
	 * 
	 */
	
	
}
