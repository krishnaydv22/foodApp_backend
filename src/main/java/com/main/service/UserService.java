package com.main.service;

import com.main.exception.UserNotFoundException;
import com.main.model.User;

public interface UserService {
	
	public User findUserByJwtToken(String jwt) throws Exception;
	
	public User findUserByEmail(String email) throws UserNotFoundException;

}
