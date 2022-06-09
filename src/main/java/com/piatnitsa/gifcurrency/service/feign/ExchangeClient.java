package com.piatnitsa.gifcurrency.service.feign;

import com.piatnitsa.gifcurrency.dto.ExchangeRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "exchange-rate-client", url = "https://openexchangerates.org/api")
public interface ExchangeClient {

    @GetMapping("/latest.json")
    ExchangeRateDto getLatestExchangeRate(@RequestParam("app_id") String appId,
                                           @RequestParam("symbols") String currencyCode);

    @GetMapping("/historical/{date}.json")
    ExchangeRateDto getExchangeRateByDate(@PathVariable String date,
                                       @RequestParam("app_id") String appId,
                                       @RequestParam("symbols") String currencyCode);
}
