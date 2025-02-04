package com.sussu.ms_um.application.service;

import com.sussu.ms_um.application.portin.ClientesService;
import com.sussu.ms_um.application.portout.MsClient;
import com.sussu.ms_um.framework.dtos.ClienteDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClientesService {

    private final MsClient<ClienteDto> msClient;

    public ClienteServiceImpl(MsClient<ClienteDto> msClient) {
        this.msClient = msClient;
    }

    @Override
    public List<ClienteDto> listarClientes() {
        return msClient.buscarClientes().getBody();
    }

    @Override
    public ClienteDto criarCliente(ClienteDto clienteDto) {
        return msClient.criarCliente(clienteDto).getBody();
    }
}
