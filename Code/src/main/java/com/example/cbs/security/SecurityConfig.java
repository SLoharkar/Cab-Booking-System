package com.example.cbs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.cbs.services.impl.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailService customUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.csrf()
				.disable()
				.authorizeHttpRequests()
				.antMatchers("/", "/user/login/**", "/user/add/**", "/user/forgotPassword/**").permitAll()
				.antMatchers("/cab/book/**", "/cab/search/**").hasRole("NORMAL")
				.antMatchers("/**").hasRole("ADMIN")
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Admin").password(passwordEncoder().encode("Admin")).roles("ADMIN");
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
