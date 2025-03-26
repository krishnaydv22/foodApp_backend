package com.main.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Event;
import com.main.model.Restaurant;
import com.main.repository.EventRepository;
import com.main.repository.RestaurantRepository;

@Service
public class EventServiceImpl implements EventService{
	
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private RestaurantService restaurantService;

	@Override
	public Event createEvent(Event event, Long restaurantId) {
		
		try {
			

			Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
			
			Event evt = new Event();
			evt.setName(event.getName());
			evt.setImage(event.getImage());
			evt.setLocation(event.getLocation());
			evt.setEndsAt(event.getEndsAt());
			evt.setStartedAt(event.getStartedAt());
			
			evt.setRestaurant(restaurant);
			
			
			
			return eventRepository.save(evt);
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return null;
	}

	@Override
	public List<Event> getRestaurantEvent(Long restaurantId) {
		
		List<Event> byRestaurantId = eventRepository.findByRestaurantId(restaurantId);
		
		return byRestaurantId;
	}

	@Override
	public List<Event> getAllEvents() {
		
		return eventRepository.findAll();
	}

	
	@Override
	public Event findByEventId(Long id) throws Exception {
		
		Optional<Event> byId = eventRepository.findById(id);
		if(byId.isEmpty()) {
			throw new Exception("event not found ");
		}
		
		return byId.get();
	}

	@Override
	public void deleteEvent(Long id) throws Exception {
		// TODO Auto-generated method stub
		Event byEventId = findByEventId(id);
		eventRepository.deleteById(id);
		
	}

}
