package com.lhs.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.dao.AppointmentRepository;
import com.lhs.dao.DoctorAssignedEntityRepo;
import com.lhs.entity.Appointment;
import com.lhs.entity.Doctor;
import com.lhs.entity.DoctorsGuide;
import com.lhs.entity.RegistrationEntity;

@Service
public class DoctorServiceIm {

	@Autowired
	DoctorAssignedEntityRepo assignedEntityRepo;

	@Autowired
	AppointmentRepository appointmentRepository;

	public List<Appointment> getByDate(LocalDate currentDate) {

		List<Appointment> l = appointmentRepository.findByDoctorAvailableDate(currentDate);

		return l;

	}

	public Appointment updateDoctor(Appointment appointment, Doctor doc, RegistrationEntity registrationEntity) {
		appointment.setDoc(doc);
		appointment.setRegistrationEntity(registrationEntity);
		Appointment app = appointmentRepository.save(appointment);

		return app;

	}

	public void DoctorAssign(DoctorsGuide doctorsGuide, RegistrationEntity registrationEntity)

	{
		doctorsGuide.setRegistrationEntity(registrationEntity);

		assignedEntityRepo.save(doctorsGuide);

	}

}
