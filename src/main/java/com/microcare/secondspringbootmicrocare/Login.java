package com.microcare.secondspringbootmicrocare;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Login implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	 private boolean accountNonLocked;
	 
	 public Login() { 
	     
	   } 
	 public Login(String username, String password, boolean accountNonLocked) { 
	      this.username = username; 
	      this.password = password; 
	      this.accountNonLocked = accountNonLocked; 
	   } 
	public String getUser_name() {
		return username;
	}

	public void setUser_name(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return  true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
