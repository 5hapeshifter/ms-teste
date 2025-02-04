package com.sussu.ms_um.application.portout;

import com.sussu.ms_um.framework.dtos.ClienteDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MsClient <T> {

    ResponseEntity<List<T>>  buscarClientes();
    ResponseEntity<T> criarCliente(ClienteDto clienteDto);

}
