package br.com.veiclo.tabelaFipApp.model.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AnoVeiculo(@JsonAlias("codigo") String codigo,
                         @JsonAlias("nome") String nome) {
}
