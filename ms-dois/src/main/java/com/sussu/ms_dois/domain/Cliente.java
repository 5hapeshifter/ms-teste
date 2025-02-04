package com.sussu.ms_dois.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private UUID id;
    private String nome;
    private Map<String, Endereco> endereco;
    private List<String> telefones;

}
