package com.sussu.ms_um.framework.rest.in;

import com.sussu.ms_um.application.portin.ClientesService;
import com.sussu.ms_um.framework.dtos.ClienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    private final ClientesService clientesService;

    private final Logger logger = Logger.getLogger("ClientesController logs: ");


    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        var result = clientesService.listarClientes();
        logger.info("ClientesController logs: " + result);
        return ResponseEntity.ok(result);
    }
}
