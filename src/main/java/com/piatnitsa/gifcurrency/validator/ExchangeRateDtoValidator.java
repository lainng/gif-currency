package com.piatnitsa.gifcurrency.validator;

import com.piatnitsa.gifcurrency.dto.ExchangeRateDto;
import com.piatnitsa.gifcurrency.exception.IncorrectParameterException;

public class ExchangeRateDtoValidator {

    public static void validate(ExchangeRateDto dto) {
        if (dto.getRates().isEmpty()) {
            throw new IncorrectParameterException("badCurrencyCode.codeDoesntExist");
        }
    }
}
