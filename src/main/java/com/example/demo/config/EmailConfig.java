package com.example.demo.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

	
	@Value("${spring.mail.host}")
	private String mailHost;
	
	@Value("${spring.mail.port}")
	private String mailPort;
	
	@Value("${spring.mail.username}")
	private String mailUsername;
	
	@Value("${spring.mail.password}")
	private String mailpassword;
	
	
	@Bean
	
	 JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		
		javaMailSender.setHost(mailHost);
		javaMailSender.setPort(Integer.parseInt(mailPort));
		javaMailSender.setUsername(mailUsername);
		javaMailSender.setPassword(mailpassword);
		
		Properties p = javaMailSender.getJavaMailProperties();
		
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.transport.protocol", "smtp");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.debug", "true");

		
		return javaMailSender;

}
}
