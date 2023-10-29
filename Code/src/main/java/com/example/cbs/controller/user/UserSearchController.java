package com.example.cbs.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cbs.models.UserRegistration;
import com.example.cbs.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserSearchController {

	@Autowired
	UserServices userService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	private List<UserRegistration> getUser() {
		return userService.getUser();
	}

}
