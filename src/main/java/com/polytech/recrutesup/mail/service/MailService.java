package com.polytech.recrutesup.mail.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
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

	private void sendEmail(String destinataire, String objet, String contenu) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
 
            mimeMessage.setContent(contenu, "text/html");
            mimeMessageHelper.setSubject(SUBJECT_PREFIXE + objet);
            mimeMessageHelper.setFrom(new InternetAddress("ptut.recrutesup@gmail.com", "recruteSUP"));
            mimeMessageHelper.setTo(destinataire);
 
            mailSender.send(mimeMessageHelper.getMimeMessage());
 
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
	}
	
	public void sendEmailCreationStudentProfil(User user, String mdp) {
		this.sendEmail("ptut.recrutesup@gmail.com", "Création profil", MailObjectGenerator.confirmCreationStudentProfil(user.getFirstname(), user.getLastname(), mdp));
	}
	
	public void sendEmailCreationEmployeeProfil(User user, String mdp) {
		this.sendEmail("ptut.recrutesup@gmail.com", "Création profil", MailObjectGenerator.confirmCreationEmployeeProfil(user.getFirstname(), user.getLastname(), mdp));
	}
	
	public void sendEmailCreationCompanyProfil(Company company) {
		this.sendEmail("ptut.recrutesup@gmail.com", "Création profil", MailObjectGenerator.confirmCreationCompanyProfil(company.getName()));
	}
	
	public void sendEmailRecuperationPassword(String email, String mdp) {
		this.sendEmail("ptut.recrutesup@gmail.com", "Récupération mot de passe", MailObjectGenerator.recuperationPassword(mdp));
	}
	
	public void sendEmailConfirmationChangePassword(User user) {
		this.sendEmail("ptut.recrutesup@gmail.com", "Modification mot de passe", MailObjectGenerator.changePassword(user.getFirstname(), user.getLastname()));
	}
	
	public void sendEmailCreationCompanyWish(Company company, Student student) {
		this.sendEmail("ptut.recrutesup@gmail.com", "Vous avez reçu un voeu !", MailObjectGenerator.creationCompanyWish(company.getName(), student.getUser().getFirstname()));
	}
	
	public void sendEmailConfirmationStudentWish(Student student, Offer offer) {
		this.sendEmail("ptut.recrutesup@gmail.com", "Vous avez reçu un voeu !", MailObjectGenerator.creationStudentWish(student.getUser().getFirstname(), student.getUser().getLastname(), offer.getCompany().getName(), offer.getLabel()));
	}
	
	public void sendEmailMeetingCreationRequest(String dateMeeting, String message, Student sender, Offer interlocutor, User receiver) {
		this.sendEmail("ptut.recrutesup@gmail.com", "Demande de rencontre",
				MailObjectGenerator.creationMeetingRequest(dateMeeting,
														   message,
														   sender.getUser().getFirstname() + " " + sender.getUser().getLastname(),
														   sender.getUser().getMailAddress(),
														   interlocutor.getCreatedByUser().getFirstname() + " " + interlocutor.getCreatedByUser().getLastname() + " (" + interlocutor.getCompany().getName() + ")"));
	
	}
	
	public void sendEmailMeetingCreationRequestToAdmin(String dateMeeting, String message, Student sender, Offer interlocutor, List<Admin> receivers) {
		this.sendEmail("ptut.recrutesup@gmail.com", "Demande de rencontre",
				MailObjectGenerator.creationMeetingRequestToAdmin(dateMeeting,
															      message,
															      sender.getUser().getFirstname() + " " + sender.getUser().getLastname(),
															      sender.getUser().getMailAddress(),
															      interlocutor.getCreatedByUser().getFirstname() + " " + interlocutor.getCreatedByUser().getLastname() + " (" + interlocutor.getCompany().getName() + ")",
																  interlocutor.getCreatedByUser().getMailAddress()));
	
	}
	
	public void sendEmailMeetingCreationRequest(String dateMeeting, String message, User sender, Company company, Student interlocutor, User receiver) {
			this.sendEmail("ptut.recrutesup@gmail.com", "Demande de rencontre",
					MailObjectGenerator.creationMeetingRequest(dateMeeting,
							                                   message,
							                                   sender.getFirstname() + " " + sender.getLastname() + " (" + company.getName() + ")",
							                                   sender.getMailAddress(),
							                                   interlocutor.getUser().getFirstname()));
	}
	
	public void sendEmailMeetingCreationRequestToAdmin(String dateMeeting, String message, User sender, Company company, Student interlocutor, List<Admin> receivers) {
		this.sendEmail("ptut.recrutesup@gmail.com", "Demande de rencontre",
				MailObjectGenerator.creationMeetingRequestToAdmin(dateMeeting,
							                                      message,
							                                      sender.getFirstname() + " " + sender.getLastname() + " (" + company.getName() + ")",
							                                      sender.getMailAddress(),
							                                      interlocutor.getUser().getFirstname() + " " + interlocutor.getUser().getLastname(),
							                                      interlocutor.getUser().getMailAddress()));
}
}
