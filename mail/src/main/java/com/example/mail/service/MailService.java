package com.example.mail.service;

import com.example.mail.dto.MailDTO;

public interface MailService {

	void sendMail(MailDTO emailMailDTO);

	void sendMailWithAttachement(MailDTO emailMailDTO);
}
