package com.lhs.entity;



import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class AppointmentTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String patientName;

	private String email;
	private int age;
	private String bloodGroup;
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	
	private Date slotDate;
	
	private String puprose;
	@Enumerated(EnumType.STRING)
	private AppointmentPatientStatus status;
	
	
   
	public Date getSlotDate() {
		return slotDate;
	}
	public void setSlotDate(Date slotDate) {
		this.slotDate = slotDate;
	}
	public String getPuprose() {
		return puprose;
	}
	public void setPuprose(String puprose) {
		this.puprose = puprose;
	}
	public AppointmentPatientStatus getStatus() {
		return status;
	}
	public void setStatus(AppointmentPatientStatus status) {
		this.status = status;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@OneToOne
	private Doctor doctor;
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	private String address;
	private String location;
	
	private String preid;
	public String getPreid() {
		return preid;
	}
	public void setPreid(String preid) {
		this.preid = preid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFullName() {
		return patientName;
	}
	public void setFullName(String fullName) {
		this.patientName = fullName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public AppointmentTable(String fullName, String phoneNo, String email, Date appointmentDate, String address) {
		super();
		this.patientName = fullName;
	
		this.email = email;
		this.address = address;
	}
	public AppointmentTable() {
		super();
	}
	@Override
	public String toString() {
		return "AppointmentTable [id=" + id + ", patientName=" + patientName + ", phoneNo="  + ", email="
				+ email + ", age=" + age + ", location="  + ", slotDate=" + slotDate + ", puprose=" + puprose
				+ ", status=" + status + ", doctor=" + doctor + ", address=" + address + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
