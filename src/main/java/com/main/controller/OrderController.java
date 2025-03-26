package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.Order;
import com.main.model.User;
import com.main.request.OrderRequest;
import com.main.response.PaymentResponse;
import com.main.service.OrderService;
import com.main.service.PaymentService;
import com.main.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/order")
	public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest req,@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		Order order = orderService.createOrder(req, user);
		
		PaymentResponse res = paymentService.createPaymentLink(order);
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@GetMapping("/order/user")
	public ResponseEntity<List<Order> > getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		List<Order> orders = orderService.getUsersOrder(user.getId());
		
		return new ResponseEntity<>(orders,HttpStatus.CREATED);
	}


}
