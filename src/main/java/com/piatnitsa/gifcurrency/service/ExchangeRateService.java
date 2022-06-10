package com.piatnitsa.gifcurrency.service;

import com.piatnitsa.gifcurrency.dto.ExchangeRateDto;
import com.piatnitsa.gifcurrency.dto.converter.DtoConverter;
import com.piatnitsa.gifcurrency.model.ExchangeRate;
import com.piatnitsa.gifcurrency.service.feign.ExchangeClient;
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
    private final ExchangeClient exchangeClient;

    @Autowired
    public ExchangeRateService(DtoConverter<ExchangeRateDto, ExchangeRate> exchangeRateDtoConverter,
                               ExchangeClient exchangeClient) {
        this.exchangeRateDtoConverter = exchangeRateDtoConverter;
        this.exchangeClient = exchangeClient;
    }

    /**
     * Fetches the latest exchange rate by the specified currency code.
     * @param currencyCode currency code.
     * @return the {@link ExchangeRate} entity that contains the latest exchange rate.
     */
    public ExchangeRate getLatestExchangeRate(String currencyCode) {
        ExchangeRateDto dto = exchangeClient.getLatestExchangeRate(appId, currencyCode);
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
        ExchangeRateDto rateByDateDto = exchangeClient.getExchangeRateByDate(date, appId, currencyCode);
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
