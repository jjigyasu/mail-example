package com.example.mail.dto;

import lombok.Data;

@Data
public class MailDTO {
	 private String[] recipentMailIds;
	 private String subject;
	 private String attachmentPath;
	 private String attachmentName;
	 private String content;
	 private String mimetype;
	 


}
