package com.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class HomeController {
	

	
	@GetMapping("/")
	public String homeController(){
		return "Hello test";
	}
	

}
