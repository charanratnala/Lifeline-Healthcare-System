package com.lhs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lhs.mail.JavaMailService;

@SpringBootApplication
public class LifeLineHealthCareSystemApplication {

	@Autowired
	JavaMailService serv;
	public static void main(String[] args) {
		SpringApplication.run(LifeLineHealthCareSystemApplication.class, args);}

	
		

	

	@Bean
public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	
	
//	"slot1": "10:30",
//    "slot2": "11:00",
//    "slot3": "11:15",
//    "slotStatus": true

}
