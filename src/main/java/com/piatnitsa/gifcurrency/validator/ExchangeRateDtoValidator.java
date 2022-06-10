package com.piatnitsa.gifcurrency.validator;

import com.piatnitsa.gifcurrency.dto.ExchangeRateDto;
import com.piatnitsa.gifcurrency.exception.IncorrectParameterException;

/**
 * This class represents exchange rate DTO validator.
 */
public class ExchangeRateDtoValidator {

    /**
     * Validates {@link ExchangeRateDto}. Throws the {@link IncorrectParameterException} when DTO are invalid.
     * @param dto checked object.
     */
    public static void validate(ExchangeRateDto dto) {
        if (dto.getRates().isEmpty()) {
            throw new IncorrectParameterException("badCurrencyCode.codeDoesntExist");
        }
    }
}
