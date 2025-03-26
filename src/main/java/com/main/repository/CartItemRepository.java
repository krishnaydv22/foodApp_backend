package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.Cart;
import com.main.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	
}
