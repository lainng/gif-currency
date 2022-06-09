package com.piatnitsa.gifcurrency.dto.converter.impl;

import com.piatnitsa.gifcurrency.dto.converter.DtoConverter;
import com.piatnitsa.gifcurrency.dto.GifDto;
import com.piatnitsa.gifcurrency.model.Gif;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GifDtoConverter implements DtoConverter<GifDto, Gif> {

    @Override
    public Gif toEntity(GifDto dto) {
        Gif gif = new Gif();
        Map<String, Object> data = dto.getGifData();
        gif.setUrl((String) data.get("url"));
        return gif;
    }
}
