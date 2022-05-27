package com.lhs.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lhs.customexception.AppointmentControllerCustomException;
import com.lhs.dao.DoctorRepo;
import com.lhs.dao.PatientRepo;
import com.lhs.dao.Slot;
import com.lhs.entity.AppointmentTable;
import com.lhs.entity.Doctor;
import com.lhs.entity.SlotEntity;

@Service
public class DoctorService {
	Logger logger = org.slf4j.LoggerFactory.getLogger(DoctorService.class);
	@Autowired
	DoctorRepo doctorRepo;
	@Autowired
	Slot slot;
	Doctor doc;
	@Autowired
	PatientRepo details;
	AppointmentTable pat;

	public void addDoctor(Doctor doctor) {

		if (doctor == null) {
			throw new AppointmentControllerCustomException("700",
					"doctor enitity is null exception occured in AppointmentController");
		} else
			logger.info("object saved into DB in Service Layer");

		this.doc = doctorRepo.save(doctor);
	}

	public void addSlot(SlotEntity slotEntity) {
		logger.info("data entered into service layer");
		if (slotEntity == null) {
			throw new RuntimeException("null entity slotEntity");
		}
		slotEntity.setDc(this.doc);
		slot.save(slotEntity);
		logger.info("Slot information Successfully Saved on DB");
	}

	public AppointmentTable addDoctors(AppointmentTable det) {
		// PatientDetails d= details.findById(det.getId()).get();

//doc.getId();
		det.setDoctor(this.doc);
		System.out.println(this.doc);

		return details.save(det);
		// details.save(pat);

	}

	public List<SlotEntity> getSlots(int pageNo, int pageSize) {
		Sort sort = Sort.by("operationDate").ascending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		logger.info("getting  slot data from getSlots method ");
		Page<SlotEntity> pe = slot.findAll(pageable);
		logger.info("getting Slot Data from Db");
		return pe.getContent();

	}

}
