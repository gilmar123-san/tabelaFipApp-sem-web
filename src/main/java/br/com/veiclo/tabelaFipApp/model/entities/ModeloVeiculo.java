package br.com.veiclo.tabelaFipApp.model.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModeloVeiculo(@JsonAlias("codigo") Integer codigo,
                            @JsonAlias("nome") String nome) {
}
