package com.piatnitsa.gifcurrency.exception;

/**
 * This class represents the exception that is thrown when the validation of entity parameters fails.
 *
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class IncorrectParameterException extends RuntimeException {

    public IncorrectParameterException() {
    }

    public IncorrectParameterException(String message) {
        super(message);
    }

    public IncorrectParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectParameterException(Throwable cause) {
        super(cause);
    }
}
