package com.lhs.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.lhs.dao.AppointmentTableRepo;
import com.lhs.dao.BillMakingRepo;
import com.lhs.dao.DoctorRepo;
import com.lhs.entity.AppointmentDetails;
import com.lhs.entity.AppointmentTable;
import com.lhs.entity.Doctor;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class BillMakingService {

	
	Optional<AppointmentDetails> ap;
	Optional<AppointmentTable> app;
	
	
	@Autowired
	AppointmentTableRepo appointmentTableRepo;
	
	
	@Autowired
	DoctorRepo doctorRepo;
	
	
	
	@Autowired
	BillMakingRepo billMakingRepo ;
	
	
	
	
	public void makeBill(AppointmentDetails appointmentDetails,Doctor  doc)
	{
		  
		//appointmentTable.setId(appointmentTable.getId());
		
		//appointmentDetails.setAppointmentTable(appointmentTable);
			
			doc.setId(doc.getId());
			
			if(doctorRepo.existsById(doc.getId())) {
			
				doc.getId();
				
				appointmentDetails.setDoctor(doc);
				
				AppointmentDetails ad= 	billMakingRepo.save(appointmentDetails);
				
		
		
	}
			else
			{
				throw new RuntimeException("Deoesnt Exists");
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Optional<AppointmentDetails> getBill(AppointmentDetails  docs, AppointmentTable appointmentTable)
	{
		ap=  billMakingRepo.findById((long) docs.getId());
		
		 app=	appointmentTableRepo.findById(appointmentTable.getId());
		 
		  return  ap;
	}
	
	
	
	
	 public void export(HttpServletResponse response) throws IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());

	        document.open();
	        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        fontTitle.setSize(18);

	        Paragraph paragraph = new Paragraph("Welcome To lhs", fontTitle);
	        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

	        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
	        fontParagraph.setSize(12);

	        Paragraph paragraph2 = new Paragraph("Medical Report "+ap, fontParagraph);
	        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

	        document.add(paragraph);
	        document.add(paragraph2);
	        document.close();
	
	
	
	
}



}
	



	




	
	
	
	
	
	

