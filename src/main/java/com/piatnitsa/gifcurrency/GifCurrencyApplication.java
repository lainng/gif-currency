package com.piatnitsa.gifcurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GifCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GifCurrencyApplication.class, args);
    }

}
