package com.lhs.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String doctorName;
	@NotNull
	private String specialist;
	private Date availableTime;
	private Date unavailable;
	private String feedback;
	private int rating;
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	private String city;
	private long doctorFee;

	public long getDoctorFee() {
		return doctorFee;
	}

	public void setDoctorFee(long doctorFee) {
		this.doctorFee = doctorFee;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// @JsonFormat(pattern = "HH:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	@Column(name = "start_time")
	private Time appointmentStartTime;
	// @JsonFormat(pattern = "HH:mm")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	@JsonDeserialize(using = SqlTimeDeserializer.class)
	@Column(name = "end_time")
	private Time appointmentEndTime;

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getId() {
		return id;
	}

	public Time getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(Time appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public Time getAppointmentEndTime() {
		return appointmentEndTime;
	}

	public void setAppointmentEndTime(Time appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return doctorName;
	}

	public void setName(String name) {
		this.doctorName = name;
	}

	public String getSpecialist() {
		return specialist;
	}

	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}

	public Date getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(Date availableTime) {
		this.availableTime = availableTime;
	}

	public Date getUnavailable() {
		return unavailable;
	}

	public void setUnavailable(Date unavailable) {
		this.unavailable = unavailable;
	}

	public Doctor(String name, String specialist, Date availableTime, Date unavailable) {
		super();
		this.doctorName = name;
		this.specialist = specialist;
		this.availableTime = availableTime;
		this.unavailable = unavailable;
	}

	private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

	public Doctor() {
		super();
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", doctorName=" + doctorName + ", specialist=" + specialist + ", availableTime="
				+ availableTime + ", unavailable=" + unavailable + ", feedback=" + feedback + ", rating=" + rating
				+ ", status=" + status + ", city=" + city + ", doctorFee=" + doctorFee + ", state="
				+ ", appointmentStartTime=" + appointmentStartTime + ", appointmentEndTime=" + appointmentEndTime
				+ ", mapAppointment=" + ", createdAt=" + createdAt + ", slotEntity=" + "]";
	}

}
