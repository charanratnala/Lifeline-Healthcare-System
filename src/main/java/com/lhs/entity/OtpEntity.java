package com.lhs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "otptable")
public class OtpEntity {

	    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    Integer id;
	    @Column(name = "one_time_password")
	    private String oneTimePassword;
	     
	    @Column(name = "otp_requested_time")
	    private Date otpRequestedTime;
	     
	     
	    public boolean isOTPRequired() {
	        if (this.getOneTimePassword() == null) {
	            return false;
	        }
	         
	        long currentTimeInMillis = System.currentTimeMillis();
	        long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();
	         
	        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
	            // OTP expires
	            return false;
	        }
	         
	        return true;
	    }
}

//
//		public String getOneTimePassword() {
//			return oneTimePassword;
//		}
//
//
//		public void setOneTimePassword(String oneTimePassword) {
//			this.oneTimePassword = oneTimePassword;
//		}
//
//
//		public Date getOtpRequestedTime() {
//			return otpRequestedTime;
//		}
//
//
//		public void setOtpRequestedTime(Date otpRequestedTime) {
//			this.otpRequestedTime = otpRequestedTime;
//		}
//
//
//		public static long getOtpValidDuration() {
//			return OTP_VALID_DURATION;
//		}
//	     
//	     
//	    
//	}
//
