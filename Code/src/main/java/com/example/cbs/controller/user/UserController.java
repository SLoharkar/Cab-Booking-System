package com.example.cbs.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cbs.models.UserRegistration;
import com.example.cbs.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public UserRegistration userRegistration(@Valid @RequestBody UserRegistration userRegistration) {
        return userService.userRegistration(userRegistration);
    }

}
