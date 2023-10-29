package com.example.cbs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class UserRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	@NotNull(message = "Username should not be null")
	String username;

	@NotNull(message = "Password should not be null")
	String password;

	@NotNull(message = "Name should not be null")
	String name;

	@NotNull(message = "Email should not be null")
	@Email(message = "Enter valid email")
	String email;

	String role;

}
