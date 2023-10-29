package com.example.cbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.cbs.models.UserRegistration;

@Repository
public interface UserRepo extends CrudRepository<UserRegistration, Integer> {

	// Get All Users
	@Query("select ur from UserRegistration ur")
	List<UserRegistration> getAllUser();

	// Find By Username & Password
	public UserRegistration findByUsernameAndPassword(String username, String password);

	// Find By Username
	UserRegistration findByUsername(String username);
}
