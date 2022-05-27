//package com.virtusa.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class MyCustomConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	UserDetailsService detailsService;
//
////	@Override
////	@Bean
////	protected UserDetailsService userDetailsService() {
////
////		List<UserDetails> user = new ArrayList();
////
////		user.add(User.withDefaultPasswordEncoder().username("charan").password("charan").roles("USER").build());
////
////		return new InMemoryUserDetailsManager(user);
////
////	}
//
//	@Bean
//	public AuthenticationProvider authenticationProvider() {
//
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//
//		daoAuthenticationProvider.setUserDetailsService(detailsService);
//		daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//
//		return daoAuthenticationProvider;
//	}
//
//}
