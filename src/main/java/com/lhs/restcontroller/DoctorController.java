package com.lhs.restcontroller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.AppointmentRepository;
import com.lhs.dao.DoctorAssignedEntityRepo;
import com.lhs.dao.DoctorRepo;
import com.lhs.dao.RegistrationRepo;
import com.lhs.entity.Appointment;
import com.lhs.entity.Doctor;
import com.lhs.entity.DoctorsGuide;
import com.lhs.entity.RegistrationEntity;
import com.lhs.service.DoctorServiceIm;
import com.lhs.service.EmailServiceImpl;

@RestController
@RequestMapping("/api")
public class DoctorController {

	Logger logger = org.slf4j.LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	EmailServiceImpl emailServiceImpl;

	@Autowired
	RegistrationRepo registrationRepo;

	@Autowired
	DoctorAssignedEntityRepo assignedEntityRepo;

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	DoctorRepo doctorRepo;

	List<Appointment> La;
	@Autowired
	DoctorServiceIm doctorServiceIm;

	@GetMapping("/currentDate")
	public ResponseEntity<?> getByCurrentDates() {

		logger.info("entered into getByCurrentDates method ");
		LocalDate today = LocalDate.now();
		List<Appointment> La = doctorServiceIm.getByDate(today);

		La.stream().map(Appointment::getAppointmentStatus).collect(Collectors.toList());

		List<Appointment> f = new ArrayList<>();
		Appointment d = null;

		for (Appointment s : La) {

			if (s.getAppointmentStatus() == true) {

				f.add(s);
			}
		}

		return new ResponseEntity<List<Appointment>>(f, HttpStatus.OK);

	}

	@GetMapping("/nextdate")
	public ResponseEntity<?> getByNextDate() {
		LocalDate nextDay = null;

		LocalDate today = LocalDate.now();

		nextDay = today.plusDays(1);

		List<Appointment> f = new ArrayList<>();

		List<Appointment> La = doctorServiceIm.getByDate(nextDay);
		La.stream().map(Appointment::getAppointmentStatus).collect(Collectors.toList());

		for (Appointment s : La) {

			if (s.getAppointmentStatus() == true)

				f.add(s);

		}

		return new ResponseEntity<List<Appointment>>(f, HttpStatus.OK);
	}

	@GetMapping("/upcomingDate")
	public ResponseEntity<?> getByUpomingDate() {
		LocalDate nextDay = null;
		LocalDate todays = null;
		LocalDate today = LocalDate.now();
		nextDay = today.plusDays(1);
		try {
			for (int i = 0; i <= 7; i++) {

				todays = today.plusDays(i);
				System.out.println(i);

				this.La = doctorServiceIm.getByDate(todays);
				System.out.println("69");
				if (!this.La.isEmpty()) {
					System.out.println("71");
					System.out.println(nextDay);

					for (Appointment s : this.La) {
						System.out.println("76" + s.getAppointmentStatus() + i);
						if (s.getAppointmentStatus() == true) {
							System.out.println("78");
							System.out.println(s.getAppointmentStatus());
							return new ResponseEntity<List<Appointment>>(La, HttpStatus.OK);

						}

					}

				}
			}
		}

		catch (Exception e) {

			return new ResponseEntity<String>("No patient was booked in this date", HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<List<Appointment>>(La, HttpStatus.OK);

	}

	@GetMapping("/findProfile/{id}")
	public ResponseEntity<?> getProfile(@PathVariable("id") Doctor doc) {
		Optional<Doctor> dd = null;

		if (doctorRepo.existsById(doc.getId()) && doc.getDoctorName() != null) {

			dd = doctorRepo.findById(doc.getId());
			return new ResponseEntity<Optional<Doctor>>(dd, HttpStatus.OK);
		}

		else
			return new ResponseEntity<String>("No doc is there", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/upcomingappoinments")
	public ResponseEntity<?> upcomingAp() {
		LocalDate datetime1 = LocalDate.now();

		List<Appointment> La = appointmentRepository.findAllDateAfter(datetime1);

		La.stream().map(Appointment::getAppointmentStatus).collect(Collectors.toList());

		List<Appointment> dummy = new ArrayList<>();
		for (Appointment appoinment2 : La) {

			if (appoinment2.getAppointmentStatus() == true) {
				dummy.add(appoinment2);

			}

		}
		return new ResponseEntity<List<Appointment>>(dummy, HttpStatus.OK);

	}

	@GetMapping("/update/{id}")
	public ResponseEntity<Appointment> updateDoctorSlot(@PathVariable("id") Doctor doctor) {

		doctor.setId(doctor.getId());
		Appointment doc = appointmentRepository.findByDocId(doctor.getId());
		return new ResponseEntity<Appointment>(doc, HttpStatus.OK);

	}

	@PutMapping("/updatedDoctorData/{id}/{ids}")
	public ResponseEntity<Appointment> updateDoctorDetail(@RequestBody Appointment appointment,
			@PathVariable("id") Doctor doc, @PathVariable("ids") RegistrationEntity registrationEntity) {
		String subject = "Updated Slot From LHS Application";
		String body = "<h1><br><b>Hi Folks</b>,<br><i>look at this nice logo :)</i></h1>" + "<h1>Dear</h1>" + "<h1>"
				+ registrationEntity.getUsername() + "</h1>"
				+ "  <h1> LHS Application  slot timings has changed By </h1> " + "Dr : " + doc.getDoctorName()
				+ " <h1> Timings To </h1> " + "<h1> Slot Timings</h1>" + appointment.getSlotTime() + " Slot Date  "
				+ appointment.getDoctorAvailableDate() + " ThankYou    " + "  <h1> Team LHS</h1>";
		String to = "saicharanratnala89@gmail.com";
		emailServiceImpl.sendEmail(subject, body, to);
		Appointment app = doctorServiceIm.updateDoctor(appointment, doc, registrationEntity);

		return new ResponseEntity<Appointment>(app, HttpStatus.OK);
	}

	@PostMapping("/assign/{id}")
	public ResponseEntity<String> assignMedidcine(@RequestBody @Valid DoctorsGuide assignedEntity,
			@PathVariable("id") RegistrationEntity registrationEntity)

	{
		doctorServiceIm.DoctorAssign(assignedEntity, registrationEntity);

		return new ResponseEntity<String>("Saved", HttpStatus.OK);

	}

}
