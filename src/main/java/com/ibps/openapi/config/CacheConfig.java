package com.ibps.openapi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.Date;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("serviceCategoryParamCache")));

        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {"serviceCategoryParamCache"})
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void reportCacheEvict() {
        log.info("Flush serviceCategoryParamCache " + new Date());
    }
}