package com.sussu.ms_um.application.config.circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.net.ConnectException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Configuration
public class CircuitBreakerGlobalConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        // Create a custom configuration for a CircuitBreaker
        var circuitBreakerConfig = CircuitBreakerConfig.custom()
                .minimumNumberOfCalls(15)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(15)
                .failureRateThreshold(25)
                .slowCallRateThreshold(25)
                .slowCallDurationThreshold(Duration.ofSeconds(1))
                .permittedNumberOfCallsInHalfOpenState(5)
                .waitDurationInOpenState(Duration.ofMillis(30000))
//            .recordException(e -> INTERNAL_SERVER_ERROR
//                    .equals(getResponse().getStatus()))
                .recordExceptions(IOException.class, TimeoutException.class, ConnectException.class, ResourceAccessException.class)
                .build();
        return CircuitBreakerRegistry.of(
                Map.of("circuitBreakerConfig", circuitBreakerConfig)
        );
    }
}
