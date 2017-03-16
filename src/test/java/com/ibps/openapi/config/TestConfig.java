package com.ibps.openapi.config;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class TestConfig {

    static {
        RestAssured.filters(new MCRestAsuredFilter(),
                new RequestLoggingFilter(),
                new ResponseLoggingFilter());
    }

    @Bean
    public MockRestServiceServer mockRestService(RestTemplate restTemplate) {
        return MockRestServiceServer.createServer(restTemplate);
    }
}
