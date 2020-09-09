/**
 * 
 */
package com.fileservice.exception;

/**
 * @author Prashant
 *
 */
public class MyFileNotFoundException extends Exception {
	private static final long serialVersionUID = -7151243466311554273L;
	 public MyFileNotFoundException(String message) {
	        super(message);
	    }

	    public MyFileNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
