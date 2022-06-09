package com.piatnitsa.gifcurrency.exception;

/**
 * This class represents objects that will be returned as a response when an error is generated.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
