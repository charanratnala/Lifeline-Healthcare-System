package com.lhs.payload;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotNewPassword {

	
	@Size(min = 8,  message = "u must enter min 8 characters.....")
	String newPassword;

}
