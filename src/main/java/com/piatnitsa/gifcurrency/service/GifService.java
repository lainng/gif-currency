package com.piatnitsa.gifcurrency.service;

import com.piatnitsa.gifcurrency.dto.GifDto;
import com.piatnitsa.gifcurrency.dto.converter.DtoConverter;
import com.piatnitsa.gifcurrency.model.Gif;
import com.piatnitsa.gifcurrency.service.feign.GifClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * This class represent the tools for working with {@link Gif} entity.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
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

    /**
     * Fetches the {@link Gif} entity by specified tag name.
     * @param tag the GIF image tag name.
     * @return the {@link Gif} entity.
     */
    public Gif getGifByTag(String tag) {
        GifDto dto = gifClient.getRandomGif(appId, tag);
        Gif gif = gifDtoConverter.toEntity(dto);
        gif.setTag(tag);
        return gif;
    }
}
