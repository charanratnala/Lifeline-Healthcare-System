package com.lhs.restcontroller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.DoctorRepo;
import com.lhs.dao.PatientRepo;
import com.lhs.dao.Slot;
import com.lhs.dao.SlotHandle;
import com.lhs.entity.AppointmentStatus;
import com.lhs.entity.AppointmentTable;
import com.lhs.entity.Doctor;
import com.lhs.entity.SlotEntity;
import com.lhs.entity.SlotHandler;
import com.lhs.payload.RequestList;
import com.lhs.payload.RequestName;
import com.lhs.service.DoctorService;

@RestController
@RequestMapping("/api")
public class AppointmentController {

	static int count = 0;
	AppointmentStatus d;

	@Autowired
	DoctorRepo doctorRepo;
	@Autowired
	PatientRepo patientRepo;

	@Autowired
	SlotHandle slotHandlers;

	Doctor da;

	@Autowired
	Slot slot;

	// DoctorService doctorService;

	@Autowired
	DoctorService doctorService;

	@PostMapping("/adddoctors")
	public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
		if (doctor == null) {
			throw new RuntimeException("null entity doctor");
		}
		// doctor.getSlotEntity().add(slotEntity);
		doctorService.addDoctor(doctor);

		// doctor.setSlotEntity(null)
		// return new ResponseEntity<String>("saved dear " + d.getName(),
		// HttpStatus.OK);
		return ResponseEntity.ok("saved dear " + doctor.getDoctorName());

	}

	@PostMapping("/addSlots")
	public ResponseEntity<String> addDoctors(@RequestBody SlotEntity slotEntity) {
		if (slotEntity == null) {
			throw new RuntimeException("null entity slotEntity");
		}
		// doctor.getSlotEntity().add(slotEntity);

		doctorService.addSlot(slotEntity);

		// doctor.setSlotEntity(null)
		// return new ResponseEntity<String>("saved dear " + d.getName(),
		// HttpStatus.OK);
		return ResponseEntity.ok("saved dear ");

	}

	@GetMapping("/getslots/{pageNo}")
	ResponseEntity<List<SlotEntity>> getSlots(@PathVariable("pageNo") int pageNo) {
		int pageSize = 3;

		List<SlotEntity> sl = doctorService.getSlots(pageNo - 1, pageSize);

		return new ResponseEntity<List<SlotEntity>>(sl, HttpStatus.OK);

	}

	@GetMapping("/getappointment")
	public ResponseEntity<List<Doctor>> getDetails(@RequestBody RequestList req) {

		List<Doctor> doctor = doctorRepo.findDetailsOfDoctor(req.getFrom(), req.getTo());

		return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.OK);

	}

	@GetMapping("/findbyspecialist")
	public ResponseEntity<?> bookAppointment(@RequestBody RequestName name) {
		// Doctor getDetails= doctorRepo.findBySpecialist(name.getSpecialist());
		if (doctorRepo.existsByAvailableTime(name.getAvailableTime())) {
			List<Doctor> getDetailss = doctorRepo.findBySpecialistAndCityAndAvailableTime(name.getSpecialist(),
					name.getCity(), name.getAvailableTime());

			// Doctor doc=null;
//		  if(getDetailss.isPresent())
//		  {
			// doc=getDetailss.get();
			return new ResponseEntity<List<Doctor>>(getDetailss, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<String>("not found", HttpStatus.BAD_REQUEST);

		}
	}
//		  else
//			  
//		  return new ResponseEntity<String>("Specialist not found",HttpStatus.NOT_FOUND);
//		  
//			  

	@PostMapping("/fillpatientdetails")
	ResponseEntity<AppointmentTable> fillDetails(
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestBody AppointmentTable patient) {

		patient.setPreid(patient.getLocation().substring(0, 4) + " " + patient.getId());

		AppointmentTable doc = doctorService.addDoctors(patient);
		return new ResponseEntity<AppointmentTable>(doc, HttpStatus.OK);

	}

	@GetMapping("/getpatient")
	ResponseEntity<List<AppointmentTable>> getDetails(@RequestBody AppointmentTable patient) {

		List<AppointmentTable> doc = (List<AppointmentTable>) patientRepo.findAll();
		return new ResponseEntity<List<AppointmentTable>>(doc, HttpStatus.OK);

	}

//	@GetMapping("/getbyname")
//	ResponseEntity<List<Doctor>> getDoc(@RequestBody Doctor d)
//	{
//	 List<Doctor> da=	doctorRepo.findByDoctorName(d.getDoctorName());
//		
//		System.out.println(da);
//		
//		int sla;
//		List<SlotEntity> sl=	slot.findAll();
//		System.out.println(sl);
//		for (SlotEntity slotEntity : sl) {
//		
//			System.out.println("after iterate"+slotEntity);
//			//sla=slotEntity.get;
//		}

	// d.getSlotEntity().addAll(sl);

	// return new ResponseEntity<List<Doctor>>(da, HttpStatus.OK);

	// }

	@PostMapping("/slot")
	void addSlot(@RequestBody SlotEntity slotEntity) {

		slot.save(slotEntity);
	}

//	@GetMapping("/getslot")
//	ResponseEntity<List<SlotEntity>> getSlot(@RequestBody SlotEntity d)
//	
//	{
//		//this.da.setSlotEntity(d);
//		
//		List<SlotEntity> sl=	slot.findAll();
//		System.out.println(sl);
//		return new  ResponseEntity<List<SlotEntity>>(sl,HttpStatus.OK); 
//		
//		
//	}

	@PostMapping("/addDate/{id}")
	public ResponseEntity<String> addDate(@RequestBody SlotHandler slotHandler, @PathVariable("id") Doctor doc) {

		List<SlotHandler> sl = slotHandlers.findByOperationDate(slotHandler.getOperationDate());

		
//		if(slotHandlers.findByOperationDateOrSlot1OrSlot2OrSlot3(slotHandler.getOperationDate(), slotHandler.getSlot1(), slotHandler.getSlot2(), slotHandler.getSlot3()) == AppointmentStatus) {
//			return new ResponseEntity<String>("Opps !!!!!! TimeSlot was not there ! ", HttpStatus.BAD_REQUEST);
//			
//		}
		
		
		
		
		System.out.println(slotHandlers.findByOperationDate(slotHandler.getOperationDate()));

		doc.setId(doc.getId());
		System.out.println(doc);

		if (slot.existsByOperationDate(slotHandler.getOperationDate())) {

			if (count == 0) {

				if (d != AppointmentStatus.Booked) {

					// if(slotHandler.getStatus()!=AppointmentStatus.Booked)

					d = AppointmentStatus.Booked;
					count++;

					slotHandler.setStatus(d);
					// System.out.println(slotHandler);
					slotHandler.setDc(doc);
					SlotHandler se = slotHandlers.save(slotHandler);
					System.out.println(se);

				}

			}

			else {

				return new ResponseEntity<String>("Opps !!!!!! TimeSlot was not there ! ", HttpStatus.BAD_REQUEST);

			}
		}

		// find by not working......

		else {
			return new ResponseEntity<String>("Opps !!!!!! TimeSlot was not there ! ", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Dear  " + " Slot timing has Booked Successfully", HttpStatus.OK);
	}

	@GetMapping("/getByDate/{operationDate}")
	public ResponseEntity<?> getByDate(@PathVariable("operationDate") Date operationDate) {

		if (slot.existsByOperationDate(operationDate)) {

			List<SlotEntity> sl = slot.findByOperationDate(operationDate);

			return new ResponseEntity<List<SlotEntity>>(sl, HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("", HttpStatus.OK);

		}

	}

}
