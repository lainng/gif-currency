package com.piatnitsa.gifcurrency.feign;

import com.piatnitsa.gifcurrency.model.ExchangeRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchange-rate-client", url = "https://openexchangerates.org/api")
public interface ExchangeClient {

    @GetMapping("/latest.json")
    ResponseEntity<ExchangeRate> getLatestExchangeRates(@RequestParam("app_id") String appId,
                                                        @RequestParam("symbols") String currencyCode);
}
