package com.piatnitsa.gifcurrency.controller;

import com.piatnitsa.gifcurrency.model.Gif;
import com.piatnitsa.gifcurrency.service.GifCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/exchange-rate-gif")
    public Gif gifByExchangeRate(@RequestParam(value = "currency-code", required = false) String currencyCode) {
        return service.gifByRate(currencyCode);
    }
}
