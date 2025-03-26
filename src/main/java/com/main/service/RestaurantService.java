package com.main.service;

import java.util.List;

import com.main.dto.RestaurantDto;
import com.main.model.Restaurant;
import com.main.model.User;
import com.main.request.CreateRestaurantRequest;

public interface  RestaurantService {
	
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
	
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedResraurant)  throws Exception;
	
	public void deleteRestauarant(Long restaurnatId) throws Exception;
	
	public List<Restaurant> getAllRestaurants();
	
	public List<Restaurant> searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws Exception;
	
	public Restaurant getRestaurantByUserId(Long userId) throws Exception;
	
	public RestaurantDto addToFavoites(Long restaurantId, User user) throws Exception;
	
	public Restaurant updateRestaurantStatus(Long id) throws Exception;
	
	
	

}
