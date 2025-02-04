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
    public ResponseEntity<List<ClienteDto>> buscarClientes() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("circuitBreakerConfig", "circuitBreakerConfig");
        Retry retry = retryRegistry.retry("retryConfig");
        RestTemplate restTemplate = new RestTemplate();

        Supplier<ResponseEntity<List<ClienteDto>>> supplier = () -> circuitBreaker.executeSupplier(() -> restTemplate.exchange(URL + ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<List<ClienteDto>>() {
        }));

        Supplier<ResponseEntity<List<ClienteDto>>> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, supplier);

        ResponseEntity<List<ClienteDto>> result = null;

        try {
            result =  decoratedSupplier.get();
            logger.info("Resultado da chamada ao MS-DOIS: " + result);
        } catch (Exception e) {
            fallbackMethods.fallbackBuscarClientes(e);
        }
//        var result = restTemplate.exchange(URL + ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<List<ClienteDto>>() {
//        });
//        var result = retry.executeSupplier(() -> restTemplate.exchange(URL + ENDPOINT, HttpMethod.GET, null, new ParameterizedTypeReference<List<ClienteDto>>() {
//        }));
        if (circuitBreakerStateChecker.getCircuitBreakerState("circuitBreakerConfig").contains(CircuitBreaker.State.OPEN.toString())) {
            logger.info("Circuit breaker aberto");
        }

        return result;
    }

    @Override
    public ResponseEntity<ClienteDto> criarCliente(ClienteDto clienteDto) {
        return null;
    }


}
