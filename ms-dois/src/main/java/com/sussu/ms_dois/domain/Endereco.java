package com.sussu.ms_dois.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String numero;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;
}
