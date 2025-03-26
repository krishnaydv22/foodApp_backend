package com.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.IngredientCategory;
import com.main.model.IngredientsItem;
import com.main.model.Restaurant;
import com.main.repository.IngredientCategoryRepository;
import com.main.repository.IngredientsItemRepository;

@Service
public class IngredientsServiceImpl implements IngredientsService{
	
	@Autowired
	private IngredientsItemRepository ingredientItemRepository;
	
	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;
	
	@Autowired
	private RestaurantService restaurantService;

	@Override
	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
		
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		
		IngredientCategory category = new IngredientCategory();
		category.setName(name);
		category.setRestaurant(restaurant);
		
		return ingredientCategoryRepository.save(category);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
		 
		Optional<IngredientCategory> byId = ingredientCategoryRepository.findById(id);
		if(byId.isEmpty()) {
			throw new Exception("ingredient category not found ");
		}
		return byId.get();
	}

	@Override
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
		restaurantService.findRestaurantById(id);
		
		return ingredientCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		
		IngredientCategory category = findIngredientCategoryById(categoryId);
		
		IngredientsItem item = new IngredientsItem();
		item.setName(ingredientName);
		item.setRestaurant(restaurant);
		item.setCategory(category);

		IngredientsItem savedItem = ingredientItemRepository.save(item);
		category.getIngredients().add(savedItem);
		
		return savedItem;
	}

	@Override
	public List<IngredientsItem> findRestaurantsIngredients(Long id) {
		
		return ingredientItemRepository.findByRestaurantId(id);
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		Optional<IngredientsItem> byId = ingredientItemRepository.findById(id);
		if(byId.isEmpty()) {
			throw new Exception("Ingredient not found with id " + id);
		}
		
		IngredientsItem ingredientsItem = byId.get();
		ingredientsItem.setInStock(!ingredientsItem.isInStock());
		
		
		return ingredientItemRepository.save(ingredientsItem);
	}

}
