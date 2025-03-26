package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.IngredientsItem;

public interface IngredientsItemRepository extends JpaRepository<IngredientsItem, Long> {
	
	List<IngredientsItem> findByRestaurantId(Long id);

}
