package com.lhs.dto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lhs.entity.Roles;

public class RegistrationDto {
	@NotEmpty(message = "firstname should be there")
	private String firstName;
	boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	private String lastName;
	@NotEmpty(message = "username should be there")
	private String username;
	@Size(min = 8, message = "u must enter min 8 characters.....")
	private String password;

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public String getLastname() {
		return lastName;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileno() {
		return phoneNo;
	}

	public void setMobileno(String mobileno) {
		this.phoneNo = mobileno;
	}

	private String dob;

	private String gender;
	@Size(min = 10, max = 10, message = "please enter valid mobile number")
	private String phoneNo;
	//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JoinTable(name="Register_Role", joinColumns = @JoinColumn(name="RegistrationEntity_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	List<Roles> roles = new ArrayList<>();

	public List<Roles> getRole() {
		return roles;
	}

	public void setRole(List<Roles> role) {
		this.roles = role;
	}

	public String getRolen() {
		return role;
	}

	public void setRolen(String rolen) {
		this.role = rolen;
	}

	private String role;
	@Email(message = "Please provide a valid email address")
	@Pattern(regexp = ".+@.+\\..+", message = "Please provide  valid email address")
	@Column(unique = true)
	private String email;
	@JsonFormat(pattern = "HH:mm")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	@Column(name = "start_time")
	private Time currentTime[];
	public Time[] getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Time[] currentTime) {
		this.currentTime = currentTime;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
