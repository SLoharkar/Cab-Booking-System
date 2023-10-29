package com.example.cbs.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.cbs.exceptions.pojo.SucessResponse;
import com.example.cbs.services.UserServices;

@RestController
@RequestMapping("/user/email")
public class EmailController {

    @Autowired
    UserServices userService;

    @PostMapping("/sendEmail")
    @ResponseStatus(HttpStatus.OK)
    public SucessResponse sendEmail() {
        return userService.sendEmail();
    }

    @PostMapping("/verifyEmail/{otp}")
    @ResponseStatus(HttpStatus.OK)
    public SucessResponse verifyEmail(@PathVariable int otp) {
        return userService.verifyEmail(otp);
    }

}
