package com.hexaware.amazecare.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.amazecare.DTO.ResponseDTO;
import com.hexaware.amazecare.DTO.UsersDTO;
import com.hexaware.amazecare.Service.AuthService;



@RestController
@RequestMapping("/api")
public class AuthController {

	
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody UsersDTO user) {
		
		ResponseDTO login= authService.authenticateUser(user);
		return new ResponseEntity<>(login,HttpStatus.ACCEPTED);
	}
	
}
