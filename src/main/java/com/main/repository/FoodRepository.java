package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.main.model.Food;

import jakarta.transaction.Transactional;

public interface FoodRepository extends JpaRepository<Food, Long>{
	
	List<Food> findByRestaurantId(Long restaurantId);
	
	@Modifying
	 @Transactional
	@Query("DELETE FROM Food")
	void deleteAllFood();
	
	
	@Query("SELECT f FROM Food f LEFT JOIN f.foodCategory fc " +
		       "WHERE lower(f.name) LIKE lower(concat('%', :query, '%')) " +
		       "OR lower(fc.name) LIKE lower(concat('%', :query, '%'))")
	List<Food> searchFood(String Query);
	
	
	

}
