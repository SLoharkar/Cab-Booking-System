package com.example.cbs.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.cbs.exceptions.DataNotFoundException;
import com.example.cbs.models.CustomUserDetail;
import com.example.cbs.models.UserRegistration;
import com.example.cbs.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserRegistration user=userRepo.findByUsername(username);
		if(user==null)
		{
			throw new DataNotFoundException("please enter valid username");
		}
		
		return new CustomUserDetail(user);
	}

}
