package com.udemy.curso.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailServices extends AbstractEmailServide {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailServices.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		
		LOG.info("Simulando envio de e-mail...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado.");
		
	}
	
	

}
