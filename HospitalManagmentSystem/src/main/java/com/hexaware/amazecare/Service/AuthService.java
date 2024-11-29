package com.hexaware.amazecare.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.amazecare.DTO.ResponseDTO;
import com.hexaware.amazecare.DTO.UsersDTO;
import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Repository.UserRepo;
import com.hexaware.amazecare.Security.JWTService;

@Service
public class AuthService {


	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserInfoUserDetailsService userService;
	
	Logger logger= LoggerFactory.getLogger(AuthService.class);
	
	public ResponseDTO authenticateUser(UsersDTO user) {
		
		logger.info("Request initiated to Login by User: "+user.getUsername());
		
		Users u=userRepo.findByUsername(user.getUsername()).orElse(null);
		Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated() && u!=null) {
			logger.info("Login Successfull!");
			ResponseDTO response=new ResponseDTO();
			String jwt=jwtService.generateToken(userService.loadUserByUsername(user.getUsername()));
			response.setJwt(jwt);
			response.setUserid(u.getUserId());
			response.setUsername(u.getUsername());
			response.setRole(u.getRole().name());
			return response;
		}else {
			logger.warn("Login Failed: Invalid Credentials!");
			throw new UsernameNotFoundException("Invalid Username and Password");
		}
	
	}

}
