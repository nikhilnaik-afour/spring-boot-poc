package com.poc.dellnxppoc.memcacheclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.*;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
public class MemcachedConfiguration implements CachingConfigurer {

    @Value("${memcached.addresses}")
    private String memcachedAddresses;

    @Value("${memcached.expiration.sec}")
    private int expirationSec;

    @Override
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        try {
            cacheManager.setCaches(internalCaches());
            return cacheManager;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<Memcached> internalCaches() throws IOException {
        final Collection<Memcached> caches = new ArrayList<>();
        caches.add(new Memcached("personCache", memcachedAddresses, expirationSec));
        caches.add(new Memcached("myCache", memcachedAddresses, expirationSec));
        return caches;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }
}