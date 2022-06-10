package com.piatnitsa.gifcurrency.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

/**
 * This class presents entity which will be returned from controller in case generating exceptions.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@RestControllerAdvice
public class ExceptionsHandler {
    private final MessageSource messageSource;

    @Autowired
    public ExceptionsHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(IncorrectParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIncorrectParameterException(IncorrectParameterException ex) {
        String errorMessage = messageSource.getMessage(ex.getMessage(), null, Locale.getDefault());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setErrorMessage(errorMessage);
        return errorResponse;
    }
}
