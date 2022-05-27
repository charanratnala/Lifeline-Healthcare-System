package com.lhs.mail;

public interface IEmailService {
	 public boolean sendEmail(String subject, String message, String to);
	 
	 public boolean sendEmailInlineImage(String subject, String message, String to);

}