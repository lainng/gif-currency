package com.piatnitsa.gifcurrency.dto;

import com.piatnitsa.gifcurrency.model.ExchangeRate;
import com.piatnitsa.gifcurrency.service.feign.ExchangeRateClient;

import java.util.Map;

/**
 * This class represents the DTO of the {@link ExchangeRate} that contains response information
 * from {@link ExchangeRateClient}.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class ExchangeRateDto {
    private Map<String, Double> rates;

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public ExchangeRateDto() {
    }

    public ExchangeRateDto(Map<String, Double> rates) {
        this.rates = rates;
    }
}
