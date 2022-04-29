package com.lhs.restcontroller;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dao.AppointmentTableRepo;
import com.lhs.dao.BillMakingRepo;
import com.lhs.dao.DoctorRepo;
import com.lhs.entity.AppointmentDetails;
import com.lhs.entity.AppointmentTable;
import com.lhs.entity.Doctor;
import com.lhs.service.BillMakingService;
import com.lowagie.text.DocumentException;


@RestController
@RequestMapping("/api")
public class BillMakingController {
	
	//Optional<AppointmentDetails> ap;
	Optional<AppointmentTable> app;
	
//	@Autowired
//	 private  PdfService pdfService;

	
	

	@Autowired
	BillMakingService billMaking;
	
	
	@Autowired
	AppointmentTableRepo appointmentTableRepo;
	
	
	@Autowired
	DoctorRepo doctorRepo;
	
	@Autowired
	BillMakingRepo billMakingRepo;
	
	
	@PostMapping("/makeBills/{docid}")
	ResponseEntity<String> addBill(@RequestBody AppointmentDetails appointmentDetails,@PathVariable("docid") Doctor  doc)
	{
		
		
		billMaking.makeBill(appointmentDetails, doc);
		return new ResponseEntity<String>("Doc Id Exists.", HttpStatus.OK);
		
	
	
	}
	
	
	@GetMapping("/getBill/{id}/{ids}")
	public ResponseEntity<?> getBill(@PathVariable("id") AppointmentDetails  docs,@PathVariable("id") AppointmentTable appointmentTable)
	{AppointmentDetails a;
		
	
	Optional<AppointmentDetails> app=  billMaking.getBill(docs, appointmentTable);
		    
		//System.out.println(ap.get().getDoctor());
		     return new ResponseEntity<String>( app.get()+"", HttpStatus.OK);
		
	}

	
	
	
	
 @GetMapping("/pdf/generate")
	void getPdfs(HttpServletResponse response) throws DocumentException, IOException
	{ 
		        response.setContentType("application/pdf");
		        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		        String currentDateTime = dateFormatter.format(new Date());

		        String headerKey = "Content-Disposition";
		        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		        response.setHeader(headerKey, headerValue);

		        this.billMaking.export(response);
		    }
		
		
		
		
		
	
	
}
