package com.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Category;
import com.main.model.Restaurant;
import com.main.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RestaurantService restaurantService;

	@Override
	public Category createCatogory(String name, Long userId) throws Exception {
		
		Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
		
		Category category = new Category();
		category.setName(name);
		
		category.setRestaurant(restaurant);
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
		// TODO Auto-generated method stub
		Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
		return categoryRepository.findByRestaurantId(id);
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {
		Optional<Category> byId = categoryRepository.findById(id);
		if(byId.isEmpty()) {
			throw new Exception("category not found ");
		}
		return byId.get();
	}

}
