package com.piatnitsa.gifcurrency.controller;

import com.piatnitsa.gifcurrency.model.ExchangeRate;
import com.piatnitsa.gifcurrency.model.Gif;
import com.piatnitsa.gifcurrency.service.GifCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final GifCurrencyService service;

    @Autowired
    public MainController(GifCurrencyService service) {
        this.service = service;
    }

    @GetMapping("/exchange-rate")
    public ExchangeRate exchangeRate(
            @RequestParam(value = "currency-code", required = false, defaultValue = "BYN") String currencyCode,
            @Value("${exchangeRate.appId}") String appId) {
        return service.getExchangeRates(appId, currencyCode);
    }

    @GetMapping("/gif")
    public Gif gif(@Value("${gif.appId}") String appId,
                   @RequestParam("tag") String tag) {
        return service.getGif(appId, tag);
    }
}
