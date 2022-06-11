package com.piatnitsa.gifcurrency.service;

import com.piatnitsa.gifcurrency.dto.GifDto;
import com.piatnitsa.gifcurrency.model.Gif;
import com.piatnitsa.gifcurrency.service.feign.GifClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GifServiceTest {

    @MockBean GifClient gifClient;
    @Autowired GifService gifService;

    private static final String RICH_TAG = "rich";
    private static final String RICH_TAG_GIF_URL = "rich tag GIF url";

    private static final String BROKE_TAG = "broke";
    private static final String BROKE_TAG_GIF_URL = "broke tag GIF url";

    private static final String SAME_TAG = "same";
    private static final String SAME_TAG_GIF_URL = "same tag GIF url";

    private final GifDto GIF_DTO_WITH_RICH_TAG = new GifDto(Collections.singletonMap("url", RICH_TAG_GIF_URL));
    private final GifDto GIF_DTO_WITH_BROKE_TAG = new GifDto(Collections.singletonMap("url", BROKE_TAG_GIF_URL));
    private final GifDto GIF_DTO_WITH_SAME_TAG = new GifDto(Collections.singletonMap("url", SAME_TAG_GIF_URL));

    @Test
    void getGifByRichTag_thatOk() {
        Mockito.when(gifClient.getRandomGif(Mockito.anyString(), Mockito.eq(RICH_TAG))).thenReturn(GIF_DTO_WITH_RICH_TAG);
        Gif expected = new Gif(RICH_TAG_GIF_URL, RICH_TAG);
        Gif actual = gifService.getGifByTag(RICH_TAG);
        assertEquals(expected, actual);
    }

    @Test
    void getGifByBrokeTag_thatOk() {
        Mockito.when(gifClient.getRandomGif(Mockito.anyString(), Mockito.eq(BROKE_TAG))).thenReturn(GIF_DTO_WITH_BROKE_TAG);
        Gif expected = new Gif(BROKE_TAG_GIF_URL, BROKE_TAG);
        Gif actual = gifService.getGifByTag(BROKE_TAG);
        assertEquals(expected, actual);
    }

    @Test
    void getGifBySameTag_thatOk() {
        Mockito.when(gifClient.getRandomGif(Mockito.anyString(), Mockito.eq(SAME_TAG))).thenReturn(GIF_DTO_WITH_SAME_TAG);
        Gif expected = new Gif(SAME_TAG_GIF_URL, SAME_TAG);
        Gif actual = gifService.getGifByTag(SAME_TAG);
        assertEquals(expected, actual);
    }
}