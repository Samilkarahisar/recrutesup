package com.polytech.recrutesup.mail.config;

public class MailObjectGenerator {
	
	public static final String confirmCreationStudentProfil(String firstname, String lastname, String mdp) {
		return 
				" <h3>" + firstname + " " + lastname + " : Votre profil Etudiant a bien été créé ! </h3>"
				+ "Voici votre mot de passe pour vous connecter : <h4>" + mdp + " </h4>"
				+ "Ce mot de passe est unique et doit rester <b>secret</b>, vous seul pouvez l'utiliser. <br>"
				+ "Vous pouvez vous connecter au site recruteSUP et le modifier dans votre profil Utilisateur <br><br>"
				+ "Très bonne journée à vous ! <br>"
				+ "L'équipe RecruteSUP";
	}
	
	public static final String confirmCreationEmployeeProfil(String firstname, String lastname, String mdp) {
		return 
				" <h3>" + firstname + " " + lastname + " : Votre profil Salarié a bien été créé ! </h3>"
				+ "Voici votre mot de passe pour vous connecter : <h4>" + mdp + " </h4>"
				+ "Ce mot de passe est unique et doit rester <b>secret</b>, vous seul pouvez l'utiliser. <br>"
				+ "Vous pouvez vous connecter au site recruteSUP et le modifier dans votre profil Utilisateur <br><br>"
				+ "Très bonne journée à vous ! <br>"
				+ "L'équipe RecruteSUP";
	}
	
	public static final String confirmCreationCompanyProfil(String companyName) {
		return 
				" <h3>L'entreprise " + companyName + " a bien été créée ! </h3>"
				+ "Les administrateurs de Polytech vont maintenant enregistrer les profils 'Salarié' liés à votre entreprise. <br>"
				+ "Vous pourrez donc bientôt vous connecter au site recruteSUP ! <br><br>"
				+ "Très bonne journée à vous ! <br>"
				+ "L'équipe RecruteSUP";
	}
	
	public static final String recuperationPassword(String mdp) {
		return 
				" <h3>Vous avez demandé à récupérer votre mot de passe</h3>"
				+ "Voici votre nouveau mot de passe pour vous connecter : <h4>" + mdp + " </h4>"
				+ "Ce mot de passe est unique et doit rester <b>secret</b>, vous seul pouvez l'utiliser. <br>"
				+ "Vous pouvez vous connecter au site recruteSUP et le modifier dans votre profil Utilisateur. <br><br>"
				+ "Si vous n'êtes pas à l'origine de cette demande, ignorez ce mail et contactez les administrateurs de Polytech Lyon. <br><br>"
				+ "Très bonne journée à vous ! <br>"
				+ "L'équipe RecruteSUP";
	}
	
	public static final String changePassword(String firstname, String lastname) {
		return 
				" <h3>" + firstname + " " + lastname + " : Vous avez demandé à changer votre mot de passe</h3>"
				+ "Votre mot de passe a bien été modifié !<br>"
				+ "Vous pouvez vous connecter au site recruteSUP et l'utiliser<br><br>"
				+ "Si vous n'êtes pas à l'origine de cette demande, ignorez ce mail et contactez les administrateurs de Polytech Lyon. <br><br>"
				+ "Très bonne journée à vous ! <br>"
				+ "L'équipe RecruteSUP";
	}
	
	public static final String creationCompanyWish(String companyName, String firstname) {
		return
				" <h3>" + firstname + " : Vous avez reçu un voeu d'une entreprise !</h3>"
				+ "L'entreprise <b>" + companyName + "</b> vous a envoyé un voeu et cherche à rentrer en contact avec vous.<br>"
				+ "Allez dans l'onglet 'Voeux' de l'application RecruteSUP pour consulter vos voeux envoyés et reçus.<br>"
				+ "Très bonne journée à vous ! <br>"
				+ "L'équipe RecruteSUP";
	}
	
	public static final String creationStudentWish(String firstname, String lastname, String companyName, String labelOffer) {
		return
				" <h3>" + companyName + " : Vous avez reçu un voeu d'un étudiant !</h3>"
				+ "<b>" + firstname + " " + lastname + "</b> vous a envoyé un voeu et cherche à rentrer en contact avec vous.<br>"
				+ "Cet étudiant est intéressé par l'offre que vous avez publié : <b>" + labelOffer + "</b><br>"
				+ "Allez dans l'onglet 'Voeux' de l'application RecruteSUP pour consulter vos voeux envoyés et reçus.<br>"
				+ "Très bonne journée à vous ! <br>"
				+ "L'équipe RecruteSUP";
	}
	
	public static final String creationMeetingRequestToAdmin(String dateMeeting, String message, String sender, String senderMail, String interlocutor, String interlocutorMail) {
		return
				" <h3>" + sender + " souhaite rencontrer " + interlocutor + "</h3>"
				+ "Message de " + sender + " : <br>"
				+ "<div style=\"white-space: pre-wrap;\"> \"" + message + "\" </div> <br><br>"  
				+ "Pouvez-vous organiser cette rencontre ? Elle doit avoir lieu le " + dateMeeting + ".<br>"
				+ "<b>Attention</b> , tous les administrateurs de Polytech Lyon ont reçu cette demande <br>"
				+ "Les participants obligatoires sont : <br>"
				+ " - " + sender + " : " + senderMail + "<br>"
				+ " - " + interlocutor + " : " + interlocutorMail + "<br>"
				+ "Très bonne journée à vous ! <br>"
				+ "L'équipe RecruteSUP";
	}
	
	public static final String creationMeetingRequest(String dateMeeting, String message, String sender, String senderMail, String interlocutor) {
		return
				" <h3>" + interlocutor + " : " + sender + " souhaite vous rencontrer </h3>"
				+ "Message de " + sender + " : <br>"
				+ "<div style=\"white-space: pre-wrap;\"> \"" + message + "\" </div> <br><br>"  
				+ "Cette rencontre doit avoir lieu le " + dateMeeting + "<br>"
				+ "Si vous souhaitez contacter cette personne directement son adresse mail est la suivante : " + senderMail + "<br>"
				+ "Très bonne journée à vous ! <br>"
				+ "L'équipe RecruteSUP";
	}
}
