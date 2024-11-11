package com.hexaware.amazecare.DTO;

import com.hexaware.amazecare.Model.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class UsersDTO {

    private Long userId;

    @Email
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()<>,.?\":{}|]).{7,}$",
             message = "Password must be at least 7 characters long, contain at least one uppercase letter, and one special character.")
    private String password;

    private Users.Role role;

    
    public UsersDTO() {
    	super();
    }
    // Constructor
    public UsersDTO(Long userId, String username, String password, Users.Role role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
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

    public Users.Role getRole() {
        return role;
    }

    public void setRole(Users.Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role + "]";
    }
}
