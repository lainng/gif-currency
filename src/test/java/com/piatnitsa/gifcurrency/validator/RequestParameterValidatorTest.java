package com.piatnitsa.gifcurrency.validator;

import com.piatnitsa.gifcurrency.exception.IncorrectParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RequestParameterValidatorTest {

    private static final String CORRECT_CURRENCY_CODE = "BYN";
    private static final String INCORRECT_CURRENCY_CODE = "BYNNN";
    private static final String CORRECT_FORMAT_DATE = "2022-06-08";
    private static final String INCORRECT_FORMAT_DATE = "08.06.2022";

    @Test
    void validateCorrectCurrencyCode_thatOk() {
        Assertions.assertDoesNotThrow(() -> RequestParameterValidator.validateCurrencyCode(CORRECT_CURRENCY_CODE));
    }

    @Test
    void validateIncorrectCurrencyCode_thatThrowEx() {
        Assertions.assertThrows(IncorrectParameterException.class,
                () -> RequestParameterValidator.validateCurrencyCode(INCORRECT_CURRENCY_CODE));
    }

    @Test
    void validateCorrectFormatSearchDate_thatOk() {
        Assertions.assertDoesNotThrow(() -> RequestParameterValidator.validateSearchDate(CORRECT_FORMAT_DATE));
    }

    @Test
    void validateIncorrectFormatSearchDate_thatThrowEx() {
        Assertions.assertThrows(IncorrectParameterException.class,
                () -> RequestParameterValidator.validateSearchDate(INCORRECT_FORMAT_DATE));
    }
}