package com.main.request;

import java.util.List;

import com.main.model.Category;
import com.main.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {
	
	private String name;
	
	private String description;
	
	private Long price;
	
	private Category category;
	
	private List<String> images;
	
	private Long restaurantId;
	
	private boolean vegitarian;
	
	private boolean seasional;
	
	private List<IngredientsItem> ingredients;

}
