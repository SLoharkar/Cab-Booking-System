package com.example.cbs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	

    @RequestMapping("/")
    public String Home() {
 
    	
        return "Welcome to Cab Booking System";
    }

}
