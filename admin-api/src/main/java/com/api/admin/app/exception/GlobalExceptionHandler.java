
 package com.api.admin.app.exception;
 
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorMessages errorMessages;

    @Autowired
    public GlobalExceptionHandler(ErrorMessages errorMessages) {
        this.errorMessages = errorMessages;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
        String errorMessage = errorMessages.getErrorMessage(ex.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RestaurantDoNotExist.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(RestaurantDoNotExist ex) {
        String errorMessage = errorMessages.getErrorMessage(ex.getErrorCode());
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenException.class) public ResponseEntity<String>
     handleInvalidTokenException(InvalidTokenException ex) { return new
    ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED); }
}
 
 /*
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.ControllerAdvice; import
 * org.springframework.web.bind.annotation.ExceptionHandler; import
 * org.springframework.web.context.request.WebRequest;
 * 
 * @ControllerAdvice public class GlobalExceptionHandler { private final
 * ErrorMessages errorMessages;
 * 
 * @Autowired public GlobalExceptionHandler(ErrorMessages errorMessages) {
 * this.errorMessages = errorMessages; }
 * 
 * @ExceptionHandler(UserAlreadyExistsException.class) public
 * ResponseEntity<String>
 * handleUserAlreadyExistsException(UserAlreadyExistsException ex,WebRequest
 * request) { //String errorMessage =
 * errorMessages.getErrorMessage(ex.getErrorCode()); return new
 * ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT); }
 * 
 * @ExceptionHandler(RestaurantDoNotExist.class) public ResponseEntity<String>
 * handleUserNotFoundException(RestaurantDoNotExist ex) { return new
 * ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); }
 * 
 * @ExceptionHandler(InvalidTokenException.class) public ResponseEntity<String>
 * handleInvalidTokenException(InvalidTokenException ex) { return new
 * ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED); }
 * 
 * 
 * }
 */