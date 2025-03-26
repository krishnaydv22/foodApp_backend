package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Restaurant;
import com.main.model.User;
import com.main.request.CreateRestaurantRequest;
import com.main.response.MessageResponse;
import com.main.service.RestaurantService;
import com.main.service.UserService;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
	
	@Autowired
	private RestaurantService restuarantService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping()
	public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req,@RequestHeader("Authorization") String jwt) throws Exception{
		  
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restuarantService.createRestaurant(req, user);
		
		
		return new ResponseEntity<Restaurant>(restaurant,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception{
		  
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restuarantService.updateRestaurant(id, req);
		
		
		return new ResponseEntity<Restaurant>(restaurant,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteRestaurant(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception{
		  
		User user = userService.findUserByJwtToken(jwt);
		 restuarantService.deleteRestauarant(id);
		
		  MessageResponse messageResponse = new MessageResponse();
		  messageResponse.setMessage("Restaurant deleted Succesfully ");
		return new ResponseEntity<MessageResponse>(messageResponse,HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}/status")
	public ResponseEntity<Restaurant> updateRestaurantStatus(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id) throws Exception{
		  
		User user = userService.findUserByJwtToken(jwt);
		 Restaurant restaurant = restuarantService.updateRestaurantStatus(id);
		
		 
		return new ResponseEntity<Restaurant>(restaurant,HttpStatus.OK);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<Restaurant> findRestaurantByUserId(
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		  
		User user = userService.findUserByJwtToken(jwt);
		 Restaurant restaurant = restuarantService.getRestaurantByUserId(user.getId());
		
		 
		return new ResponseEntity<Restaurant>(restaurant,HttpStatus.OK);
		
	}
	
	


}
