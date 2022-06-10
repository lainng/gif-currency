package com.piatnitsa.gifcurrency.validator;

import com.piatnitsa.gifcurrency.exception.IncorrectParameterException;

/**
 * This class represents the validator for the HTTP request parameters.
 */
public class RequestParameterValidator {

    /**
     * Validates HTTP request parameters. Throws the {@link IncorrectParameterException} when parameters are invalid.
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
}
