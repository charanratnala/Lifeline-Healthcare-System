package com.lhs.restcontroller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.AppointmentTableRepo;
import com.lhs.dao.BillMakingRepo;
import com.lhs.dao.DoctorRepo;
import com.lhs.entity.AppointmentTable;
import com.lhs.entity.Combo;
import com.lhs.entity.Doctor;
import com.lhs.entity.Prescription;
import com.lhs.service.BillMakingService;
import com.lowagie.text.DocumentException;

@RestController
@CrossOrigin()
@RequestMapping("/api")
public class BillMakingController {

	Logger logger = org.slf4j.LoggerFactory.getLogger(BillMakingController.class);

	Combo combo;
	Optional<AppointmentTable> app;
	Optional<Prescription> ap;

	@GetMapping("/kk")
	public String g() {
		return "Hii Rajesh";
	}

	@Autowired
	BillMakingService billMaking;

	@Autowired
	AppointmentTableRepo appointmentTableRepo;

	@Autowired
	DoctorRepo doctorRepo;

	@Autowired
	BillMakingRepo billMakingRepo;

	@GetMapping("/getByPre/{preid}")
	public ResponseEntity<String> getPre(@PathVariable("preid") String preid) {

		billMakingRepo.findByPreid(preid);
		return new ResponseEntity<String>("done", HttpStatus.ACCEPTED);

	}

	@PostMapping("/makeBills/{docid}")
	ResponseEntity<String> addBill(@RequestBody Prescription appointmentDetails, @PathVariable("docid") Doctor doc) {

		appointmentDetails
				.setGrandTotal(appointmentDetails.getTreatmentPrice() + appointmentDetails.getTotalMedicinesPrices());

		Random r = new Random();
		int rs = r.nextInt(100);
		System.out.println(rs);

		appointmentDetails.setPreid("0" + rs + appointmentDetails.getPatientFirstName().substring(0, 1)
				+ appointmentDetails.getPatientLastName().substring(0, 2));

		billMaking.makeBill(appointmentDetails, doc);
		return new ResponseEntity<String>("Doc Id Exists.", HttpStatus.OK);

	}

	@GetMapping("/getBill/{id}/{ids}")
	public ResponseEntity<?> getBill(@PathVariable("id") Prescription docs,
			@PathVariable("id") AppointmentTable appointmentTable, @PathVariable("id") Doctor d) {

		ap = billMakingRepo.findById((long) docs.getId());

		app = appointmentTableRepo.findById(appointmentTable.getId());
		Optional<Doctor> dd = doctorRepo.findById(d.getId());

		Combo combo = new Combo();
		combo.setAppointmentDetails(ap.get());
		combo.setDoc(dd.get());
		return new ResponseEntity<Combo>(combo, HttpStatus.OK);

	}

	@GetMapping("/pdf/generate")
	void getPdfs(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		this.billMaking.export(response);
	}

}
