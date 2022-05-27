package com.lhs.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "doctorAssignEntity")
public class DoctorsGuide {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	private String assignMedicine;

	@NotNull
	private boolean consultingStatus;

	public boolean isConsultingStatus() {
		return consultingStatus;
	}

	public void setConsultingStatus(boolean consultingStatus) {
		this.consultingStatus = consultingStatus;
	}

	@Enumerated(EnumType.STRING)
	private AdmitStatus admitStatus;

	@OneToOne
	RegistrationEntity registrationEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssignMedicine() {
		return assignMedicine;
	}

	public void setAssignMedicine(String assignMedicine) {
		this.assignMedicine = assignMedicine;
	}

	public AdmitStatus getStatus() {
		return admitStatus;
	}

	public void setStatus(AdmitStatus status) {
		this.admitStatus = status;
	}

	public RegistrationEntity getRegistrationEntity() {
		return registrationEntity;
	}

	public void setRegistrationEntity(RegistrationEntity registrationEntity) {
		this.registrationEntity = registrationEntity;
	}

	public AdmitStatus getAdmitStatus() {
		return admitStatus;
	}

	public void setAdmitStatus(AdmitStatus admitStatus) {
		this.admitStatus = admitStatus;
	}

	public DoctorsGuide(Integer id, @NotNull String assignMedicine, boolean consultingStatus, AdmitStatus admitStatus,
			RegistrationEntity registrationEntity) {
		super();
		this.id = id;
		this.assignMedicine = assignMedicine;
		this.consultingStatus = consultingStatus;
		this.admitStatus = admitStatus;
		this.registrationEntity = registrationEntity;
	}

	public DoctorsGuide() {
		super();
	}

	@Override
	public String toString() {
		return "DoctorsGuide [id=" + id + ", assignMedicine=" + assignMedicine + ", consultingStatus="
				+ consultingStatus + ", admitStatus=" + admitStatus + ", registrationEntity=" + registrationEntity
				+ "]";
	}

}
