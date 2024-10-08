package com.bigcorp.project.business.exception;

/**
 * Lancée si un utilisateur est invalide 
 */
public class InvalidUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserException(String message) {
		super(message);
	}

}
