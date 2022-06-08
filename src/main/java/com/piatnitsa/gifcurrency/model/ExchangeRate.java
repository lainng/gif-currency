package com.piatnitsa.gifcurrency.model;

import java.util.Date;
import java.util.Map;

public class ExchangeRate {
    private Date timestamp;
    private Map<String, Double> rates;

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        addMillis(timestamp);
        this.timestamp = timestamp;
    }

    private void addMillis(Date timestamp) {
        timestamp.setTime(timestamp.getTime() * 1000);
    }
}
