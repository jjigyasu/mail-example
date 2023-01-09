/**
 * 
 */
package com.example.mail.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

import com.example.mail.dto.MailDTO;
import com.example.mail.service.MailServiceImpl;

import ch.qos.logback.classic.Logger;
/**
 * @author Balasudhakar
 *
 */
@WebMvcTest(value =MailController.class)
class MailControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Mock 
	JavaMailSender emailSender;
	@MockBean
	private MailServiceImpl mailService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this); 
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.example.mail.controller.MailController#sendMail()}.
	 * @throws Exception 
	 */
	@Test
	void testSendMail() throws Exception {
		doNothing().when(mailService).sendMail(Mockito.any(MailDTO.class));
        mockMvc.perform(get("/sendmail")).andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link com.example.mail.controller.MailController#sendMailA()}.
	 * @throws Exception 
	 */
	@Test
	void testSendMailA() throws Exception {
		doNothing().when(mailService).sendMailWithAttachement(Mockito.any(MailDTO.class));
        mockMvc.perform(get("/sendmailA")).andExpect(status().isOk());
	}
	

	/**
	 * Test method for
	 * {@link com.example.mail.controller.MailController#sendMail()}.
	 * @throws Exception 
	 */
	@Test
	void testSendMailWithException() throws Exception {
		doThrow(new MailSendException("exception test case")).when(emailSender).send(Mockito.any(SimpleMailMessage.class));
  	    mockMvc.perform(get("/sendmail")).andExpect(status().isOk());
	}

	/**
	 * Test method for
	 * {@link com.example.mail.controller.MailController#sendMailA()}.
	 * @throws Exception 
	 */
	@Test
	void testSendMailAWithException() throws Exception {
		doThrow(new MailSendException("exception test case")).when(mailService).sendMailWithAttachement(Mockito.any(MailDTO.class));
        mockMvc.perform(get("/sendmailA")).andExpect(status().isOk());
	}
	
	
}
