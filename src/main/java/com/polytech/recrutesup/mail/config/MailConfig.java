package com.polytech.recrutesup.mail.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Autowired
	private Environment env;
    
	@Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));
 
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        javaMailProperties.put("mail.smtp.auth", env.getProperty("spring.mail.properties.mail.smtp.auth"));
        javaMailProperties.put("mail.debug", env.getProperty("mail.smtp.debug"));
        javaMailProperties.put("mail.transport.protocol", "smtp");
 
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}
