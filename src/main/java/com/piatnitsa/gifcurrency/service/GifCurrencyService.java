package com.piatnitsa.gifcurrency.service;

import com.piatnitsa.gifcurrency.model.ExchangeRate;
import com.piatnitsa.gifcurrency.model.Gif;
import com.piatnitsa.gifcurrency.util.ExchangeRatesComparator;
import com.piatnitsa.gifcurrency.validator.RequestParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
