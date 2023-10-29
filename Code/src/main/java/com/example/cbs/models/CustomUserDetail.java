package com.example.cbs.models;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetail implements UserDetails {

	private UserRegistration userRegistration;

	public CustomUserDetail(UserRegistration userRegistration) {
		this.userRegistration = userRegistration;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generaSimpleGrantedAuthorityhSet<SimpleGrantedAuthority> set = new
		// HashSet<>();
		HashSet<SimpleGrantedAuthority> set = new HashSet<>();
		set.add(new SimpleGrantedAuthority(userRegistration.getRole()));

		return set;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userRegistration.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userRegistration.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
