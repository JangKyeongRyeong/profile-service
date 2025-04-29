package com.test.profile_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // RestTemplate : RESTful 웹 서비스를 호출하는 데 사용되는 템플릿 클래스
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
