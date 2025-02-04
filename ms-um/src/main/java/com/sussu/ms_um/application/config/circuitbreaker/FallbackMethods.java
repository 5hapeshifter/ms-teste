package com.sussu.ms_um.application.config.circuitbreaker;

import org.springframework.stereotype.Component;

@Component
public class FallbackMethods {

    public void fallbackBuscarClientes(Throwable t) {
        // Retorna uma resposta alternativa quando a chamada falha
        throw new RuntimeException("Circuito ABERTO em virtude de erros no Ms-Dois");
    }

}
