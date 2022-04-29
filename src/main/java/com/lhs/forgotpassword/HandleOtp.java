//package com.lhs.forgotpassword;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import com.lhs.mail.JavaMailCheck;
//
//@Component
//public class HandleOtp {
//	
//	@Autowired
//	JavaMailCheck check;
//	
//	@EventListener(ApplicationReadyEvent.class)
//	void triggerMail()
//	{
//		check.generateMail("hhh@", "jbkjd", "ddd");
//	}
//
//}
