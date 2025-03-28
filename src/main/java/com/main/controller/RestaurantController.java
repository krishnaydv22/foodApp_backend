package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.RestaurantDto;
import com.main.model.Restaurant;
import com.main.model.User;
import com.main.request.CreateRestaurantRequest;
import com.main.service.RestaurantService;
import com.main.service.UserService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restuarantService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/search")
	public ResponseEntity<List<Restaurant> > searchRestaurant(
			@RequestHeader("Authorization") String jwt,
			@RequestParam String keyword) throws Exception{
		  
		User user = userService.findUserByJwtToken(jwt);
		List<Restaurant> restaurant = restuarantService.searchRestaurant(keyword);
		
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
		
	}
	
	@GetMapping()
	public ResponseEntity<List<Restaurant> > getAllRestaurant(
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		  
		User user = userService.findUserByJwtToken(jwt);
		List<Restaurant> restaurant = restuarantService.getAllRestaurants();
		
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findRestaurantById(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id
			) throws Exception{
		  
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restuarantService.findRestaurantById(id);
		
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/{id}/add-favorites")
	public ResponseEntity<RestaurantDto> addToFavorites(
			@RequestHeader("Authorization") String jwt,
			@PathVariable Long id
			) throws Exception{
		  
		User user = userService.findUserByJwtToken(jwt);
		RestaurantDto restaurant = restuarantService.addToFavoites(id,user);
		
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
		
	}


}
