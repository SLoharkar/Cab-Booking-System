package com.example.cbs.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cbs.exceptions.pojo.SucessResponse;
import com.example.cbs.models.UserRegistration;
import com.example.cbs.services.UserServices;

@RestController
@RequestMapping("/user")
public class UserManagementController {

    @Autowired
    UserServices userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserRegistration login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @PostMapping("/forgotPassword")
    @ResponseStatus(HttpStatus.OK)
    public SucessResponse forgotPassword(@RequestParam String username, @RequestParam String password) {
        return userService.forgotPassword(username, password);
    }

}
