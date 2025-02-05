package com.sussu.ms_um.application.config.circuitbreaker;

import com.sussu.ms_um.framework.dtos.ClienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FallbackMethods {

    public ResponseEntity<List<ClienteDto>> fallbackBuscarClientes(Throwable t) {
        System.out.println(String.format("Exception original: cause - %s, message - %s, stack trace - " + t.getCause(),t.getMessage(), Arrays.toString(t.getStackTrace())));
        // Retorna uma resposta alternativa quando a chamada falha
        throw new RuntimeException("Circuito ABERTO em virtude de erros no Ms-Dois");
    }

}
