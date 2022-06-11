package com.piatnitsa.gifcurrency.service;

import com.piatnitsa.gifcurrency.exception.IncorrectParameterException;
import com.piatnitsa.gifcurrency.model.ExchangeRate;
import com.piatnitsa.gifcurrency.model.Gif;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class GifCurrencyServiceTest {

    @Autowired GifCurrencyService gifCurrencyService;
    @MockBean ExchangeRateService exchangeRateService;
    @MockBean GifService gifService;

    private static final String RICH_TAG = "rich";
    private static final String RICH_TAG_GIF_URL = "rich tag GIF url";

    private static final String BROKE_TAG = "broke";
    private static final String BROKE_TAG_GIF_URL = "broke tag GIF url";

    private static final String SAME_TAG = "same";
    private static final String SAME_TAG_GIF_URL = "same tag GIF url";

    private static final String CORRECT_CURRENCY_CODE = "BYN";
    private static final String INCORRECT_CURRENCY_CODE = "BYNN";
    private static final Double BYN_RATE_CURRENT = 2.6034;
    private static final Double BYN_RATE_GREATER_CURRENT = 2.6134;
    private static final Double BYN_RATE_LESS_CURRENT = 2.5934;

    private final ExchangeRate CURRENT_EXCHANGE_RATE = new ExchangeRate(BYN_RATE_CURRENT);
    private final ExchangeRate EXCHANGE_RATE_GREATER_CURRENT = new ExchangeRate(BYN_RATE_GREATER_CURRENT);
    private final ExchangeRate EXCHANGE_RATE_LESS_CURRENT = new ExchangeRate(BYN_RATE_LESS_CURRENT);

    @Test
    void gifByRate_rateGreaterCurrent_thatOk() {
        Gif expected = new Gif(RICH_TAG_GIF_URL, RICH_TAG);
        Mockito.when(exchangeRateService.getLatestExchangeRate(CORRECT_CURRENCY_CODE)).thenReturn(EXCHANGE_RATE_GREATER_CURRENT);
        Mockito.when(exchangeRateService.getYesterdayRate(CORRECT_CURRENCY_CODE)).thenReturn(CURRENT_EXCHANGE_RATE);
        Mockito.when(gifService.getGifByTag(RICH_TAG)).thenReturn(expected);
        Gif actual = gifCurrencyService.gifByRate(CORRECT_CURRENCY_CODE);
        Assertions.assertEquals(expected ,actual);
    }

    @Test
    void gifByRate_rateLessCurrent_thatOk() {
        Gif expected = new Gif(BROKE_TAG_GIF_URL, BROKE_TAG);
        Mockito.when(exchangeRateService.getLatestExchangeRate(CORRECT_CURRENCY_CODE)).thenReturn(EXCHANGE_RATE_LESS_CURRENT);
        Mockito.when(exchangeRateService.getYesterdayRate(CORRECT_CURRENCY_CODE)).thenReturn(CURRENT_EXCHANGE_RATE);
        Mockito.when(gifService.getGifByTag(BROKE_TAG)).thenReturn(expected);
        Gif actual = gifCurrencyService.gifByRate(CORRECT_CURRENCY_CODE);
        Assertions.assertEquals(expected ,actual);
    }

    @Test
    void gifByRate_rateSameCurrent_thatOk() {
        Gif expected = new Gif(SAME_TAG_GIF_URL, SAME_TAG);
        Mockito.when(exchangeRateService.getLatestExchangeRate(CORRECT_CURRENCY_CODE)).thenReturn(CURRENT_EXCHANGE_RATE);
        Mockito.when(exchangeRateService.getYesterdayRate(CORRECT_CURRENCY_CODE)).thenReturn(CURRENT_EXCHANGE_RATE);
        Mockito.when(gifService.getGifByTag(SAME_TAG)).thenReturn(expected);
        Gif actual = gifCurrencyService.gifByRate(CORRECT_CURRENCY_CODE);
        Assertions.assertEquals(expected ,actual);
    }

    @Test
    void gifByRate_incorrectCurrencyCode_thatThrowEx() {
        Assertions.assertThrows(IncorrectParameterException.class,
                () -> gifCurrencyService.gifByRate(INCORRECT_CURRENCY_CODE));
    }
}