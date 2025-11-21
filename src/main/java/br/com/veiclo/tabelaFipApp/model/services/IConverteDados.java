package br.com.veiclo.tabelaFipApp.model.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IConverteDados {

    <T> T converteDados(String json, Class<T> classe);
    <T> List<T> obterLista(String json, Class<T> classe);

}
