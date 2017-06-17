package com.actualize.mortgage.exceptions;

/**
 * @author sboragala
 *
 */
public class XMLServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;

    /**
     * @param message
     * @param cause
     */
    public XMLServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     */
    public XMLServiceException(String message) {
        super(message);
        this.message=message;
    }

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
