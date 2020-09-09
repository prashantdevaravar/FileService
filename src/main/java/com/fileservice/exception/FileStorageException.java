/**
 * 
 */
package com.fileservice.exception;

/**
 * @author Prashant
 *
 */
public class FileStorageException extends Exception {
	private static final long serialVersionUID = -7151243466311554272L;

	public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
