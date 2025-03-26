package com.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.config.JwtProvider;
import com.main.exception.UserNotFoundException;
import com.main.model.User;
import com.main.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserByJwtToken(String jwt) throws Exception {
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		User user = findUserByEmail(email);
		
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws UserNotFoundException{
		
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UserNotFoundException("User not found ");
		}
		
		return user;
	}

}
