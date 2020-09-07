
package com.fileservice.exception;

/**
 * This is the subclass of RuntimeException those exceptions that can be thrown
 * during validating the input arguments received from request operation.
 */
public class InputDataValidationException extends RuntimeException {

	private static final long serialVersionUID = -7151243466311554271L;

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * 
	 * @param message the detail exception message.
	 */
	public InputDataValidationException(String message) {
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message with
	 * exception cause.
	 * 
	 * @param message the detail exception message.
	 * @param cause   the cause of exception
	 */
	public InputDataValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}
