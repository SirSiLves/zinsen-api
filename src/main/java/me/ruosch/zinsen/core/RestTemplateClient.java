package me.ruosch.zinsen.core;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateClient {
    @Bean
    public RestTemplate tradesApi(RestTemplateBuilder builder) {
        return builder
                .build();
    }
}
