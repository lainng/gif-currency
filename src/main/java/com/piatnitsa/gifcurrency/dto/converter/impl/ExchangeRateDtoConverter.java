package com.piatnitsa.gifcurrency.dto.converter.impl;

import com.piatnitsa.gifcurrency.dto.converter.DtoConverter;
import com.piatnitsa.gifcurrency.dto.ExchangeRateDto;
import com.piatnitsa.gifcurrency.model.ExchangeRate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ExchangeRateDtoConverter implements DtoConverter<ExchangeRateDto, ExchangeRate> {

    @Override
    public ExchangeRate toEntity(ExchangeRateDto dto) {
        ExchangeRate rate = new ExchangeRate();
        Map<String, Double> rates = dto.getRates();
        rates.values()
                .stream()
                .findFirst()
                .ifPresent(rate::setRate);
        return rate;
    }
}
