package com.udemy.curso.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.udemy.curso.domain.Pedido;

public abstract class AbstractEmailServide implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido p) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(p);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido p) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(p.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado!! Codigo: " + p.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(p.toString());
		return sm;
	}
}
