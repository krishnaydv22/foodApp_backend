package com.main.service;

import java.util.List;

import com.main.model.Event;
import com.main.model.Restaurant;

public interface EventService {
	
	public Event createEvent(Event event, Long restaurantId);
	
	public List<Event> getRestaurantEvent(Long restaurantId);
	
	public List<Event> getAllEvents();
	
	public Event findByEventId(Long id) throws Exception;
	
	public void deleteEvent(Long id) throws Exception;
	
	
	
	

}
