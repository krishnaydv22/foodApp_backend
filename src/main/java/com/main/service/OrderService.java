package com.main.service;

import java.util.List;

import com.main.model.Order;
import com.main.model.User;
import com.main.request.OrderRequest;

public interface OrderService {
	
	public Order createOrder(OrderRequest order, User user) throws Exception ;
	
	public Order updateOrder(Long orderId, String orderStatus) throws Exception;
	
	public void cancelOrder(Long orderId) throws Exception;
	
	public List<Order> getUsersOrder(Long userId) throws Exception;
	
	public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;
	
	public Order findOrderById(Long orderId) throws Exception;

}
