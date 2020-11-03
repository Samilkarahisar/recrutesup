package com.polytech.recrutesup.exceptions;

public class RecruteSupApplicationException extends RuntimeException {

	private static final long serialVersionUID = -6481606876742980422L;
	private final RecruteSupErrorType type;
	
	public RecruteSupApplicationException(RecruteSupErrorType type) {
		super(type.getTitle());
		this.type = type;
	}
	
	public RecruteSupApplicationException(RecruteSupErrorType type, Throwable cause) {
		super(type.getTitle(), cause);
		this.type = type;
	}

}
