package com.hexaware.amazecare.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;


@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    @Column(length = 50, unique = true, nullable = false)
    @Email
    private String username;

    //@Column(length = 25, nullable = false)
    @Pattern(regexp="^(?=.*[A-Z])(?=.*[!@#$%^&*()<>,.?\":{}|]).{7,}$",
    message="Password must be at least 7 characters long, contain at least one uppercase letter, and one special character.")
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 7, nullable = false)
    private Role role;

    public enum Role {
        PATIENT, DOCTOR, ADMIN
    }
    
	public Users() {
		super();
	}

	public Users(Long userId, String username, String password, Role role) {
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
