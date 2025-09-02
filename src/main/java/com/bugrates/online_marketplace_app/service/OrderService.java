package com.bugrates.online_marketplace_app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bugrates.online_marketplace_app.mapper.IncomingOrderMapper;
import com.bugrates.online_marketplace_app.mapper.OrderItemMapper;
import com.bugrates.online_marketplace_app.mapper.OrderMapper;
import com.bugrates.online_marketplace_app.model.dto.response.IncomingOrderResponse;
import com.bugrates.online_marketplace_app.model.dto.response.OrderItemResponse;
import com.bugrates.online_marketplace_app.model.dto.response.OrderResponse;
import com.bugrates.online_marketplace_app.model.entity.CartItem;
import com.bugrates.online_marketplace_app.model.entity.Customer;
import com.bugrates.online_marketplace_app.model.entity.Order;
import com.bugrates.online_marketplace_app.model.entity.OrderItem;
import com.bugrates.online_marketplace_app.model.entity.Seller;
import com.bugrates.online_marketplace_app.repo.CartItemRepository;
import com.bugrates.online_marketplace_app.repo.OrderItemRepository;
import com.bugrates.online_marketplace_app.repo.OrderRepository;

@Service
public class OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

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
		logger.info("Starting order placement process");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Customer currentUser = (Customer) authentication.getPrincipal();

		logger.debug("Processing order for customer ID: {}", currentUser.getUserId());

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

			logger.info("Order placed successfully with {} items for customer ID: {}", myCartItems.size(), currentUser.getUserId());
			return response;
		}

		logger.warn("Order placement failed - no products in cart for customer ID: {}", currentUser.getUserId());
		throw new Exception("No product in cart !"); // TODO

	}

	public List<OrderResponse> getMyOrders() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Customer currentUser = (Customer) authentication.getPrincipal();

		logger.debug("Fetching orders for customer ID: {}", currentUser.getUserId());
		
		List<Order> myOrders = orderRepository.findAllByCustomer_UserId(currentUser.getUserId());

		List<OrderResponse> myOrdersResponse = myOrders.stream().map(myOrder -> {

			OrderResponse orderResponse = OrderMapper.toDTO(myOrder);

			return orderResponse;

		}).collect(Collectors.toList());

		logger.info("Retrieved {} orders for customer ID: {}", myOrdersResponse.size(), currentUser.getUserId());
		return myOrdersResponse;

	}

	public List<IncomingOrderResponse> getMyIncomingOrders() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Seller currentUser = (Seller) authentication.getPrincipal();

		logger.debug("Fetching incoming orders for seller ID: {}", currentUser.getUserId());

		List<OrderItem> myIncomingOrderItems = orderItemRepository.findAllBySeller_UserId(currentUser.getUserId());

		List<IncomingOrderResponse> incomingOrderResponses = myIncomingOrderItems.stream().map(myIncomingOrderItem -> {
		
			IncomingOrderResponse incomingOrderResponse = IncomingOrderMapper.toIncomingOrderResponse(myIncomingOrderItem);
			
			return incomingOrderResponse;
			
		}).toList();
		
		logger.info("Retrieved {} incoming orders for seller ID: {}", incomingOrderResponses.size(), currentUser.getUserId());
		return incomingOrderResponses;
	}

}
