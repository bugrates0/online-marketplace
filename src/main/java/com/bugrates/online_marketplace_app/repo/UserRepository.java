package com.bugrates.online_marketplace_app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bugrates.online_marketplace_app.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByeMailAddress(String eMailAddress);
	
}
