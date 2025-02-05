package com.sussu.ms_um.framework.rest.out;


import com.sussu.ms_um.application.config.circuitbreaker.CircuitBreakerStateChecker;
import com.sussu.ms_um.application.config.circuitbreaker.FallbackMethods;
import com.sussu.ms_um.application.portout.MsClient;
import com.sussu.ms_um.framework.dtos.ClienteDto;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Component
public class MsDoisClientImpl implements MsClient {

    Logger logger = Logger.getLogger("MsDoisClientImpl logs: ");

    @Value("${ms-dois.url}")
    private String URL;
    private final String ENDPOINT = "/clientes";
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final RetryRegistry retryRegistry;
    private final CircuitBreakerStateChecker circuitBreakerStateChecker;
    private final FallbackMethods fallbackMethods;

    public MsDoisClientImpl(CircuitBreakerRegistry circuitBreakerRegistry, RetryRegistry retryRegistry, CircuitBreakerStateChecker circuitBreakerStateChecker, FallbackMethods fallbackMethods) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.retryRegistry = retryRegistry;
        this.circuitBreakerStateChecker = circuitBreakerStateChecker;
        this.fallbackMethods = fallbackMethods;
    }

    @Override
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "circuitBreakerConfig", fallbackMethod = "fallbackBuscarClientes")
    public ResponseEntity<List<ClienteDto>> buscarClientes() {
        RestTemplate restTemplate = new RestTemplate();
        var result = restTemplate.exchange(URL + ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<List<ClienteDto>>() {});
        logger.info("Resultado da chamada ao MS-DOIS: " + result);
        return result;
    }

    public ResponseEntity<List<ClienteDto>> fallbackBuscarClientes(Throwable t) {
        return fallbackMethods.fallbackBuscarClientes(t);
    }

    @Override
    public ResponseEntity<ClienteDto> criarCliente(ClienteDto clienteDto) {
        return null;
    }
}
