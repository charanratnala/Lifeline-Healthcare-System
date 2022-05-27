package com.lhs.restcontroller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.AppointmentRepository;
import com.lhs.dao.DoctorRepo;
import com.lhs.dao.PatientRepo;
import com.lhs.dao.Slot;
import com.lhs.dao.SlotHandle;
import com.lhs.entity.Appointment;
import com.lhs.entity.AppointmentStatus;
import com.lhs.entity.AppointmentTable;
import com.lhs.entity.Doctor;
import com.lhs.entity.RegistrationEntity;
import com.lhs.entity.SlotEntity;
import com.lhs.entity.SlotHandler;
import com.lhs.payload.RequestList;
import com.lhs.payload.RequestName;
import com.lhs.service.DoctorService;

@RestController
@RequestMapping("/api")
public class AppointmentController {
	Logger logger = org.slf4j.LoggerFactory.getLogger(AppointmentController.class);
	@Autowired
	private AppointmentRepository appointmentRepository;

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

	@Autowired
	DoctorService doctorService;

	@PostMapping("/adddoctors")
	public ResponseEntity<String> addDoctor(@RequestBody @Valid Doctor doctor) {
		logger.info(" object entered into addDoctor method in AppointmentController ");
		doctorService.addDoctor(doctor);
		logger.info("object successfully inserted into Db");
		return ResponseEntity.ok("saved dear " + doctor.getDoctorName());
	}

	@PostMapping("/addSlots")
	@ResponseStatus(code = HttpStatus.OK)
	public void addDoctors(@RequestBody SlotEntity slotEntity) {
		logger.info("slot data entered into controller");
		doctorService.addSlot(slotEntity);
		logger.info("slot Data Successfully Saved from the service");
	}

	@GetMapping("/getslots/{pageNo}")
	ResponseEntity<List<SlotEntity>> getSlots(@PathVariable("pageNo") int pageNo) {
		logger.info("slot data entered into controller");
		int pageSize = 3;
		List<SlotEntity> sl = doctorService.getSlots(pageNo - 1, pageSize);
		return new ResponseEntity<List<SlotEntity>>(sl, HttpStatus.OK);

	}

	@GetMapping("/getappointment")
	public ResponseEntity<List<Doctor>> getDetails(@RequestBody RequestList req) {
		List<Doctor> doctor = doctorRepo.findDetailsOfDoctor(req.getFrom(), req.getTo());
		logger.info("getting data From the Service for GetDetails Method");
		return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.OK);

	}

	@GetMapping("/findbyspecialist")
	public ResponseEntity<?> bookAppointment(@RequestBody RequestName name) {
		logger.info("entered into bookAppointment method In appointment Controller");
		if (doctorRepo.existsByAvailableTime(name.getAvailableTime())) {
			List<Doctor> getDetailss = doctorRepo.findBySpecialistAndCityAndAvailableTime(name.getSpecialist(),
					name.getCity(), name.getAvailableTime());
			return new ResponseEntity<List<Doctor>>(getDetailss, HttpStatus.OK);
		}

		else {
			return new ResponseEntity<String>("not found", HttpStatus.BAD_REQUEST);

		}
	}

	@PostMapping("/fillpatientdetails")
	ResponseEntity<AppointmentTable> fillDetails(
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestBody AppointmentTable patient) {
		AppointmentTable doc = doctorService.addDoctors(patient);
		return new ResponseEntity<AppointmentTable>(doc, HttpStatus.OK);

	}

	@GetMapping("/getpatient")
	ResponseEntity<List<AppointmentTable>> getDetails(@RequestBody AppointmentTable patient) {

		List<AppointmentTable> doc = (List<AppointmentTable>) patientRepo.findAll();
		return new ResponseEntity<List<AppointmentTable>>(doc, HttpStatus.OK);

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

	@GetMapping("/get")
	private Iterable<Appointment> getAppointment() {

		return appointmentRepository.findAll();

	}

	@GetMapping("/book/{id}")
	private ResponseEntity<Appointment> bookAppointment(@PathVariable("id") Integer Id) {
		Optional<Appointment> aptmt = appointmentRepository.findById(Id);
		Appointment appointment = aptmt.get();
		if (appointment.getAppointmentStatus() == false)
			appointment.setAppointmentStatus(true);
		appointmentRepository.save(appointment);

		return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);
	}

	@PostMapping("/addSlotDoc/{id}/{ids}")
	public ResponseEntity<Appointment> addSlotsDoc(@RequestBody Appointment appointment,
			@PathVariable("id") Doctor doctor, @PathVariable("ids") RegistrationEntity registrationEntity) {
		if (appointment == null) {
			throw new RuntimeException();
		} else {
			Doctor doc = new Doctor();
			RegistrationEntity appointmen = new RegistrationEntity();
			appointmen.setId(registrationEntity.getId());
			doc.setId(doctor.getId());
			appointment.setDoc(doctor);

			appointment.setRegistrationEntity(appointmen);
			appointmentRepository.save(appointment);
			return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);

		}
	}

	//
	@PostMapping("/addDate/{id}")
	public ResponseEntity<String> addDate(@RequestBody SlotHandler slotHandler, @PathVariable("id") Doctor doc) {

		// List<SlotHandler> sl =
		// slotHandlers.findByOperationDate(slotHandler.getOperationDate());

		doc.setId(doc.getId());
		System.out.println(doc);

		if (slot.existsByOperationDate(slotHandler.getOperationDate())) {

			if (slotHandlers.existsBySlot1(slotHandler.getSlot1())) {

				d = AppointmentStatus.Booked;

				slotHandler.setSlot1status(true);
				slotHandler.setDc(doc);
				SlotHandler se = slotHandlers.save(slotHandler);
				System.out.println(se);

			} else {
				System.out.println("error");
			}

		}

		else {

			return new ResponseEntity<String>("Opps !!!!!! TimeSlot was not there ! ", HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<String>("Dear  " + " Slot timing has Booked Successfully", HttpStatus.OK);
	}

	// find by not working......
}
