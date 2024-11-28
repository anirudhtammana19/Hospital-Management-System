package com.hexaware.amazecare.DTO;

import com.hexaware.amazecare.Model.Users.Role;

public class ResponseDTO {

    private String jwt;
    private String role;

    
	public ResponseDTO() {
		super();
	}


	public ResponseDTO(String jwt, String role) {
		super();
		this.jwt = jwt;
		this.role = role;
	}


	public String getJwt() {
		return jwt;
	}


	public void setJwt(String jwt) {
		this.jwt = jwt;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "ResponseDTO [jwt=" + jwt + ", role=" + role + "]";
	}

	
}
