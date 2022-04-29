package com.lhs.payload;

import javax.validation.constraints.Size;

public class UpdatePasswordJavaBean {

	
	public UpdatePasswordJavaBean(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	@Size(min = 8, message = "u must enter valid passcode.....")
	String oldPassword;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Size(min = 8,  message = "u must enter min 8 characters.....")
	String newPassword;
}
