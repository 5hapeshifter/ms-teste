package com.sussu.ms_dois.framework.rest.in;

import com.sussu.ms_dois.application.service.ClientesServiceImpl;
import com.sussu.ms_dois.framework.rest.dtos.ClienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    Logger logger = Logger.getLogger("ClientesController logs: ");
    
    public final ClientesServiceImpl    clientesServiceImpl;

    public ClientesController(ClientesServiceImpl clientesServiceImpl) {
        this.clientesServiceImpl = clientesServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        var result = clientesServiceImpl.listarClientesComDelay();
        logger.info("ClientesController logs: " + result);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/criar")
    public ResponseEntity<ClienteDto> criarCliente(ClienteDto clienteDto) {
        return ResponseEntity.ok(clientesServiceImpl.criarCliente(clienteDto));
    }
}
