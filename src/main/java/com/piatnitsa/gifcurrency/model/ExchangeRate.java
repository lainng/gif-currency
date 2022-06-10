package com.piatnitsa.gifcurrency.model;

import java.util.Objects;

/**
 * This class represents the entity of the exchange rates.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class ExchangeRate {
    private Double rate;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate rate1 = (ExchangeRate) o;
        return Objects.equals(rate, rate1.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ExchangeRate{");
        builder.append("rate=").append(rate).append('}');
        return builder.toString();
    }
}
