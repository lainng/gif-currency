package com.piatnitsa.gifcurrency.service.feign;

import com.piatnitsa.gifcurrency.dto.GifDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This interface is a Feign client that provides http endpoint to connect to the GIF image service.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@FeignClient(name = "gif-client", url = "https://api.giphy.com/v1/gifs")
public interface GifClient {

    /**
     * Fetches information about GIF image by the specified tag.
     * @param appId application ID of the GIF image service.
     * @param tag the name of the GIF tag.
     * @return the object that contains information about GIF image.
     */
    @GetMapping("/random")
    GifDto getRandomGif(@RequestParam("api_key") String appId,
                        @RequestParam("tag") String tag);
}
