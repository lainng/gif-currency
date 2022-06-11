package com.piatnitsa.gifcurrency.validator;

import com.piatnitsa.gifcurrency.exception.IncorrectParameterException;

/**
 * This class represents the validator for the HTTP request parameters.
 */
public class RequestParameterValidator {

    /**
     * Validates HTTP request parameter <code>currency-code</code>.
     * Throws the {@link IncorrectParameterException} when parameters are invalid.
     * @param currencyCode request parameter <code>currency-code</code>.
     */
    public static void validateCurrencyCode(String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            throw new IncorrectParameterException("badCurrencyCode.emptyLine");
        }

        if (currencyCode.length() > 3) {
            throw new IncorrectParameterException("badCurrencyCode.exceedingLengthLimit");
        }
    }

    /**
     * Validates HTTP request parameter <code>date</code>.
     * Throws the {@link IncorrectParameterException} when parameters are invalid.
     * @param date request parameter <code>date</code>.
     */
    public static void validateSearchDate(String date) {
        if (date == null || date.isEmpty()) {
            throw new IncorrectParameterException("badCurrencyCode.emptyDate");
        }

        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IncorrectParameterException("badCurrencyCode.malformedDate");
        }
    }
}
