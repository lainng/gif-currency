package com.piatnitsa.gifcurrency.controller;

import com.piatnitsa.gifcurrency.model.Gif;
import com.piatnitsa.gifcurrency.service.GifCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is an API endpoint that allows you to get a GIF image depending on the exchange rate of the selected currency.
 * Annotated by {@link RestController} with no parameters to provide an answer in application/json.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@RestController
public class MainController {
    private final GifCurrencyService service;

    @Autowired
    public MainController(GifCurrencyService service) {
        this.service = service;
    }

    /**
     * Returns {@link Gif} entity that contains URL for GIF image depending on the exchange rate
     * of the specified <code>currencyCode</code> parameter.
     * @param currencyCode the currency code of the exchange rates being compared.
     * @return {@link Gif} entity that contains URL for GIF imageю
     */
    @GetMapping("/exchange-rate-gif")
    public Gif gifByExchangeRate(@RequestParam(value = "currency-code", required = false) String currencyCode) {
        return service.gifByRate(currencyCode);
    }
}
