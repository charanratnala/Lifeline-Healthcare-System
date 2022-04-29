package com.lhs.security;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhs.jwthandler.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityHandler extends WebSecurityConfigurerAdapter {

	@Autowired
	ImplementationUserDetailsService serve;
	@Autowired
	JwtFilter filter;

	@Bean
	public AuthenticationProvider auth() {

		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(serve);
		dao.setPasswordEncoder(new BCryptPasswordEncoder());
		return dao;
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
///authenticate", "/register","/api/getemail/","/api/otp/
		http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated().and().exceptionHandling()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

	}
	
	
	
	
	
	//
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping("/k")
	public void getCurrentUsera(Principal principal) {
		final UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
		System.out.println(currentUser);
	}

	@GetMapping("/user")
	public User getCurrentUser(Principal principal) {
		System.out.println("ll");

		System.out.println();
		return (User) serve.loadUserByUsername(principal.getName());

	}
}
