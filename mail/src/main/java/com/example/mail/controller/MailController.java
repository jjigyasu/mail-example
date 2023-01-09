package com.example.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mail.dto.MailDTO;
import com.example.mail.service.MailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MailController {
	@Autowired
	MailService mailservice;

	@GetMapping("/sendmail")
	void sendMail() {
		try {
			MailDTO emailMailDTO = new MailDTO();
			emailMailDTO.setRecipentMailIds(new String[] { "bala.sudhakar@gmail.com" });
			emailMailDTO.setContent("Hi Bala how are you ");
			emailMailDTO.setSubject("test mail ");
			mailservice.sendMail(emailMailDTO);
		} catch (MailException e) {
			log.error("MailException", e);
		}
	}

	@GetMapping("/sendmailA")
	void sendMailA() {
		try {
			MailDTO emailMailDTO = new MailDTO();
			emailMailDTO.setRecipentMailIds(new String[] { "bala.sudhakar@gmail.com" });
			emailMailDTO.setContent("Hi Bala how are you ");
			emailMailDTO.setSubject("test mail ");
			emailMailDTO.setAttachmentPath("E:\\.m2\\Downloads\\myPhoto.jpg");
			emailMailDTO.setAttachmentName("MyPhoto.jpg");
			mailservice.sendMailWithAttachement(emailMailDTO);
		} catch (MailException e) {
			log.error("MailException ", e);
		}
	}
}
