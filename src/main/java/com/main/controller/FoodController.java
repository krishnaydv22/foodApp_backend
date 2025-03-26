package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Food;
import com.main.model.Restaurant;
import com.main.model.User;
import com.main.request.CreateFoodRequest;
import com.main.service.FoodService;
import com.main.service.RestaurantService;
import com.main.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {
	
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		
		
		List<Food> foods= foodService.searchFood(name);
		
		
		
		return new ResponseEntity<>(foods,HttpStatus.OK);
	}
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Food>> getRestaurantFood(
			@RequestParam(required = false) Boolean vegitarian,
			@RequestParam(required = false) Boolean seasonal,
			@RequestParam(required = false) Boolean nonveg,
			@PathVariable Long restaurantId,
			@RequestParam(required = false) String food_category,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		
		
		
		List<Food> foods= foodService.getRestaurantsFood(restaurantId, vegitarian, nonveg, seasonal, food_category);
		
		
		
		return new ResponseEntity<>(foods,HttpStatus.OK);
	}
	
	
	
	
	

}
