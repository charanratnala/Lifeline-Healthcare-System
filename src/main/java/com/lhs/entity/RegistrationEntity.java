package com.lhs.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class RegistrationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private Timestamp createdAt= new Timestamp(System.currentTimeMillis());

	private String lastName;
	private String username;
	private String password;

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

	private String dob;

	private String gender;
	private String phoneNo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Roles> roles = new ArrayList<>();

	boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	private String role;

	public List<Roles> getRole() {
		return roles;
	}

	public RegistrationEntity() {
		super();
	}

	public RegistrationEntity(int id, String firstname, String username, String password) {
		super();
		this.id = id;
		this.firstName = firstname;
		this.username = username;
		this.password = password;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String email;

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

	@Override
	public String toString() {
		return "RegistrationEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", dob=" + dob + ", gender=" + gender + ", phoneNo=" + phoneNo
				+ ", roles=" + roles + ", status=" + status + ", role=" + role + ", email=" + email + "]";
	}

}
