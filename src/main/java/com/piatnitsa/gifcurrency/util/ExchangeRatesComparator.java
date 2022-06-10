package com.piatnitsa.gifcurrency.util;

import com.piatnitsa.gifcurrency.model.ExchangeRate;
import org.springframework.stereotype.Component;

import java.util.Comparator;

/**
 * This class represents an implementation of {@link Comparator} interface.
 */
@Component
public class ExchangeRatesComparator implements Comparator<ExchangeRate> {

    @Override
    public int compare(ExchangeRate o1, ExchangeRate o2) {
        return Double.compare(o1.getRate(), o2.getRate());
    }
}
