package com.example.SpringAlura.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Component
public class ConsumoApi {

    private static final String BASE_URL = "https://gutendex.com/books/?search=";

    private final HttpClient client = HttpClient.newHttpClient();

    public String obterDados(String titulo) {
        String url = BASE_URL + URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .GET()
                .timeout(Duration.ofSeconds(20))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro ao consultar a API Gutendex: HTTP " + response.statusCode());
            }
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao consultar a API Gutendex", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Consulta à API Gutendex interrompida", e);
        }
    }
}
