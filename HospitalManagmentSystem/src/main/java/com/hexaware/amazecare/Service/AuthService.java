package com.hexaware.amazecare.Service;

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
	
	public String authenticateUser(UsersDTO user) {
		//System.out.println("Provided Details: " + user.getUsername()+"Pass: "+user.getPassword());
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(userService.loadUserByUsername(user.getUsername()));
		}else {
			throw new UsernameNotFoundException("Invalid Username and Password");
		}
	}

}
