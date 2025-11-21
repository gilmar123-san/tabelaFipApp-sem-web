package br.com.veiclo.tabelaFipApp.model.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    public String obterDados(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(String.format("Erro ao executar a consulta dos dados. Motivo: %s", e.getMessage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }

}
