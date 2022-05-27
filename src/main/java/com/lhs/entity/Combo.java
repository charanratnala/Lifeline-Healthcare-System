package com.lhs.entity;

public class Combo {
	private Doctor doc;
	private Prescription appointmentDetails;

	public Combo(Doctor doc, Prescription appointmentDetails) {
		super();
		this.doc = doc;
		this.appointmentDetails = appointmentDetails;
	}

	public Combo() {
		super();
	}

	public Doctor getDoc() {
		return doc;
	}

	public void setDoc(Doctor doc) {
		this.doc = doc;
	}

	public Prescription getAppointmentDetails() {
		return appointmentDetails;
	}

	public void setAppointmentDetails(Prescription appointmentDetails) {
		this.appointmentDetails = appointmentDetails;
	}

}
