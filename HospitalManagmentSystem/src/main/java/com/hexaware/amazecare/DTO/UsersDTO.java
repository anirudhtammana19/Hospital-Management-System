package com.hexaware.amazecare.DTO;

import com.hexaware.amazecare.Model.Users.Role;

public class UsersDTO {

    private Long userId;
    private String username;
    private String password;
    private Role role;

    
	public UsersDTO() {
		super();
	}

	public UsersDTO(Long userId, String username, String password, Role role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}

}
