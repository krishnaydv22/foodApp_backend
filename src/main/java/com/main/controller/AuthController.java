package com.main.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.config.JwtProvider;
import com.main.model.Cart;
import com.main.model.USER_ROLE;
import com.main.model.User;
import com.main.repository.CartRepository;
import com.main.repository.UserRepository;
import com.main.request.LoginRequest;
import com.main.response.AuthResponse;
import com.main.service.CustomerUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;
	
	@Autowired
	private CartRepository cartRepository;
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{
		  
		User isUserExist = userRepository.findByEmail(user.getEmail());
		if(isUserExist != null) {
			throw new Exception("Email is already used with another account ");
			
		}
		
		User createdUser = new User();
		createdUser.setEmail(user.getEmail());
		createdUser.setFullName(user.getFullName());
		createdUser.setRole(user.getRole());
		createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUSer = userRepository.save(createdUser);
		
		Cart cart = new Cart();
		cart.setCustomer(savedUSer);
		cartRepository.save(cart);
		
		 Authentication authentication =  new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		 
		 String jwt = jwtProvider.generateToken(authentication);
		 
		 AuthResponse authResponse = new AuthResponse();
		 authResponse.setJwt(jwt);
		 authResponse.setMessage("Registered successfully");
		 authResponse.setRole(savedUSer.getRole());
		 
		 
		 return new ResponseEntity<>(authResponse,HttpStatus.CREATED);
		 
		
		
		}

	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){
		
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username, password);
		
         String jwt = jwtProvider.generateToken(authentication);
		 
		 AuthResponse authResponse = new AuthResponse();
		 authResponse.setJwt(jwt);
		 authResponse.setMessage("Login successfully");
		 
		 Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		 
		 String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
			  
		 authResponse.setRole(USER_ROLE.valueOf(role));
		 
		
		
		
		
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
		
	}


	private Authentication authenticate(String username, String password) {
		
		UserDetails userdetails = customerUserDetailsService.loadUserByUsername(username);
		
		if(userdetails == null) {
			throw new BadCredentialsException("Invalid username ....");
		}
		
		if(!passwordEncoder.matches(password, userdetails.getPassword())) {
			
			
			throw new BadCredentialsException("Invalid password ...");
		} // use to match encrypted passwords
		
		return new UsernamePasswordAuthenticationToken(userdetails, null,userdetails.getAuthorities());
	}
	
}
