package com.api.admin.app.exception;

public class RestaurantDoNotExist extends RuntimeException {
    /**
	 * Custom Exception to handle
	 */
	private static final long serialVersionUID = 1L;

	public RestaurantDoNotExist(String message) {
        super(message);
    }
}
