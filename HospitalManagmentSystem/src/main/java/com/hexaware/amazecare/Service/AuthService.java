package com.hexaware.amazecare.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.DTO.UsersDTO;
import com.hexaware.amazecare.Security.JWTService;

@Service
public class AuthService {


	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	UserInfoUserDetailsService userService;
	
	Logger logger= LoggerFactory.getLogger(AuthService.class);
	
	public String authenticateUser(UsersDTO user) {
		
		logger.info("Request initiated to Login by User: "+user.getUsername());
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			logger.info("Login Successfull!");
			return jwtService.generateToken(userService.loadUserByUsername(user.getUsername()));
		}else {
			logger.warn("Login Failed: Invalid Credentials!");
			throw new UsernameNotFoundException("Invalid Username and Password");
		}
	}

}