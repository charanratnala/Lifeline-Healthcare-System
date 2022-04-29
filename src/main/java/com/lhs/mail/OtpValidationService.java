//package com.lhs.mail;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Date;
//
//import javax.mail.MessagingException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.lhs.dao.OtpRepo;
//import com.lhs.entity.OtpEntity;
//import com.lhs.restcontroller.ForgotpasswordController;
//
//import net.bytebuddy.utility.RandomString;
//
//@Service
//public class OtpValidationService {
//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;
//	
//	@Autowired
//	ForgotpasswordController foget;
//	@Autowired
//	JavaMailService services;
//	@Autowired
//	OtpRepo repo;
//	
//	public void generateOneTimePassword(OtpEntity customer)
//	        throws UnsupportedEncodingException, MessagingException {
//	    String OTP = RandomString.make(8);
//	    String encodedOTP = passwordEncoder.encode(OTP);
//	     
//	    customer.setOneTimePassword(encodedOTP);
//	    customer.setOtpRequestedTime(new Date());
//	     
//	    repo.save(customer);
//	     
//	    services.sendOTPEmail(customer, OTP);
//
//	}
//}
