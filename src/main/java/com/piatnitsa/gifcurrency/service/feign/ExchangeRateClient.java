package com.piatnitsa.gifcurrency.service.feign;

import com.piatnitsa.gifcurrency.dto.ExchangeRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This interface is a Feign client that provides http endpoints to connect to the exchange rate service.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@FeignClient(name = "exchange-rate-client", url = "https://openexchangerates.org/api")
public interface ExchangeRateClient {

    /**
     * Fetches the latest exchange rate of the specified currency code.
     * @param appId application ID of the exchange rate service.
     * @param currencyCode currency code of the exchange rate.
     * @return the latest exchange rate.
     */
    @GetMapping("/latest.json")
    ExchangeRateDto getLatestExchangeRate(@RequestParam("app_id") String appId,
                                           @RequestParam("symbols") String currencyCode);

    /**
     * Fetches the exchange rate of the specified currency code on the specified date.
     * @param date the date.
     * @param appId application ID of the exchange rate service.
     * @param currencyCode currency code of the exchange rate.
     * @return the exchange rate.
     */
    @GetMapping("/historical/{date}.json")
    ExchangeRateDto getExchangeRateByDate(@PathVariable String date,
                                       @RequestParam("app_id") String appId,
                                       @RequestParam("symbols") String currencyCode);
}
