package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Event;
import com.main.model.IngredientCategory;
import com.main.model.User;
import com.main.service.EventService;
import com.main.service.UserService;

@RestController
@RequestMapping("/api")
public class EventController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;
	
	
	@PostMapping("/admin/events/restaurant/{restaurantId}")
	public ResponseEntity<Event> createEvent(@RequestBody Event event, @RequestHeader("Authorization") String jwt,@PathVariable Long restaurantId) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		Event event2 = eventService.createEvent(event, restaurantId);
		
		return new ResponseEntity<>(event2,HttpStatus.CREATED);
		
		
		
		
		
	}
	
	@GetMapping("/admin/events/restaurant/{restaurantId}")
	public ResponseEntity<List<Event>> getRestaurantEvent( @RequestHeader("Authorization") String jwt,@PathVariable Long restaurantId) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		List<Event> event2 = eventService.getRestaurantEvent(restaurantId);
		
		return new ResponseEntity<>(event2,HttpStatus.OK);
		
		
		
		
		
	}
	
	@DeleteMapping("/admin/events/{eventId}")
	public ResponseEntity<Void> deleteEvent( @RequestHeader("Authorization") String jwt,@PathVariable Long eventId) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		eventService.deleteEvent(eventId);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("/events")
	public ResponseEntity<List<Event>> getAllEvent( @RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwtToken(jwt);
		List<Event> allEvents = eventService.getAllEvents();
		
		return new ResponseEntity<>(allEvents,HttpStatus.OK);
		
	}

}
