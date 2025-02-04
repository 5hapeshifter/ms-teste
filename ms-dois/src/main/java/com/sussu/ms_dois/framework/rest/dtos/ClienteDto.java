package com.sussu.ms_dois.framework.rest.dtos;

import com.sussu.ms_dois.domain.Endereco;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record ClienteDto (UUID id, String nome, Map<String, EnderecoDto> enderecoDto, List<String> telefones) {


}
