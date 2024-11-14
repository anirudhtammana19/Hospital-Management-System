package com.hexaware.amazecare.DTO;

import com.hexaware.amazecare.Model.Users.Role;

public class UsersDTO {

    private String username;
    private String password;

    
	public UsersDTO() {
		super();
	}

	public UsersDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + "]";
	}

}
