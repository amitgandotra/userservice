package com.ibps.openapi.config;


import com.ibps.openapi.util.LogTagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringMvc extends WebMvcConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger(SpringMvc.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogTagger());
    }

}
