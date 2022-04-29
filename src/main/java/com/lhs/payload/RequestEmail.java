package com.lhs.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class RequestEmail {
	
	
	public RequestEmail() {
		super();
	}

	public RequestEmail(String email) {
		super();
		this.email = email;
	}
	@Email(message = "Please provide a valid email address")
	@Pattern(regexp = ".+@.+\\..+", message = "Please provide  valid email address")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
