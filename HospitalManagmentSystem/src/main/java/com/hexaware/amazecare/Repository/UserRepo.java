package com.hexaware.amazecare.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Users;
import com.hexaware.amazecare.Model.Users.Role;

public interface UserRepo extends JpaRepository<Users,Long> {

	public Optional<Users> findByUsername(String username);
	
	public Users findByRole(Role role);
		
}


