package com.sussu.ms_um.application.portin;

import com.sussu.ms_um.framework.dtos.ClienteDto;

import java.util.List;

public interface ClientesService {

    List<ClienteDto> listarClientes();

    ClienteDto criarCliente(ClienteDto clienteDto);
}
