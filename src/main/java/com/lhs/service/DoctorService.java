package com.lhs.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.lhs.dao.DoctorRepo;
import com.lhs.dao.PatientRepo;
import com.lhs.dao.Slot;
import com.lhs.entity.AppointmentTable;
import com.lhs.entity.Doctor;
import com.lhs.entity.SlotEntity;

import org.springframework.data.domain.Sort;


@Service
public class DoctorService {
	
	@Autowired
	DoctorRepo doctorRepo;
	
	
	@Autowired
	Slot slot;
	
	
	
	Doctor doc;
	
	@Autowired
	PatientRepo details;
	
	AppointmentTable pat;
	
	public void addDoctor( Doctor doctor)
	{
		
	//	doctor.getSlotEntity().add(slotEntity);
		
		
//		System.out.println(slotEntity);
		
//		doctor.getSlotEntity().add(slotEntity);
		
				this.doc=		doctorRepo.save(doctor);
//				slot.save(slotEntity);
				System.out.println(this.doc);
				//return doc;
		
	}
	public void addSlot( SlotEntity slotEntity)
	{
		
	//	doctor.getSlotEntity().add(slotEntity);
		
		
		System.out.println(slotEntity);
		
		//doctor.getSlotEntity().add(slotEntity);
		
				//this.doc=		doctorRepo.save(doctor);
		slotEntity.setDc(this.doc);
				slot.save(slotEntity);
				System.out.println(this.doc);
				//return doc;
		
	}
	
	
	
	
	
	public AppointmentTable addDoctors(AppointmentTable det)
	{
		//PatientDetails d=	details.findById(det.getId()).get();
		
//doc.getId();
		det.setDoctor(this.doc);
		System.out.println(this.doc);
	
		
		
		return details.save(det);
		//details.save(pat);
		
		
		
		
		
	}
	
	
	
	
	
	
	public List<SlotEntity> getSlots(int pageNo,int pageSize)
	{
		Sort sort= Sort.by("operationDate").ascending();
		
		Pageable pageable=PageRequest.of(pageNo, pageSize,sort);
		
		//otEntity  slotEntity= new SlotEntity();
	Page<SlotEntity> pe=	   slot.findAll(pageable);
	
	return pe.getContent();
		
		
		
		
		
	}


}

