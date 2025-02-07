package com.sussu.ms_dois.application.config;

import com.sussu.ms_dois.application.portin.ClienteService;
import com.sussu.ms_dois.application.service.ClientesServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SchedulerExecutorDelay {

    private ClienteService clienteService = new ClientesServiceImpl();

    @PostConstruct
    private void executeDelay() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(clienteService::listarClientes, 2L, TimeUnit.SECONDS);

    }

}
