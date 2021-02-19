package com.polytech.recrutesup.mail.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.entities.Admin;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.Offer;
import com.polytech.recrutesup.entities.Student;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.mail.config.MailObjectGenerator;

@Service
public class MailService {
	
	private final String SUBJECT_PREFIXE = "RecruteSUP - Forum alternance Polytech Lyon : ";
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Environment env;

	private void sendEmail(String destinataire, String objet, String contenu) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
 
            mimeMessage.setContent(contenu, "text/html");
            mimeMessageHelper.setSubject(SUBJECT_PREFIXE + objet);
            mimeMessageHelper.setFrom(new InternetAddress("ptut.recrutesup@gmail.com", "recruteSUP"));
            
            if(env.getProperty("spring.mail.fakeReceiver").equals("true")) {
            	mimeMessageHelper.setTo("ptut.recrutesup@gmail.com");
            } else {
            	mimeMessageHelper.setTo(destinataire);
            }
            
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
 
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	}
	
	public void sendEmailCreationStudentProfil(User user, String mdp) {
		this.sendEmail(user.getMailAddress(), "Création profil", MailObjectGenerator.confirmCreationStudentProfil(user.getFirstname(), user.getLastname(), mdp));
	}
	
	public void sendEmailCreationEmployeeProfil(User user, String mdp) {
		this.sendEmail(user.getMailAddress(), "Création profil", MailObjectGenerator.confirmCreationEmployeeProfil(user.getFirstname(), user.getLastname(), mdp));
	}
	
	public void sendEmailCreationCompanyProfil(Company company) {
		this.sendEmail(company.getMailAddress(), "Création profil", MailObjectGenerator.confirmCreationCompanyProfil(company.getName()));
	}
	
	public void sendEmailRecuperationPassword(String email, String mdp) {
		this.sendEmail(email, "Récupération mot de passe", MailObjectGenerator.recuperationPassword(mdp));
	}
	
	public void sendEmailConfirmationChangePassword(User user) {
		this.sendEmail(user.getMailAddress(), "Modification mot de passe", MailObjectGenerator.changePassword(user.getFirstname(), user.getLastname()));
	}
	
	public void sendEmailCreationCompanyWish(Company company, Student student) {
		this.sendEmail(student.getUser().getMailAddress(), "Vous avez reçu un voeu !", MailObjectGenerator.creationCompanyWish(company.getName(), student.getUser().getFirstname()));
	}
	
	public void sendEmailConfirmationStudentWish(Student student, Offer offer) {
		this.sendEmail(offer.getCreatedByUser().getMailAddress(), "Vous avez reçu un voeu !", MailObjectGenerator.creationStudentWish(student.getUser().getFirstname(), student.getUser().getLastname(), offer.getCompany().getName(), offer.getLabel()));
	}
	
	public void sendEmailMeetingCreationRequestWithDate(String dateMeeting, String message, User sender, Company company, Student interlocutor, User receiver) {
			this.sendEmail(receiver.getMailAddress(), "Demande de rencontre",
					MailObjectGenerator.creationMeetingRequestWithDate(dateMeeting,
							                                   message,
							                                   sender.getFirstname() + " " + sender.getLastname() + " (" + company.getName() + ")",
							                                   sender.getMailAddress(),
							                                   interlocutor.getUser().getFirstname()));
	}
	
	public void sendEmailMeetingCreationRequestWithoutDate(String message, User sender, Company company, Student interlocutor, User receiver) {
		this.sendEmail(receiver.getMailAddress(), "Demande de rencontre",
				MailObjectGenerator.creationMeetingRequestWithoutDate(message,
						                                   sender.getFirstname() + " " + sender.getLastname() + " (" + company.getName() + ")",
						                                   sender.getMailAddress(),
						                                   interlocutor.getUser().getFirstname()));
	}
	
	public void sendEmailMeetingCreationRequestToAdminWithDate(String dateMeeting, String message, User sender, Company company, Student interlocutor, List<Admin> receivers) {
		for(Admin admin: receivers) {
			this.sendEmail(admin.getUser().getMailAddress(), "Demande de rencontre",
					MailObjectGenerator.creationMeetingRequestToAdminWithDate(dateMeeting,
								                                      message,
								                                      sender.getFirstname() + " " + sender.getLastname() + " (" + company.getName() + ")",
								                                      sender.getMailAddress(),
								                                      interlocutor.getUser().getFirstname() + " " + interlocutor.getUser().getLastname(),
								                                      interlocutor.getUser().getMailAddress()));
		}
	}
	
	public void sendEmailMeetingCreationRequestToAdminWithoutDate(String message, User sender, Company company, Student interlocutor, List<Admin> receivers) {
		for(Admin admin: receivers) {
			this.sendEmail(admin.getUser().getMailAddress(), "Demande de rencontre",
					MailObjectGenerator.creationMeetingRequestToAdminWithoutDate(message,
								                                      sender.getFirstname() + " " + sender.getLastname() + " (" + company.getName() + ")",
								                                      sender.getMailAddress(),
								                                      interlocutor.getUser().getFirstname() + " " + interlocutor.getUser().getLastname(),
								                                      interlocutor.getUser().getMailAddress()));
		}
	}
	
	public void sendEmailMessage(String message, User sender, Offer offer, User receiver) {
		this.sendEmail(receiver.getMailAddress(), "Vous avez un message",
				MailObjectGenerator.emailMessage(message,
		                                         sender.getFirstname() + " " + sender.getLastname(),
		                                         sender.getMailAddress(),
		                                         offer.getLabel(),
		                                         receiver.getFirstname() + " " + receiver.getLastname()));
	}
	
	public void sendEmailMessage(String message, User sender, Company company, User receiver) {
		this.sendEmail(receiver.getMailAddress(), "Vous avez un message",
				MailObjectGenerator.emailMessage(message,
		                                         sender.getFirstname() + " " + sender.getLastname() + " (" + company.getName() + ")",
		                                         sender.getMailAddress(),
		                                         receiver.getFirstname() + " " + receiver.getLastname()));
	}
	
	public void sendAdminMessage(String message, User receiver) {
		this.sendEmail(receiver.getMailAddress(), "Vous aves un message des admins",
				MailObjectGenerator.adminMessage(message));
	}
	
}
