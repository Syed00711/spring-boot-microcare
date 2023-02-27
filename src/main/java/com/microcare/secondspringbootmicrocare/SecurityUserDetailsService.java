package com.microcare.secondspringbootmicrocare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; 


@Service
public class SecurityUserDetailsService implements UserDetailsService { 

	@Autowired
	DatabaseConnection db;
	
   @Override 
   public UserDetails loadUserByUsername(String username) { 
	   
	   Login user =db.getUser(username);
	   
if(user==null) {
	throw new UsernameNotFoundException("No User Found");
}

         return user; 
   } 

}
