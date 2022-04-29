package com.lhs.restcontroller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.RegistrationRepo;
import com.lhs.dto.RegistrationDto;
import com.lhs.entity.RegistrationEntity;
import com.lhs.jwthandler.JwtRequest;
import com.lhs.jwthandler.JwtResponse;
import com.lhs.jwthandler.JwtUtil;
import com.lhs.security.ImplementationUserDetailsService;
import com.lhs.service.GetService;
import com.lhs.service.RegistrationService;

//@RequestMapping("/api")
@RestController
@CrossOrigin()
public class RegistrationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	GetService serv;

	Logger logger = org.slf4j.LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	JwtUtil jwtUtility;

	@Autowired
	ImplementationUserDetailsService detailsService;

	@Autowired
	RegistrationRepo registrationRepo;

	@Autowired
	RegistrationService service;

	@PostMapping("/register")
	public ResponseEntity<String> addRegistration(@RequestBody @Valid RegistrationDto register) {

		logger.info("executed add method in controller");
		if (register == null) {
			logger.error("registration object is null");
		}

		service.addAccount(register);
		logger.info("account saved in the database sucessfull");

		return ResponseEntity.ok("added account sucessfully " + register.getUsername());

	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = detailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtUtility.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(jwt));
	}

	
	
	
	
	
	
	@GetMapping("/getregister")
	List<RegistrationEntity> gget()
	{
		
	List<RegistrationEntity>	d=(List<RegistrationEntity>) registrationRepo.findAll();
	
	
	
	return d;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	


	@GetMapping("/fulldetail")
	ResponseEntity<UserDetails> currentUser(Principal principle)

	{
		final UserDetails currentUser = (UserDetails) ((Authentication) principle).getPrincipal();

		if (currentUser == null) {
			return new ResponseEntity<UserDetails>(HttpStatus.BAD_REQUEST);

		} else
			return ResponseEntity.ok(currentUser);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	@GetMapping("/getdetail")
//	public ResponseEntity<String> updateCurrentUser(@Valid @RequestBody UpdatePassword updates, Principal principle) {
//		final UserDetails currentUser = (UserDetails) ((Authentication) principle).getPrincipal();
//
//		if (updates.getNewPassword().isEmpty() || currentUser.getUsername().isEmpty()
//				|| updates.getOldPassword().isEmpty()) {
//
//			return new ResponseEntity<String>("pls dont enter null overtheir enter valid password",
//					HttpStatus.BAD_REQUEST);
//		}
//
//		else {
//
//			RegistrationEntity res = registrationRepo.findByUsername(currentUser.getUsername());
//			System.out.println(res);
//
//			updates.setOldPassword(res.getPassword());
//			res.setPassword(updates.getNewPassword());
//			registrationRepo.save(res);
//			System.out.println(res);
//
//			// return new ResponseEntity<String>("ADDED",HttpStatus.ACCEPTED);
//
//		}
//
//		return new ResponseEntity<String>("",HttpStatus.BAD_REQUEST);
//		//return null;
//
//	}

}
