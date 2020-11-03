package com.polytech.recrutesup.exceptions;

public enum RecruteSupErrorType{

	STUDENT_UNKNOWN("Etudiant inconnu", "l'étudiant ne peut pas être trouvée en BDD"),
	STUDENT_ALREADY_CREATED("Etudiant déjà créé","l'étudiant existe déjà en BDD et ne peut être créé");

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
