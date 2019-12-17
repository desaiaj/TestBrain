package ca.testbrain.controller;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class SpringMailConfig {
	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);
		mailSender.setUsername("testsher3@gmail.com");
		mailSender.setPassword("Test.Sher3");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp"); // for ssl error o authentication
		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		// props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}
}
