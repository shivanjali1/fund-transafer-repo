package com.hcl.springbootbankapp.serviceimpl;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.mail.SimpleMailMessage;
	import org.springframework.mail.javamail.JavaMailSender;
	import org.springframework.stereotype.Component;

	@Component
	public class EmailServiceImpl {

		@Autowired
	    private JavaMailSender javaMailSender;
		
		public void sendSimpleMessage(String subject,String body, String emailId) {
			
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(emailId);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(body);
			javaMailSender.send(simpleMailMessage);
		}
		
	}


