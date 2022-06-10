package com.piatnitsa.gifcurrency.service;

import com.piatnitsa.gifcurrency.dto.ExchangeRateDto;
import com.piatnitsa.gifcurrency.dto.converter.DtoConverter;
import com.piatnitsa.gifcurrency.model.ExchangeRate;
import com.piatnitsa.gifcurrency.service.feign.ExchangeRateClient;
import com.piatnitsa.gifcurrency.validator.ExchangeRateDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represent the tools for working with {@link ExchangeRate} entity.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Service
public class ExchangeRateService {

    @Value("${exchangeRate.appId}")
    private String appId;
    private final DtoConverter<ExchangeRateDto, ExchangeRate> exchangeRateDtoConverter;
    private final ExchangeRateClient exchangeRateClient;

    @Autowired
    public ExchangeRateService(DtoConverter<ExchangeRateDto, ExchangeRate> exchangeRateDtoConverter,
                               ExchangeRateClient exchangeRateClient) {
        this.exchangeRateDtoConverter = exchangeRateDtoConverter;
        this.exchangeRateClient = exchangeRateClient;
    }

    /**
     * Fetches the latest exchange rate by the specified currency code.
     * @param currencyCode currency code.
     * @return the {@link ExchangeRate} entity that contains the latest exchange rate.
     */
    public ExchangeRate getLatestExchangeRate(String currencyCode) {
        ExchangeRateDto dto = exchangeRateClient.getLatestExchangeRate(appId, currencyCode);
        ExchangeRateDtoValidator.validate(dto);
        return exchangeRateDtoConverter.toEntity(dto);
    }

    /**
     * Fetches the exchange rate by the specified currency code on the specified date.
     * @param date the date.
     * @param currencyCode the currency code.
     * @return the {@link ExchangeRate} entity that contains the exchange rate on the specified date.
     */
    public ExchangeRate getExchangeRateByDate(String date, String currencyCode) {
        ExchangeRateDto rateByDateDto = exchangeRateClient.getExchangeRateByDate(date, appId, currencyCode);
        ExchangeRateDtoValidator.validate(rateByDateDto);
        return exchangeRateDtoConverter.toEntity(rateByDateDto);
    }

    /**
     * Fetches yesterday's exchange rate by the specified currency code.
     * @param currencyCode the currency code.
     * @return the {@link ExchangeRate} entity that contains yesterday's exchange rate.
     */
    public ExchangeRate getYesterdayRate(String currencyCode) {
        String yesterday = LocalDateTime.now()
                .minusDays(1)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return getExchangeRateByDate(yesterday, currencyCode);
    }

}
