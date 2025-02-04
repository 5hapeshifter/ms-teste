package com.sussu.ms_um.application.config.circuitbreaker;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.stereotype.Component;

@Component
public class CircuitBreakerStateChecker {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public CircuitBreakerStateChecker(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    public String getCircuitBreakerState(String circuitBreakerName) {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker(circuitBreakerName);
        return circuitBreaker.getState().toString();
    }
}
