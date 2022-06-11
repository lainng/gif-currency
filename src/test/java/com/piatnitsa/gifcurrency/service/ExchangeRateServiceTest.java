package com.piatnitsa.gifcurrency.service;

import com.piatnitsa.gifcurrency.dto.ExchangeRateDto;
import com.piatnitsa.gifcurrency.exception.IncorrectParameterException;
import com.piatnitsa.gifcurrency.model.ExchangeRate;
import com.piatnitsa.gifcurrency.service.feign.ExchangeRateClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@SpringBootTest
class ExchangeRateServiceTest {

    @MockBean ExchangeRateClient exchangeRateClient;
    @Autowired ExchangeRateService exchangeRateService;

    private static final String CORRECT_CURRENCY_CODE = "BYN";
    private static final String INCORRECT_CURRENCY_CODE = "BYNN";
    private static final Double BYN_RATE = 2.6034;
    private static final String YESTERDAY = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private static final String CORRECT_FORMAT_DATE = "2022-06-08";
    private static final String INCORRECT_FORMAT_DATE = "08.06.2022";

    private final ExchangeRateDto CORRECT_BYN_RATE_DTO = new ExchangeRateDto(
            Collections.singletonMap(CORRECT_CURRENCY_CODE, BYN_RATE));
    private final ExchangeRateDto EMPTY_BYN_RATE_DTO = new ExchangeRateDto(
            Collections.emptyMap());

    @Test
    void getLatestExchangeRate_thatOk() {
        Mockito.when(exchangeRateClient.getLatestExchangeRate(Mockito.anyString(), Mockito.eq(CORRECT_CURRENCY_CODE)))
                .thenReturn(CORRECT_BYN_RATE_DTO);
        ExchangeRate expected = new ExchangeRate(BYN_RATE);
        ExchangeRate actual = exchangeRateService.getLatestExchangeRate(CORRECT_CURRENCY_CODE);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getLatestExchangeRateByIncorrectCode_thatThrow_Ex() {
        Mockito.when(exchangeRateClient.getLatestExchangeRate(Mockito.anyString(), Mockito.eq(INCORRECT_CURRENCY_CODE)))
                .thenReturn(EMPTY_BYN_RATE_DTO);
        Assertions.assertThrows(IncorrectParameterException.class,
                () -> exchangeRateService.getLatestExchangeRate(INCORRECT_CURRENCY_CODE));
    }

    @Test
    void getExchangeRateByDate_thatOk() {
        Mockito.when(exchangeRateClient.getExchangeRateByDate(
                Mockito.eq(CORRECT_FORMAT_DATE), Mockito.any(), Mockito.eq(CORRECT_CURRENCY_CODE)))
                .thenReturn(CORRECT_BYN_RATE_DTO);
        ExchangeRate expected = new ExchangeRate(BYN_RATE);
        ExchangeRate actual = exchangeRateService.getExchangeRateByDate(CORRECT_FORMAT_DATE, CORRECT_CURRENCY_CODE);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getExchangeRateByMalformedDate_thatThrowEx() {
        Assertions.assertThrows(IncorrectParameterException.class,
                () -> exchangeRateService.getExchangeRateByDate(INCORRECT_FORMAT_DATE, CORRECT_CURRENCY_CODE));
    }

    @Test
    void getYesterdayRate_thatOk() {
        Mockito.when(exchangeRateClient.getExchangeRateByDate(
                        Mockito.eq(YESTERDAY), Mockito.any(), Mockito.eq(CORRECT_CURRENCY_CODE)))
                .thenReturn(CORRECT_BYN_RATE_DTO);
        ExchangeRate expected = new ExchangeRate(BYN_RATE);
        ExchangeRate actual = exchangeRateService.getYesterdayRate(CORRECT_CURRENCY_CODE);
        Assertions.assertEquals(expected, actual);
    }
}