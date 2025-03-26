package com.main.service;

import java.util.List;

import com.main.model.Category;

public interface CategoryService {
	
	public Category createCatogory(String name,Long userId) throws Exception;
	
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception;
	
	public Category findCategoryById(Long id) throws Exception;
	

}
