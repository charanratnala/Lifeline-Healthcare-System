package com.lhs.updationhandler;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.customexception.UpdationException;
import com.lhs.dao.RegistrationRepo;
import com.lhs.entity.RegistrationEntity;
import com.lhs.payload.UpdatePasswordJavaBean;
import com.lhs.security.ImplementationUserDetailsService;

@RestController
@RequestMapping("/api")
public class UpdatePasswordHandler {
	@Autowired
	BCryptPasswordEncoder bcrypt;
	@Autowired
	ImplementationUserDetailsService detailsService;

	@Autowired
	RegistrationRepo registrationRepo;

	@PutMapping("/updatepasscode")
	public ResponseEntity<String> updateCurrentUser(@Valid @RequestBody UpdatePasswordJavaBean updates, Principal principle) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		RegistrationEntity resPasscode = registrationRepo.findByUsername(auth.getName());

		if (updates.getNewPassword() != null && !updates.getNewPassword().isEmpty()
				&& !updates.getNewPassword().contains(" ") && !updates.getNewPassword().equals("")) {

			if(this.bcrypt.matches(updates.getOldPassword(), resPasscode.getPassword())) {
				resPasscode.setPassword(bcrypt.encode(updates.getNewPassword()));
			registrationRepo.save(resPasscode);
			return new ResponseEntity("Password Updated sucessfully ", HttpStatus.ACCEPTED);}

			else {
				

			return new ResponseEntity("Please enter valid old Password ", HttpStatus.BAD_REQUEST);
			}
		}

		else {

			throw new UpdationException("707", "exception raised in updation password ");

		}

	}

	
}
