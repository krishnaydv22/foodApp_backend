package com.main.request;

import com.main.model.Address;

import lombok.Data;

@Data
public class OrderRequest {
	
	private Long restaurantId;
	
	private Address deliveryAddress;

}
