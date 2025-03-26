package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Food;
import com.main.model.Restaurant;
import com.main.model.User;
import com.main.request.CreateFoodRequest;
import com.main.response.MessageResponse;
import com.main.service.FoodService;
import com.main.service.RestaurantService;
import com.main.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		Restaurant restaurant =  restaurantService.findRestaurantById(req.getRestaurantId());
		
		Food food= foodService.createFood(req, req.getCategory(), restaurant);
		
		
		
		return new ResponseEntity<Food>(food,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		
		
		foodService.deleteFood(id);
		
		MessageResponse message = new MessageResponse();
		message.setMessage("food deleted succesfully ");
		return new ResponseEntity<MessageResponse>(message,HttpStatus.OK);
	}
	
	 @DeleteMapping("/food")
	    public ResponseEntity<String> deleteAllFood() {
	        foodService.deleteAllFood();
	        return ResponseEntity.ok("All food records deleted successfully.");
	    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long id,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		
		
		Food food = foodService.updateAvailabilityStatus(id);
		
		
		return new ResponseEntity<Food>(food,HttpStatus.OK);
	}


}
