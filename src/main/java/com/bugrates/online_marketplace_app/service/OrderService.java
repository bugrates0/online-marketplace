package com.bugrates.online_marketplace_app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugrates.online_marketplace_app.mapper.OrderItemMapper;
import com.bugrates.online_marketplace_app.mapper.OrderMapper;
import com.bugrates.online_marketplace_app.model.dto.response.OrderItemResponse;
import com.bugrates.online_marketplace_app.model.dto.response.OrderResponse;
import com.bugrates.online_marketplace_app.model.entity.CartItem;
import com.bugrates.online_marketplace_app.model.entity.Customer;
import com.bugrates.online_marketplace_app.model.entity.Order;
import com.bugrates.online_marketplace_app.model.entity.OrderItem;
import com.bugrates.online_marketplace_app.repo.CartItemRepository;
import com.bugrates.online_marketplace_app.repo.OrderItemRepository;
import com.bugrates.online_marketplace_app.repo.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	private OrderItemRepository orderItemRepository;
	private CartItemRepository cartItemRepository;

	public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
			CartItemRepository cartItemRepository) {
		this.orderItemRepository = orderItemRepository;
		this.orderRepository = orderRepository;
		this.cartItemRepository = cartItemRepository;
	}

	@Transactional
	public OrderResponse placeOrder() throws Exception {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Customer currentUser = (Customer) authentication.getPrincipal();

		// sepetteki ürünleri çek

		List<CartItem> myCartItems = cartItemRepository.findAllByCustomer_UserId(currentUser.getUserId());

		if (myCartItems.isEmpty() == false) {

			Order order = new Order();
			order.setCustomer(currentUser);
			order.setOrderedAt(LocalDateTime.now());

			List<OrderItem> myOrderItems = myCartItems.stream().map(myCartItem -> {

				order.setTotalAmount(
						order.getTotalAmount() + (myCartItem.getQuantity() * myCartItem.getListedProduct().getPrice()));

				myCartItem.getListedProduct()
						.setStockQuantity(myCartItem.getListedProduct().getStockQuantity() - myCartItem.getQuantity());

				return new OrderItem(myCartItem.getQuantity(), myCartItem.getListedProduct().getPrice(), order,
						myCartItem.getListedProduct(), myCartItem.getListedProduct().getSeller());

			}).toList();

			order.setOrderItems(myOrderItems);

			orderRepository.save(order);

			cartItemRepository.deleteAllByCustomer_UserId(currentUser.getUserId());

			
			List<OrderItemResponse> orderItemResponses = new ArrayList<OrderItemResponse>();

			for (OrderItem item : myOrderItems) {

				orderItemResponses.add(OrderItemMapper.toDTO(item));

			}

			OrderResponse response = OrderMapper.toDTO(order, orderItemResponses);

			return response;
		}

		throw new Exception("No product in cart !"); // TODO

	}

}
