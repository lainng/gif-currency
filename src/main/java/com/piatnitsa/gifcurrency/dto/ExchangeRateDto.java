package com.piatnitsa.gifcurrency.dto;

import java.util.Map;

public class ExchangeRateDto {
    private Map<String, Double> rates;

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
