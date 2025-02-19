package com.bugrates.online_marketplace_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bugrates.online_marketplace_app.model.dto.response.CartItemResponse;
import com.bugrates.online_marketplace_app.model.entity.CartItem;
import com.bugrates.online_marketplace_app.model.entity.Customer;
import com.bugrates.online_marketplace_app.model.entity.ListedProduct;
import com.bugrates.online_marketplace_app.repo.CartItemRepository;
import com.bugrates.online_marketplace_app.repo.ListedProductRepository;

@Service
public class CartService {

	private final int DEFAULT_QUANTITY = 1;

	private CartItemRepository cartItemRepository;
	private ListedProductRepository listedProductRepository;

	public CartService(CartItemRepository cartItemRepository, ListedProductRepository listedProductRepository) {
		this.cartItemRepository = cartItemRepository;
		this.listedProductRepository = listedProductRepository;
	}

	public void addProductToCart(int listedProductId) throws Exception {

		ListedProduct listedProduct = listedProductRepository.findById(listedProductId)
				.orElseThrow(() -> new Exception("No listed product")); // TODO getReferenceById() olur muydu ??

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Customer currentUser = (Customer) authentication.getPrincipal();

		CartItem newCartItem = new CartItem();

		newCartItem.setCustomer(currentUser);
		newCartItem.setListedProduct(listedProduct);
		newCartItem.setQuantity(DEFAULT_QUANTITY);

		cartItemRepository.save(newCartItem);
	}

	public void removeProductFromCart(int listedProductId) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Customer currentUser = (Customer) authentication.getPrincipal();

		cartItemRepository.deleteByCustomer_UserIdAndListedProduct_ListedProductId(currentUser.getUserId(),
				listedProductId);

	}

	public List<CartItemResponse> getMyCartItems() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Customer currentUser = (Customer) authentication.getPrincipal();

		List<CartItem> myCartItems = cartItemRepository.findByCustomer_UserId(currentUser.getUserId());

		List<CartItemResponse> myCartItemsResponse = myCartItems.stream()
				.map(myCartItem -> new CartItemResponse(myCartItem.getCartItemId(),
						myCartItem.getListedProduct().getListedProductId(),
						myCartItem.getListedProduct().getSeller().getUserId(),
						myCartItem.getListedProduct().getSeller().getStoreName(),
						myCartItem.getListedProduct().getProduct().getProductName(),
						myCartItem.getListedProduct().getBrand(), myCartItem.getQuantity(),
						myCartItem.getQuantity() * myCartItem.getListedProduct().getPrice()))
				.collect(Collectors.toList());

		return myCartItemsResponse;

	}

	/*
	 * update quantity function //TODO
	 * 
	 */
	
	
}
