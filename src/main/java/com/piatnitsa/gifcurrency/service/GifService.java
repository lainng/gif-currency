package com.piatnitsa.gifcurrency.service;

import com.piatnitsa.gifcurrency.dto.GifDto;
import com.piatnitsa.gifcurrency.dto.converter.DtoConverter;
import com.piatnitsa.gifcurrency.feign.GifClient;
import com.piatnitsa.gifcurrency.model.Gif;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifService {

    @Value("${gif.appId}")
    private String appId;
    private final DtoConverter<GifDto, Gif> gifDtoConverter;
    private final GifClient gifClient;

    public GifService(DtoConverter<GifDto, Gif> gifDtoConverter,
                      GifClient gifClient) {
        this.gifDtoConverter = gifDtoConverter;
        this.gifClient = gifClient;
    }

    public Gif getGifByTag(String tag) {
        GifDto dto = gifClient.getRandomGif(appId, tag);
        Gif gif = gifDtoConverter.toEntity(dto);
        gif.setTag(tag);
        return gif;
    }
}
