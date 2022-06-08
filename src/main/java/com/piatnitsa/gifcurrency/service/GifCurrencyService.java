package com.piatnitsa.gifcurrency.service;

import com.piatnitsa.gifcurrency.feign.ExchangeClient;
import com.piatnitsa.gifcurrency.feign.GifClient;
import com.piatnitsa.gifcurrency.model.ExchangeRate;
import com.piatnitsa.gifcurrency.model.Gif;
import com.piatnitsa.gifcurrency.dto.GifDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GifCurrencyService {
    private final ExchangeClient exchangeClient;
    private final GifClient gifClient;

    @Autowired
    public GifCurrencyService(ExchangeClient exchangeClient, GifClient gifClient) {
        this.exchangeClient = exchangeClient;
        this.gifClient = gifClient;
    }

    public ExchangeRate getExchangeRates(String appId, String currencyCode) {
        return exchangeClient.getLatestExchangeRates(appId, currencyCode).getBody();
    }

    public Gif getGif(String appId, String tag) {
        GifDto gifDto = gifClient.getRandomGif(appId, tag);
        Gif gif = toEntity(gifDto);
        gif.setTag(tag);
        return gif;
    }

    private Gif toEntity(GifDto dto) {
        Gif gif = new Gif();
        Map<String, Object> data = dto.getGifData();
        gif.setUrl((String) data.get("url"));
        return gif;
    }
}
