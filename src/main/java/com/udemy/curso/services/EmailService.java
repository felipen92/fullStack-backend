package com.udemy.curso.services;

import org.springframework.mail.SimpleMailMessage;

import com.udemy.curso.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido p);
	void sendEmail(SimpleMailMessage msg);

}
