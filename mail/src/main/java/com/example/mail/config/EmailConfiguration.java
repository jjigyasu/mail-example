package com.example.mail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.spring6.SpringTemplateEngine;

public class EmailConfiguration {

	@Autowired
	private SpringTemplateEngine thymeleafTemplateEngine;

	
}
