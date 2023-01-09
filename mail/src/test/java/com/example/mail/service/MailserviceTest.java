/**
 * 
 */
package com.example.mail.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.example.mail.dto.MailDTO;

import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

/**
 * @author Balasudhakar
 *
 */
class MailserviceTest {

	@InjectMocks
	MailServiceImpl mailService;
	@Mock 
	JavaMailSender emailSender;
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
	 * Test method for {@link com.example.mail.service.MailServiceImpl#sendMail(com.example.mail.dto.MailDTO)}.
	 */
	@Test
	void testSendMail() {
		when(emailSender.createMimeMessage()).thenReturn(new MimeMessage((Session)
				 null));
        MailDTO emailMailDTO=new MailDTO();
        emailMailDTO.setRecipentMailIds(new String[]{"test@gmail.com"});
        emailMailDTO.setContent("Hi  how are you ");
        emailMailDTO.setSubject("test mail ");
        mailService.sendMail(emailMailDTO);
        verify(emailSender, times(1)).send(Mockito.any(SimpleMailMessage.class));
	}
	/**
	 * Test method for {@link com.example.mail.service.MailServiceImpl#sendMail(com.example.mail.dto.MailDTO)}.
	 */
	@Test() 
	void testsendMailException() {
		doThrow(new MailSendException("exception test case")).when(emailSender).send(Mockito.any(SimpleMailMessage.class));
	    MailDTO emailMailDTO=new MailDTO();
        emailMailDTO.setRecipentMailIds(new String[]{"test@gmail.com"});
        emailMailDTO.setContent("Hi how are you ");
        emailMailDTO.setSubject("test mail ");
        assertThrows(MailException.class, () -> {
        	mailService.sendMail(emailMailDTO);
        });
	}
	/**
	 * Test method for {@link com.example.mail.service.MailServiceImpl#sendMailWithAttachement(com.example.mail.dto.MailDTO)}.
	 */
	@Test
	void testsendMailWithAttachement() {
		when(emailSender.createMimeMessage()).thenReturn(new MimeMessage((Session)
				 null));
        MailDTO emailMailDTO=new MailDTO();
        emailMailDTO.setRecipentMailIds(new String[]{"test@gmail.com"});
        emailMailDTO.setContent("Hi how are you ");
        emailMailDTO.setSubject("test mail ");
        mailService.sendMailWithAttachement(emailMailDTO);
        verify(emailSender, times(1)).send(Mockito.any(MimeMessagePreparator.class));
	}
	/**
	 * Test method for {@link com.example.mail.service.MailServiceImpl#sendMailWithAttachement(com.example.mail.dto.MailDTO)}.
	 */
	@Test() 
	void testsendMailWithAttachementException() {
		doThrow(new MailSendException("exception test case")).when(emailSender).send(Mockito.any(MimeMessagePreparator.class));
	    MailDTO emailMailDTO=new MailDTO();
        emailMailDTO.setRecipentMailIds(new String[]{"test@gmail.com"});
        emailMailDTO.setContent("Hi how are you ");
        emailMailDTO.setSubject("test mail ");
        assertThrows(MailException.class, () -> {
        	mailService.sendMailWithAttachement(emailMailDTO);
        });
	}
}
