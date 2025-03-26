package com.main.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Category;
import com.main.model.Food;
import com.main.model.Restaurant;
import com.main.repository.FoodRepository;
import com.main.request.CreateFoodRequest;

import jakarta.transaction.Transactional;

@Service
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	private FoodRepository foodRepository;
	
	
	
	
	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
		Food food = new Food();
		
	
		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setDescription(req.getDescription());
		
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredients(req.getIngredients());
		food.setSeasonal(req.isSeasional());
		food.setVegitarian(req.isVegitarian());
		food.setCreationDate(new Date());
		Food savedFood = foodRepository.save(food);
		restaurant.getFood().add(savedFood);
		
		
		return savedFood;
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {
		
		Food food = findFoodById(foodId);
		food.setRestaurant(null);
		foodRepository.save(food);
//		foodRepository.deleteById(foodId); we are keeping food data in db
		
		
	}

	@Override
	public List<Food> getRestaurantsFood(Long restuarantId, 
			Boolean isVegitarian, 
			Boolean inNonveg, 
			Boolean isSeasonal,
			String foodCategory) {
		
		

		
		List<Food> foods = foodRepository.findByRestaurantId(restuarantId);
		if(Boolean.TRUE.equals(isVegitarian)) {
			foods = filterByVegitarian(foods,isVegitarian);
		}
		
		if(Boolean.TRUE.equals(inNonveg)) {
			foods = filterByNonveg(foods,inNonveg);
		}
		
		if(Boolean.TRUE.equals(isSeasonal)) {
			foods = filterBySeasonal(foods,isSeasonal);
		}
		if(foodCategory != null && !foodCategory.equals("")) {
			foods = filterByCategory(foods,foodCategory);
			
		}
		
		
		
		return foods;
	}

	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
		return foods.stream().filter(food ->{
			if(food.getFoodCategory() != null) {
				return food.getFoodCategory().getName().equalsIgnoreCase(foodCategory);
			}
			
			return false;
			
		}).collect(Collectors.toList());
		
		
		
	}

	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
		
		return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
	}

	private List<Food> filterByNonveg(List<Food> foods, boolean isVegitarian) {
		
		return foods.stream().filter(food -> food.isVegitarian() == false).collect(Collectors.toList());
	}

	private List<Food> filterByVegitarian(List<Food> foods, boolean isVegitarian) {
		
		return foods.stream().filter(food -> food.isVegitarian() == isVegitarian).collect(Collectors.toList());
	}

	@Override
	public List<Food> searchFood(String keyword) {
		
		return foodRepository.searchFood(keyword);
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> byId = foodRepository.findById(foodId);
		if(byId.isEmpty()) {
			throw new Exception("Food not exist ...");
		}
		
		return byId.get();
	}

	@Override
	public Food updateAvailabilityStatus(Long foodId) throws Exception {
		Food food = findFoodById(foodId);
		food.setAvailable(!food.isAvailable());
		
		
		return foodRepository.save(food);
	}

	@Override
	@Transactional
	public void deleteAllFood() {
		 foodRepository.deleteAllFood();
		
	}



}
