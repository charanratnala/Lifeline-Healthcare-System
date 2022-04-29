package com.lhs.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lhs.entity.OtpEntity;
@Service
public class JavaMailService {

	@Autowired
	JavaMailSender javaMailSender;

	public void generateMail(String to, String sub, String body) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom("charanratnala98@gmail.com");
		mailMessage.setTo(to);
		mailMessage.setSubject(sub);
		mailMessage.setText(body);

		javaMailSender.send(mailMessage);

	}
	
	
	
	
	
	
public	void generateMailWithAttachment( String toMail,String sub,String body) throws MessagingException
	{
		
		MimeMessage message= javaMailSender.createMimeMessage();
		MimeMessageHelper mes = new MimeMessageHelper(message, true);
		//MimeMessageHelper mes= new MimeMessageHelper(message);
	try {
		mes.setTo(toMail);
		mes.setFrom("charanratnala98@gmail.com");
		mes.setSubject(sub);
		mes.setText(body);
//		FileSystemResource r= new FileSystemResource(new File(attachment));
//		
//		
//	mes.addAttachment(r.getFilename(), r);
		javaMailSender.send(message);
		
				
	}
	catch(MessagingException e)
	{
		e.printStackTrace();
	}

}








public void sendOTPEmail(OtpEntity customer, String OTP)
        throws UnsupportedEncodingException, MessagingException {
    MimeMessage message = javaMailSender.createMimeMessage();              
    MimeMessageHelper helper = new MimeMessageHelper(message);
     
    helper.setFrom("charanratnala98@gmail.com");
    helper.setTo("ratnalasaicharan98@gmail.com");
     
    String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
     
    String content = "<p>Hello "  + "</p>"
            + "<p>For security reason, you're required to use the following "
            + "One Time Password to login:</p>"
            + "<p><b>" + OTP + "</b></p>"
            + "<br>"
            + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
     
    helper.setSubject(subject);
     
    helper.setText(content, true);
     
    javaMailSender.send(message);      
}

//     
//    mailSender.send(message);      
//}
}
