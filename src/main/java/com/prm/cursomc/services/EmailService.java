package com.prm.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.prm.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
}
