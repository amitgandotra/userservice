package com.ibps.openapi.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.SSLSocketFactory;

@Configuration
@Profile("container")
public class ContainerConfig {
    private static Log log = LogFactory.getLog(ContainerConfig.class);

//    @Bean
//    public HttpClient httpClient(SSLSocketFactory sslSocketFactory) {
//        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslSocketFactory, new NoopHostnameVerifier());
//
//        return ApplicationConfig.defaultHttpClientBuilder()
//                .setSSLSocketFactory(connectionFactory)
//                .build();
//    }
}
