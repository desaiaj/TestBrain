package ca.testbrain.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailServiceImpl {
	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(String from, String subject, String name, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("ankd605@gmail.com");

		message.setSubject(subject);
		message.setText(name + "  " + from + " " + msg);
		mailSender.send(message);
	}

//	public void sendMailWithInline(String name, String messageBody, String footer, String to, String subject)
//			throws MessagingException {// Prepare the evaluation context
//		final Context ctx = new Context();
//		ctx.setVariable("name", name);
//		ctx.setVariable("message", messageBody);
//		ctx.setVariable("footer", footer);// Prepare message using a Spring helper
//		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
//		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//		message.setSubject(subject);
//		message.setFrom("account@gmail.com");
//		message.setTo(to);
//		// Create the HTML body using Thymeleaf
//		final String htmlContent = this.templateEngine.process("index.html", ctx);
//		message.setText(htmlContent, true); // true = isHtml
//		this.mailSender.send(mimeMessage);
//	}
}
