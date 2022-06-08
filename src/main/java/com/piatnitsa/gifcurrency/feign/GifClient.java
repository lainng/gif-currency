package com.piatnitsa.gifcurrency.feign;

import com.piatnitsa.gifcurrency.dto.GifDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gif-client", url = "https://api.giphy.com/v1/gifs")
public interface GifClient {

    @GetMapping("/random")
    GifDto getRandomGif(@RequestParam("api_key") String appId,
                        @RequestParam("tag") String tag);
}
