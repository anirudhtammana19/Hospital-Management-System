package com.hexaware.amazecare.DTO;

import com.hexaware.amazecare.Model.Users.Role;

public class ResponseDTO {

    private String jwt;
    private long userid;
    private String username;
    private String role;

    
	public ResponseDTO() {
		super();
	}



	public ResponseDTO(String jwt, long userid, String username, String role) {
		super();
		this.jwt = jwt;
		this.userid = userid;
		this.username = username;
		this.role = role;
	}



	public long getUserid() {
		return userid;
	}



	public void setUserid(long userid) {
		this.userid = userid;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
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
