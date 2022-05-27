package com.lhs.entity;

import java.sql.Time;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate doctorAvailableDate;

	private Time slotTime;

	private boolean appointmentStatus;

	@OneToOne
	private Doctor doc;

	public boolean getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(boolean appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	@OneToOne
	private RegistrationEntity registrationEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Time getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(Time slotTime) {
		this.slotTime = slotTime;
	}

	public Doctor getDoc() {
		return doc;
	}

	public void setDoc(Doctor doc) {
		this.doc = doc;
	}

	public RegistrationEntity getRegistrationEntity() {
		return registrationEntity;
	}

	public void setRegistrationEntity(RegistrationEntity registrationEntity) {
		this.registrationEntity = registrationEntity;
	}

	public Appointment(Integer id, LocalDate doctorAvailableDate, Time slotTime, boolean appointmentStatus, Doctor doc,
			RegistrationEntity registrationEntity) {
		super();
		this.id = id;
		this.doctorAvailableDate = doctorAvailableDate;
		this.slotTime = slotTime;
		this.appointmentStatus = appointmentStatus;
		this.doc = doc;
		this.registrationEntity = registrationEntity;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", doctorAvailableDate=" + doctorAvailableDate + ", slotTime=" + slotTime
				+ ", appointmentStatus=" + appointmentStatus + ", doc=" + doc + ", registrationEntity="
				+ registrationEntity + "]";
	}

	public Appointment() {
		super();
	}

	public LocalDate getDoctorAvailableDate() {
		return doctorAvailableDate;
	}

	public void setDoctorAvailableDate(LocalDate doctorAvailableDate) {
		this.doctorAvailableDate = doctorAvailableDate;
	}

}
