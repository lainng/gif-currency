package com.piatnitsa.gifcurrency.exception;

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
