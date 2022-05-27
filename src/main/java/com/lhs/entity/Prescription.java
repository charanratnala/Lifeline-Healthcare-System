package com.lhs.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String preid;

	public String getPreid() {
		return preid;
	}

	public void setPreid(String preid) {
		this.preid = preid;
	}

	private String patientFirstName;
	private String patientLastName;
	private String BloodGroup;;
	private int patientAge;
	private Date appointmentDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	@Column(name = "start_time")
	private Time appointmentTime;
	private String problemDescription;
	private String assignedMedicines;
	private AppointmentPatientStatusEnum treatmentStatus;
	private long treatmentPrice;
	private long totalMedicinesPrices;
	private long grandTotal;

	public long getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(long grandTotal) {
		this.grandTotal = grandTotal;
	}

	private boolean medicineFromHospital;
	@OneToOne
	Doctor doctor;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public String getBloodGroup() {
		return BloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		BloodGroup = bloodGroup;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public String getAssignedMedicines() {
		return assignedMedicines;
	}

	public void setAssignedMedicines(String assignedMedicines) {
		this.assignedMedicines = assignedMedicines;
	}

	public AppointmentPatientStatusEnum getTreatmentStatus() {
		return treatmentStatus;
	}

	public void setTreatmentStatus(AppointmentPatientStatusEnum treatmentStatus) {
		this.treatmentStatus = treatmentStatus;
	}

	public long getTreatmentPrice() {
		return treatmentPrice;
	}

	public void setTreatmentPrice(long treatmentPrice) {
		this.treatmentPrice = treatmentPrice;
	}

	public long getTotalMedicinesPrices() {
		return totalMedicinesPrices;
	}

	public void setTotalMedicinesPrices(long totalMedicinesPrices) {
		this.totalMedicinesPrices = totalMedicinesPrices;
	}

	public boolean isMedicineFromHospital() {
		return medicineFromHospital;
	}

	public void setMedicineFromHospital(boolean medicineFromHospital) {
		this.medicineFromHospital = medicineFromHospital;
	}

	@Override
	public String toString() {
		return "AppointmentDetails [id=" + id + ", patientFirstName=" + patientFirstName + ", patientLastName="
				+ patientLastName + ", BloodGroup=" + BloodGroup + ", patientAge=" + patientAge + ", appointmentDate="
				+ appointmentDate + ", appointmentTime=" + appointmentTime + ", problemDescription="
				+ problemDescription + ", assignedMedicines=" + assignedMedicines + ", treatmentStatus="
				+ treatmentStatus + ", treatmentPrice=" + treatmentPrice + ", totalMedicinesPrices="
				+ totalMedicinesPrices + ", medicineFromHospital=" + medicineFromHospital + preid + "]";
	}

	public Prescription() {
		super();
	}

	public Time getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	@OneToOne
	AppointmentTable appointmentTable;

	public AppointmentTable getAppointmentTable() {
		return appointmentTable;
	}

	public void setAppointmentTable(AppointmentTable appointmentTable) {
		this.appointmentTable = appointmentTable;
	}

}
