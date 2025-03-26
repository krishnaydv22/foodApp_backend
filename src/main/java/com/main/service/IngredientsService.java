package com.main.service;

import java.util.List;

import com.main.model.IngredientCategory;
import com.main.model.IngredientsItem;

public interface IngredientsService {
	
	public IngredientCategory createIngredientCategory(String name,Long restaurantId) throws Exception;
	
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
	
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;
	
	public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName,Long categoryId) throws Exception;
	
	public List<IngredientsItem> findRestaurantsIngredients(Long id);
	
	public IngredientsItem updateStock(Long id) throws Exception;

}
