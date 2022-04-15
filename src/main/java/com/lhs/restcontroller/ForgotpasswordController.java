package com.lhs.restcontroller;

import java.security.Principal;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import javax.mail.MessagingException;
import javax.naming.directory.InvalidAttributeIdentifierException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.RegistrationRepo;
import com.lhs.entity.RegistrationEntity;
import com.lhs.mail.JavaMailService;
import com.lhs.payload.ForgotNewPassword;
import com.lhs.payload.RequestEmail;
import com.lhs.payload.RequestOtp;

@RestController
@RequestMapping("/api")
public class ForgotpasswordController {

	@Autowired
	BCryptPasswordEncoder bcrypt;
	@Autowired
	JavaMailService sender;

	String res;

	@Autowired
	RegistrationRepo rep;
	int random;

	// private final LoadingCache<UUID, Integer> oneTimePasswordCache;
	
	
	@GetMapping("/gg")
	public ResponseEntity<RegistrationEntity> updateNewPassword( Principal principle) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		RegistrationEntity resPasscode = rep.findByUsername(auth.getName());
		return new ResponseEntity<RegistrationEntity>(resPasscode, HttpStatus.OK);
		
		
	}


	@GetMapping("/forgotpassword")
	public ResponseEntity<String> getResponse() {
		System.out.println(random);
		return new ResponseEntity<String>("valid .......given permission to enter email", HttpStatus.OK);

	}

	@GetMapping("/otpgeneration")
	ResponseEntity<String> g() {
		Random r = new Random();
		int rs = r.nextInt(10000);

		if (rs > 1000) {

			random = rs + 1000;

			System.out.println(random);
		}
		return new ResponseEntity<String>(" otp generated successfully " + random, HttpStatus.ACCEPTED);
	}

	// @RequestParam("getemail") String email

	@PostMapping("/getemail/")
	ResponseEntity<String> getEmailByPath(@Valid @RequestBody RequestEmail email)
			throws MessagingException, InvalidAttributeIdentifierException {
		if (rep.existsByEmail(email.getEmail())) {
			// System.out.println("kk");
			RegistrationEntity ent = rep.findByEmail(email.getEmail());

			System.out.println(ent);
		if(email.getEmail()==ent.getEmail())
	System.out.println("hh");
			g();
			sender.generateMailWithAttachment(email.getEmail(), "  mail for otp generation ",
					"For security reason, you're required to use the following !!!!! One Time Password to login ?  Your OTP "
							+ random);

			return new ResponseEntity<String>("otp sended Successfully pls Check... "+ent.getEmail(), HttpStatus.OK);
		}
		// return new ResponseEntity<String>("otp sended Successfully pls Checkl... ",
		// HttpStatus.OK);

		else {
			throw new RuntimeException("email not found");
		}
		// return new ResponseEntity<String>("otp sended Successfully pls Checkl... ",
		// HttpStatus.OK);

	}

	@PostMapping("/otp/")
	private ResponseEntity<String> validateOtp(@RequestBody RequestOtp otp,HttpSession session) throws ExecutionException {

		session.setAttribute("myotp",random);
		int motp=(int)session.getAttribute("myotp");
		
		if (motp == otp.getOtp()) {
			res = "ok";
			return new ResponseEntity<String>("valid otp   ", HttpStatus.OK);

		}

		else {
			res = "reject";
			return new ResponseEntity<String>("Otp Is Invalid. Please Check ", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/newpassword")
	public ResponseEntity<String> updateNewPassword(@Valid @RequestBody ForgotNewPassword bes, Principal principle) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		RegistrationEntity resPasscode = rep.findByUsername(auth.getName());

		String f = bes.getNewPassword();
		//System.out.println(f);

		resPasscode.setPassword(bcrypt.encode(f));
	//	System.out.println(resPasscode);
		rep.save(resPasscode);
		return new ResponseEntity<String>("Password Updated sucessfully ", HttpStatus.ACCEPTED);

	}

}
