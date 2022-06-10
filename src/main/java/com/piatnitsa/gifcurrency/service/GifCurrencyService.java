package com.piatnitsa.gifcurrency.service;

import com.piatnitsa.gifcurrency.model.ExchangeRate;
import com.piatnitsa.gifcurrency.model.Gif;
import com.piatnitsa.gifcurrency.util.ExchangeRatesComparator;
import com.piatnitsa.gifcurrency.validator.RequestParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * This class represent the tools for working with {@link Gif} entity.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Service
public class GifCurrencyService {

    @Value("${richTag}") private String richTag;
    @Value("${brokeTag}") private String brokeTag;
    @Value("${sameRatesTag}") private String sameTag;
    private final ExchangeRatesComparator ratesComparator;
    private final ExchangeRateService exchangeRateService;
    private final GifService gifService;

    @Autowired
    public GifCurrencyService(ExchangeRatesComparator ratesComparator,
                              ExchangeRateService exchangeRateService,
                              GifService gifService) {
        this.ratesComparator = ratesComparator;
        this.exchangeRateService = exchangeRateService;
        this.gifService = gifService;
    }

    /**
     * Compares yesterday's and current exchange rates and returns the {@link Gif} entity with a special tag.
     * If yesterday's rate is less than the current one, it returns {@link Gif} with the "rich" tag,
     * otherwise with the "broke" tag. If rates are equal that returns with the "same" tag.
     * @param currencyCode currency code that rates are being compared.
     * @return the {@link Gif} entity with a special tag.
     */
    public Gif gifByRate(String currencyCode) {
        RequestParameterValidator.validateCurrencyCode(currencyCode);
        ExchangeRate currentRate = exchangeRateService.getLatestExchangeRate(currencyCode);
        ExchangeRate yesterdayRate = exchangeRateService.getYesterdayRate(currencyCode);
        int comparingValue = ratesComparator.compare(currentRate, yesterdayRate);
        Gif gif;
        switch (comparingValue) {
            case 1: {
                gif = gifService.getGifByTag(richTag);
                break;
            }
            case -1: {
                gif = gifService.getGifByTag(brokeTag);
                break;
            }
            default: {
                gif = gifService.getGifByTag(sameTag);
            }
        }
        return gif;
    }
}
