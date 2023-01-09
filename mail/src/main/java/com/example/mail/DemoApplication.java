package com.example.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.mail.service.MailService;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	MailService mailService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

}
