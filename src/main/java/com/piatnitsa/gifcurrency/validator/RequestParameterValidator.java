package com.piatnitsa.gifcurrency.validator;

import com.piatnitsa.gifcurrency.exception.IncorrectParameterException;

public class RequestParameterValidator {

    public static void validateCurrencyCode(String currencyCode) {
        if (currencyCode.isEmpty()) {
            throw new IncorrectParameterException("badCurrencyCode.emptyLine");
        }

        if (currencyCode.length() > 3) {
            throw new IncorrectParameterException("badCurrencyCode.exceedingLengthLimit");
        }
    }
}
