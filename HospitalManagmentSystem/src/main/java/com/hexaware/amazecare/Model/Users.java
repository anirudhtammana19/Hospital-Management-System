package com.hexaware.amazecare.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;


@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long userId;

    @Column(length = 50, unique = true, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    @Pattern(regexp="^(?=.*[A-Z])(?=.*[!@#$%^&*()<>,.?\":{}|]).{7,}$",
    message="Password must be at least 7 characters long, contain at least one uppercase letter, and one special character.")
    private String password;

    @Column(name = "role", length = 7, nullable = false)
    private String role;

	public Users() {
		super();
	}

	public Users(Long userId, String username, String password, String role) {
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}

}
