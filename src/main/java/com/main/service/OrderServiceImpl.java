package com.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Address;
import com.main.model.Cart;
import com.main.model.CartItem;
import com.main.model.Order;
import com.main.model.OrderItem;
import com.main.model.Restaurant;
import com.main.model.User;
import com.main.repository.AddressRepository;
import com.main.repository.OrderItemRepository;
import com.main.repository.OrderRepository;
import com.main.repository.RestaurantRepository;
import com.main.repository.UserRepository;
import com.main.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CartService cartService;

	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		
		Address shippedAddress = order.getDeliveryAddress();
		
		Address savedAddress = addressRepository.save(shippedAddress);
		if(!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}
		
		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
		
		
		Order createdOrder = new Order();
		createdOrder.setCustomer(user);
		createdOrder.setCreatedAt(new Date());
		createdOrder.setRestaurant(restaurant);
		createdOrder.setDeliveryAddress(savedAddress);
		createdOrder.setOrderStatus("PENDING");
		
		Cart cart = cartService.findCartByUserId(user.getId());
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for(CartItem cartItem :  cart.getItem()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredients(cartItem.getIngreadients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			
			OrderItem savedOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
			
		}
		Long totalPrice = cartService.calculateCartTotals(cart);
		
		createdOrder.setItems(orderItems);
		createdOrder.setTotalPrice(totalPrice);
		
		Order savedOrder = orderRepository.save(createdOrder);
		
		restaurant.getOrders().add(savedOrder);
		
		
		
//		createdOrder.
		return createdOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		Order order = findOrderById(orderId);
		if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") || 
				orderStatus.equals("COMPLETED")
				|| orderStatus.equals("PENDING")) {
			order.setOrderStatus(orderStatus);
			
			return orderRepository.save(order);
		}
		
		throw new Exception("Please select valid order status ");
			
		
		
		
		
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		
		Order order = findOrderById(orderId);
	   orderRepository.deleteById(orderId);
		
		
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {
		
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
		List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
		
		if(orderStatus!= null) {
			orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
		}
		
		return orders;
	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {
		Optional<Order> byId = orderRepository.findById(orderId);
		if(byId.isEmpty()) {
			throw new Exception("Order not found with id " + orderId);
		}
		return byId.get();
	}

}
