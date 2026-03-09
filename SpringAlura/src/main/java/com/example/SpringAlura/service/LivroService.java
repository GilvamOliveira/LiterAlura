package com.example.SpringAlura.service;

import com.example.SpringAlura.dto.DadosAutor;
import com.example.SpringAlura.dto.DadosLivro;
import com.example.SpringAlura.dto.DadosResultado;
import com.example.SpringAlura.model.Autor;
import com.example.SpringAlura.model.Livro;
import com.example.SpringAlura.repository.AutorRepository;
import com.example.SpringAlura.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class LivroService {

    private final ConsumoApi consumoApi;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroService(ConsumoApi consumoApi,
                        LivroRepository livroRepository,
                        AutorRepository autorRepository) {
        this.consumoApi = consumoApi;
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void buscarLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do livro:");
        String titulo = scanner.nextLine();

        String json = consumoApi.obterDados(titulo);
        DadosResultado resultado = new DadosResultado(); // ajuste com seu ConverteDados

        if (resultado.getResults() == null || resultado.getResults().isEmpty()) {
            System.out.println("Livro não encontrado!");
            return;
        }

        DadosLivro dadosLivro = resultado.getResults().get(0);
        DadosAutor dadosAutor = dadosLivro.getAuthors().get(0);

        Autor autor = new Autor();
        autor.setNome(dadosAutor.getName());
        autor.setAnoNascimento(dadosAutor.getBirth_year());
        autor.setAnoFalecimento(dadosAutor.getDeath_year());
        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setTitulo(dadosLivro.getTitle());
        livro.setIdioma(dadosLivro.getLanguages().get(0));
        livro.setNumeroDownloads(dadosLivro.getDownload_count());
        livro.setAutor(autor);
        livroRepository.save(livro);

        System.out.println("Livro salvo com sucesso!");
    }

    public void listarLivros() {
        livroRepository.findAll().forEach(l ->
                System.out.println(
                        "Livro: " + l.getTitulo() +
                                " | Autor: " + l.getAutor().getNome() +
                                " | Idioma: " + l.getIdioma()
                )
        );
    }

    public void listarAutores() {
        autorRepository.findAll().forEach(a ->
                System.out.println(
                        "Autor: " + a.getNome() +
                                " (" + a.getAnoNascimento() +
                                " - " + a.getAnoFalecimento() + ")"
                )
        );
    }
}