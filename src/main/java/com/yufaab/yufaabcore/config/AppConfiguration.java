package com.yufaab.yufaabcore.config;

import com.yufaab.yufaabcore.logger.ClientsCallInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
@Validated
@Configuration
public class AppConfiguration {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
