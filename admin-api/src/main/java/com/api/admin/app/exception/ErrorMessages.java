package com.api.admin.app.exception;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class ErrorMessages {
	private final MessageSource messageSource;
    private HttpStatus status;
    private List<String> errors;

    // Default no-argument constructor
    public ErrorMessages() {
        this.messageSource = null;  // or some default initialization
        this.status = null;          // or some default initialization
        this.errors = null;          // or some default initialization
    }

    public ErrorMessages(MessageSource messageSource1, HttpStatus status, List<String> errors) {
        this.messageSource = messageSource1;
        this.status = status;
        this.errors = errors;
    }

    public ErrorMessages(HttpStatus status, MessageSource message, String error) {
        this.status = status;
        this.messageSource = message;
        this.errors = Arrays.asList(error);
    }

    public String getErrorMessage(int errorCode) {
        return messageSource.getMessage("error." + errorCode, null, LocaleContextHolder.getLocale());
    }
    
}
