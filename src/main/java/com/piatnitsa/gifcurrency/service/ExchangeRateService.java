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

    public ExchangeRate getLatestExchangeRate(String currencyCode) {
        ExchangeRateDto dto = exchangeClient.getLatestExchangeRate(appId, currencyCode);
        ExchangeRateDtoValidator.validate(dto);
        return exchangeRateDtoConverter.toEntity(dto);
    }

    public ExchangeRate getExchangeRateByDate(String date, String currencyCode) {
        ExchangeRateDto rateByDateDto = exchangeClient.getExchangeRateByDate(date, appId, currencyCode);
        ExchangeRateDtoValidator.validate(rateByDateDto);
        return exchangeRateDtoConverter.toEntity(rateByDateDto);
    }

    public ExchangeRate getYesterdayRate(String currencyCode) {
        String yesterday = LocalDateTime.now()
                .minusDays(1)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return getExchangeRateByDate(yesterday, currencyCode);
    }

}
