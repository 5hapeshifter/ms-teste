package com.sussu.ms_dois.application.portin;

import com.sussu.ms_dois.framework.rest.dtos.ClienteDto;

import java.util.List;

public interface ClienteService {

    List<ClienteDto> listarClientes();

    ClienteDto criarCliente(ClienteDto clienteDto);
}
