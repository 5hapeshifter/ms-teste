package com.sussu.ms_dois.application.service;

import com.sussu.ms_dois.application.portin.ClienteService;
import com.sussu.ms_dois.framework.rest.dtos.ClienteDto;
import com.sussu.ms_dois.framework.rest.dtos.EnderecoDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ClientesServiceImpl implements ClienteService {

    @Override
    public List<ClienteDto> listarClientes() {
        var endCli1 = new EnderecoDto("Rua 1", "123", "Bairro 1", "Cidade 1", "Estado 1", "CEP 1");
        var endCli12 = new EnderecoDto("Rua 2", "456", "Bairro 2", "Cidade 2", "Estado 2", "CEP 2");
        var endCli2 = new EnderecoDto("Rua 3", "789", "Bairro 3", "Cidade 3", "Estado 3", "CEP 3");
        var endCli22 = new EnderecoDto("Rua 4", "1011", "Bairro 4", "Cidade 4", "Estado 4", "CEP 4");
        Map<String, EnderecoDto> enderecosCli1 = Map.of("Casa", endCli1, "Trabalho", endCli12);
        Map<String, EnderecoDto> enderecosCli2 = Map.of("Casa", endCli2, "Trabalho", endCli22);

        var cliente1 = new ClienteDto(UUID.randomUUID(), "Cliente 1", enderecosCli1, Arrays.asList("123456789", "987654321"));
        var cliente2 = new ClienteDto(UUID.randomUUID(), "Cliente 2", enderecosCli2, Arrays.asList("123456789", "987654321"));

        return Arrays.asList(cliente1, cliente2);
    }

    @Override
    public ClienteDto criarCliente(ClienteDto clienteDto) {
        var endCli1 = new EnderecoDto("Rua 1", "123", "Bairro 1", "Cidade 1", "Estado 1", "CEP 1");
        var endCli12 = new EnderecoDto("Rua 2", "456", "Bairro 2", "Cidade 2", "Estado 2", "CEP 2");
        Map<String, EnderecoDto> enderecosCli1 = Map.of("Casa", endCli1, "Trabalho", endCli12);
        var cliente1 = new ClienteDto(UUID.randomUUID(), "Cliente 1", enderecosCli1, Arrays.asList("123456789", "987654321"));
        return cliente1;
    }
}
