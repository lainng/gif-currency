package com.piatnitsa.gifcurrency.validator;

import com.piatnitsa.gifcurrency.dto.ExchangeRateDto;
import com.piatnitsa.gifcurrency.exception.IncorrectParameterException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class ExchangeRateDtoValidatorTest {
    private static final String CURRENCY_CODE = "BYN";
    private static final Double EXCHANGE_RATE = 2.6043;

    private final ExchangeRateDto CORRECT_DTO = new ExchangeRateDto(
            Collections.singletonMap(CURRENCY_CODE, EXCHANGE_RATE));
    private final ExchangeRateDto INCORRECT_DTO = new ExchangeRateDto(Collections.emptyMap());

    @Test
    void validateCorrectDto_thatOk() {
        Assertions.assertDoesNotThrow(() -> ExchangeRateDtoValidator.validate(CORRECT_DTO));
    }

    @Test
    void validateIncorrectDto_thatThrowEx() {
        Assertions.assertThrows(IncorrectParameterException.class,
                () -> ExchangeRateDtoValidator.validate(INCORRECT_DTO));
    }
}