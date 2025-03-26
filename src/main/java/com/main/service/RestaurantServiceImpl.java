package com.main.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.RestaurantDto;
import com.main.model.Address;
import com.main.model.Restaurant;
import com.main.model.User;
import com.main.repository.AddressRepository;
import com.main.repository.RestaurantRepository;
import com.main.repository.UserRepository;
import com.main.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restuarantRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
		
		Address address = addressRepository.save(req.getAddress());
		
		Restaurant restaurant = new Restaurant();
		restaurant.setAddress(address);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpeningHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwner(user);
		
		
		
		
		return restuarantRepository.save(restaurant);
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedResraurant) throws Exception {
		
		Restaurant restaurant = findRestaurantById(restaurantId);
		if(restaurant.getCuisineType() != null) {
			restaurant.setCuisineType(updatedResraurant.getCuisineType()); 
		}
		
		if(restaurant.getDescription() != null) {
			restaurant.setDescription(updatedResraurant.getDescription());
		}
		
		if(restaurant.getName() != null) {
			restaurant.setName(updatedResraurant.getName());
		}
		restaurant.setImages(updatedResraurant.getImages());
		
		
		return restuarantRepository.save(restaurant);
	}

	@Override
	public void deleteRestauarant(Long restaurnatId) throws Exception {
		
		Restaurant restaurant = findRestaurantById(restaurnatId);
		restuarantRepository.delete(restaurant);
		
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		return restuarantRepository.findAll();
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		
		return restuarantRepository.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant findRestaurantById(Long id) throws Exception {
		Optional<Restaurant> byId = restuarantRepository.findById(id);
		if(byId.isEmpty()) {
			throw new Exception("Restaurnat not found with id " + id);
		}
		
		
		return byId.get();
	}

	@Override
	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
		Restaurant restaurant = restuarantRepository.findByOwnerId(userId);
		if(restaurant == null) {
			throw new Exception("restaurnat not found with owner id " + userId);
		}
		
		return restaurant;
	}

	@Override
	public RestaurantDto addToFavoites(Long restaurantId, User user) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		
		RestaurantDto dto = new RestaurantDto();
		dto.setDescription(restaurant.getDescription());
		dto.setImages(restaurant.getImages());
		dto.setTitle(restaurant.getName());
		dto.setId(restaurant.getId());
		
		
		boolean isFavorited = false;
		List<RestaurantDto> favorites = user.getFavorites();
		
		for(RestaurantDto favorite : favorites) {
			if(favorite.getId().equals(restaurantId)) {
			isFavorited = true;
			break;
			}
		}
		if(isFavorited) {
			favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
		}else {
			favorites.add(dto);
		}
		
//		user.setFavorites(favorites);
		
	
		
		userRepository.save(user);
		
		return dto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		Restaurant restaurant = findRestaurantById(id);
		restaurant.setOpen(!restaurant.isOpen());
		
		return restuarantRepository.save(restaurant);
	}

}
