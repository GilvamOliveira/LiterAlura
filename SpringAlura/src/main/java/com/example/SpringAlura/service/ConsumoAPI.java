package com.example.SpringAlura.service;

import com.example.SpringAlura.dto.ApiRespostaDTO;
import com.example.SpringAlura.dto.LivroDTO;
import com.example.SpringAlura.model.Autor;
import com.example.SpringAlura.model.Livro;
import com.example.SpringAlura.repository.AutorRepository;
import com.example.SpringAlura.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConsumoAPI {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private ObjectMapper mapper = new ObjectMapper();

    public void buscarLivroPorTituloESalvar(String titulo) throws Exception {
        String url = "https://gutendex.com/books?search=" + titulo.replace(" ", "%20");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ApiRespostaDTO resposta = mapper.readValue(response.body(), ApiRespostaDTO.class);

        if (resposta.getResults() != null && !resposta.getResults().isEmpty()) {
            LivroDTO livroDTO = resposta.getResults().get(0);

            if (livroDTO.getAutores() != null && !livroDTO.getAutores().isEmpty()) {
                var autorDTO = livroDTO.getAutores().get(0);

                Autor autor = autorRepository.findByNome(autorDTO.getNome())
                        .orElse(new Autor());

                autor.setNome(autorDTO.getNome());
                autor.setAnoNascimento(autorDTO.getAnoNascimento());
                autor.setAnoFalecimento(autorDTO.getAnoFalecimento());

                autorRepository.save(autor);

                Livro livro = new Livro();
                livro.setTitulo(livroDTO.getTitulo());

                if (livroDTO.getIdiomas() != null && !livroDTO.getIdiomas().isEmpty()) {
                    livro.setIdioma(livroDTO.getIdiomas().get(0));
                }

                livro.setDownloads(livroDTO.getDownloads());
                livro.setAutor(autor);

                livroRepository.save(livro);
            }
        }
    }
}