package com.order.api.app.exception;

public class UserNotFoundException extends RuntimeException {
    /**
	 * Custom Exception to handle
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
        super(message);
    }
}
