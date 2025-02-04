package com.sussu.ms_um.application.config.retry;

import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.net.ConnectException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Configuration
public class RetryGlobalConfig {

    @Bean
    public RetryRegistry createRetryRegistry() {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(5)
                .waitDuration(Duration.ofMillis(1000))
                //.retryOnException(e -> e instanceof WebServiceEx)
                .retryExceptions(IOException.class, TimeoutException.class, ConnectException.class, ResourceAccessException.class)
                //.ignoreExceptions(BusinessException.class, OtherBusinessException.class)
                .failAfterMaxAttempts(true)
                .build();

        Map<String, RetryConfig> configs = Map.of("retryConfig", retryConfig);
        return RetryRegistry.of(configs);
    }
}
