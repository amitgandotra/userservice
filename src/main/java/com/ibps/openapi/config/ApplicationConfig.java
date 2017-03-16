package com.ibps.openapi.config;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.Filter;

@SpringBootConfiguration
public class ApplicationConfig {
    private static Log log = LogFactory.getLog(ApplicationConfig.class);
    @Autowired private Environment environment;


    public static HttpClientBuilder defaultHttpClientBuilder() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10 * 1000)
                .setConnectionRequestTimeout(10 * 1000)
                .setSocketTimeout(10 * 1000)
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .build();

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .disableCookieManagement();
    }

    @Bean
    @Profile("!container")
    public HttpClient httpClient() {
        return defaultHttpClientBuilder().build();
    }

    @Bean
    public RestTemplate restTemplate(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(factory);
    }

    @Bean
    @Profile({"dev", "stage", "docker", "test"})
    public Filter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(true);
        filter.setMaxPayloadLength(5000);
        return filter;
    }

    private String getCurrentEnvironment() {
        if (environment.acceptsProfiles("prod")) {
            return "prod";
        } else if (environment.getActiveProfiles().length > 0) {
            return environment.getActiveProfiles()[0];
        } else {
            return "unknown";
        }
    }
}