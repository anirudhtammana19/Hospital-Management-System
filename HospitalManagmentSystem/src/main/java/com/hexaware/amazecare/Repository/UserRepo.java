package com.hexaware.amazecare.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.amazecare.Model.Users;

public interface UserRepo extends JpaRepository<Users,Integer> {

	public Users findByUsername(String username);
		
}


