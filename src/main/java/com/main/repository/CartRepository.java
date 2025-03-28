package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	
	public Cart findByCustomerId(Long userId);

}
