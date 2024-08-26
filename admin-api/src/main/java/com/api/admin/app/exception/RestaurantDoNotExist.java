package com.api.admin.app.exception;

import lombok.Getter;

@Getter
public class RestaurantDoNotExist extends RuntimeException {
    /**
	 * Custom Exception to handle
	 */
	 private final int errorCode;
	private static final long serialVersionUID = 1L;

	public RestaurantDoNotExist(String message) {
		super(message);
        this.errorCode = 0;
		
    }
	public int getErrorCode() {
        return errorCode;
    }
}
