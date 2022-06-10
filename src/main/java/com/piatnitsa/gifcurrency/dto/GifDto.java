package com.piatnitsa.gifcurrency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.piatnitsa.gifcurrency.model.Gif;
import com.piatnitsa.gifcurrency.service.feign.GifClient;

import java.util.Map;

/**
 * This class represents the DTO of the {@link Gif} that contains response information
 * from {@link GifClient}.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public class GifDto {
    @JsonProperty("data")
    private Map<String, Object> gifData;

    public Map<String, Object> getGifData() {
        return gifData;
    }

    public void setGifData(Map<String, Object> gifData) {
        this.gifData = gifData;
    }
}
