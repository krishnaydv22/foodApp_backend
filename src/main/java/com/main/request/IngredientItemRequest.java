package com.main.request;

import lombok.Data;

@Data
public class IngredientItemRequest {
	
	private String name;
	private Long categoryId;
	
	private Long restaurantId;

}
