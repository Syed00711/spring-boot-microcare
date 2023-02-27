package com.microcare.secondspringbootmicrocare;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 
@SuppressWarnings("deprecation")
@Configuration 
public class ApplicationConfig extends WebSecurityConfigurerAdapter { 
   @Bean 
   public PasswordEncoder passwordEncoder() { 
      return new BCryptPasswordEncoder(); 
   } 
   @Override 
   protected void configure(HttpSecurity http) throws Exception { 
      http 
      .csrf().disable()
      .authorizeRequests()
      .antMatchers("/**").authenticated()
      .and()
     .formLogin().loginPage("/login")
     .permitAll() 
      .and() 
      .logout().invalidateHttpSession(true) 
      .clearAuthentication(true).permitAll(); 
   }
   
  

}
