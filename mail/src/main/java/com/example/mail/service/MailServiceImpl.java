package com.example.mail.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.example.mail.dto.MailDTO;
@Service
public class MailServiceImpl implements MailService {
	@Autowired
	public JavaMailSender emailSender;
	/**
	 * This method is used get simple mail message object
	 * @param emailMailDTO
	 * 
	 */
	@Override
	public void sendMail(MailDTO emailMailDTO)throws MailException {
		emailSender.send(getSimpleMail(emailMailDTO));
	}
	
	@Override
	public void sendMailWithAttachement(MailDTO emailMailDTO) throws MailException{
		emailSender.send(getMessageWithAttachment(emailMailDTO));
	}

	/**
	 * This method is used get simple mail message object
	 * 
	 * @param emailMailDTO
	 * @return SimpleMailMessage
	 */
	private SimpleMailMessage getSimpleMail(MailDTO emailMailDTO) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(emailMailDTO.getRecipentMailIds());
		mailMessage.setText(emailMailDTO.getContent());
		mailMessage.setSubject(mailMessage.getSubject());
		return mailMessage;
	}

	/**
	 * This method will generate MimeMessage Object 
	 * @param emailMailDTO
	 * @return MimeMessage
	 */
	private MimeMessagePreparator getMessageWithAttachment(MailDTO emailMailDTO) {
		String[] to = emailMailDTO.getRecipentMailIds();
		String subject = emailMailDTO.getSubject();
		String text = emailMailDTO.getContent();
		String pathToAttachment = emailMailDTO.getAttachmentPath();
		String attachmentName = emailMailDTO.getAttachmentName();
		return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,1);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            messageHelper.addAttachment(attachmentName, file);
        };
	}
}