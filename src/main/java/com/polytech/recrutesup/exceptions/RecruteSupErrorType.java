package com.polytech.recrutesup.exceptions;

public enum RecruteSupErrorType{

	USER_UNKNOWN("Utilisateur inconnu", "l'utilisateur ne peut pas être trouvée en BDD"),
	
	STUDENT_UNKNOWN("Etudiant inconnu", "l'étudiant ne peut pas être trouvée en BDD"),
	STUDENT_ALREADY_CREATED("Etudiant déjà créé","l'étudiant existe déjà en BDD et ne peut être créé une deuxième fois"),
	STATE_STUDENT_INCORRECT("Statut de l'étudiant incorrecte", "le statut de l'étudiant est incorrecte"),
	UPDATE_STATE_STUDENT_INVALID("Impossible de modifier le statut de l'étudiant", "le statut de l'étudiant ne peut pas être modifié"),
	
	ROLE_STUDENT_UNKNOWN("Role Etudiant inconnu", "le role Etudiant souhaité n'existe pas en BDD"),
	ROLE_COMPANY_UNKNOWN("Role Entreprise inconnu", "le role Entreprise souhaité n'existe pas en BDD"),
	ROLE_ADMIN_UNKNOWN("Role Admin inconnu", "le role Admin souhaité n'existe pas en BDD"),
	
	COMPANY_UNKNOWN("Entreprise inconnue", "l'entreprise ne peut pas être trouvée en BDD"),
	COMPANY_ALREADY_CREATED("Entreprise déjà créée","l'entreprise existe déjà en BDD et ne peut être créée une deuxième fois"),
	STATE_COMPANY_INCORRECT("Statut de l'entreprise incorrect", "le statut de l'entreprise est incorrect"),
	UPDATE_STATE_COMPANY_INVALID("Impossible de modifier le statut de l'entreprise", "le statut de l'entreprise ne peut pas être modifié"),
	
	EMPLOYEE_UNKNOWN("Employé inconnu", "l'employé ne peut pas être trouvé en BDD"),
	EMPLOYEE_ALREADY_CREATED("Employé déjà créé", "l'employé existe déjà en BDD et ne peut être créé une deuxième fois"),
	
	OFFER_UNKNOWN("Offre inconnue", "l'offre ne peut pas être trouvée en BDD"),
	STATE_OFFER_INCORRECT("Statut de l'offre incorrect", "le statut de l'offre est incorrect"),
	UPDATE_OFFER_INVALID("Impossible de modifier l'offre", "l'offre ne peut pas être modifié"),
	UPDATE_STATE_OFFER_INVALID("Impossible de modifier le statut de l'offre", "le statut de l'offre ne peut pas être modifié"),
	
	STUDENT_WISH_ALREADY_CREATED("voeu étudiant déjà créé", "le voeu reliant l'étudiant et l'offre existe déjà en BDD et ne peut être créé une deuxième fois"),
	COMPANY_WISH_ALREADY_CREATED("voeu entreprise déjà créé", "le voeu reliant l'entreprise et l'étudiant existe déjà en BDD et ne peut être créé une deuxième fois"),
	
	ADMIN_UNKNOWN("Admin inconnu", "l'admin souhaité n'existe pas en BDD"),
	
	EMAIL_ERROR("Erreur Service Email", "une erreur s'est produite avec le service d'envoi de mail automatique"),
	
	WISH_UNKNOWN("Voeu inconnu", "Le voeu souhaité n'existe pas en BDD"),
	WISH_BAD_TYPE("Erreur de type de voeu", "Le type du voeu traité n'est pac valide"),
	WISH_INCORRECT("Erreur voeu incorect", "Problème d'id ou de status sur le voeu"),
	STATE_WISH_INCORRECT("Statut du voeu incorrect", "le statut du voeu est incorrect"),
	UPDATE_STATE_WISH_INVALID("Impossible de modifier le statut du voeu", "le statut du voeu ne peut pas être modifié"),
	
	STATE_UNKNOWN("Statut inconnu", "le statut est inconnu");

	private final String title;
	private final String message;
	
	RecruteSupErrorType(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public String getTitle() {
		return this.title;
	}

	public String getMessage() {
		return this.message;
	}
}
