package com.lhs.updationhandler;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.RegistrationRepo;
import com.lhs.entity.RegistrationEntity;
import com.lhs.payload.DetailsUpdationJavaBean;

@RestController
@RequestMapping("/api")
public class UpdateDetails {

	@Autowired
	RegistrationRepo registrationRepo;
	
	
	
	

	@PutMapping("/updatedetails")
	public ResponseEntity<String> updateCurrentUser(@Valid @RequestBody DetailsUpdationJavaBean updates, Principal principle) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		RegistrationEntity res = registrationRepo.findByUsername(auth.getName());

		res.setFirstname(updates.getFirstName());
		res.setEmail(updates.getEmail());
		res.setDob(updates.getDob());
		res.setLastname(updates.getLastName());
		res.setPhoneNo(updates.getPhoneNo());
		res.setUsername(updates.getUsername());
		registrationRepo.save(res);

		return new ResponseEntity("details updates successfully", HttpStatus.ACCEPTED);

	}

}
