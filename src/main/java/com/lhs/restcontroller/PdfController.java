//package com.lhs.restcontroller;
//
//import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.lhs.service.PdfService;
//import com.lowagie.text.DocumentException;
//
////@Controller
//@RestController
////@RequestMapping("/api")
//public class PdfController {
//	
//	
//	
////	@Autowired
////	public PdfController(PdfService pdfService) {
////		super();
////		this.pdfService = pdfService;
////	}
//
//
//
//
//	 private final PdfService pdfService;
//
//	    public PdfController(PdfService pdfService) {
//	        this.pdfService = pdfService;
//	    }
//	
//	
//	
//	    
//	    
//	    
//	    
//	    
//	
//	    
//	    
//	    
//	    
//	
//  @GetMapping("/pdf/generate")
//	void getPdfs(HttpServletResponse response) throws DocumentException, IOException
//	{ 
//		        response.setContentType("application/pdf");
//		        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
//		        String currentDateTime = dateFormatter.format(new Date());
//
//		        String headerKey = "Content-Disposition";
//		        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
//		        response.setHeader(headerKey, headerValue);
//
//		        this.pdfService.export(response);
//		    }
//		
//		
//		
//		
//		
//		
//		
//		
//		
//	}
//	
//	
//
//
