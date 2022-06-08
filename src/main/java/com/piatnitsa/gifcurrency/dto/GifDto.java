package com.piatnitsa.gifcurrency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

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
