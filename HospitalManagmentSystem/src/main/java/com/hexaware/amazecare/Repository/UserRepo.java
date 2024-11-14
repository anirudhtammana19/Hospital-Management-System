package com.hexaware.amazecare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Model.Users.Role;

public interface UserRepo extends JpaRepository<Users,Integer> {

	public Users findByUsername(String username);
	
	public Users findByRole(Role role);
		
}


