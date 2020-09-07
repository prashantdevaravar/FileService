package com.fileservice.exception;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Response handler for exceptions
 *
 */
@XmlRootElement(name = "error")
public class ExceptionHandlerResponse {
	
	/**
	 * Constructor for migration exception response handler
	 * 
	 * @param message the message to set
	 */
	public ExceptionHandlerResponse(String message) {
		this.message = message;
	}
 
	/**
	 * General error message about nature of error
	 */
	private String message;

	/**
	 * Getter for error message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter for error message
	 * 
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
