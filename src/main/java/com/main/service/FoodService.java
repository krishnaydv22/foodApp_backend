package com.main.service;

import java.util.List;

import com.main.model.Category;
import com.main.model.Food;
import com.main.model.Restaurant;
import com.main.request.CreateFoodRequest;

import jakarta.transaction.Transactional;

public interface FoodService {
	
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
	
	public void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood(Long restuarantId, Boolean isVegitarian,
			Boolean inNonveg, 
			Boolean isSeasonal,
			                          String foodCategory);
	
//	public List<Food> getRestaurantsAllFood(Long restuarantId
//          );

		
	public List<Food> searchFood(String keyword);

	public Food findFoodById(Long foodId) throws Exception;

	public Food updateAvailabilityStatus(Long foodId) throws Exception;
	
//	@Transactional
	 public void deleteAllFood();
	        
	    
	

}
