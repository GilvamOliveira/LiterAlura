package com.example.SpringAlura.service;

import com.example.SpringAlura.dto.DadosAutor;
import com.example.SpringAlura.dto.DadosLivro;
import com.example.SpringAlura.dto.DadosResultado;
import com.example.SpringAlura.model.Autor;
import com.example.SpringAlura.model.Livro;
import com.example.SpringAlura.repository.AutorRepository;
import com.example.SpringAlura.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class LivroService {

    private final ConsumoApi consumoApi;
    private final ConverteDados converteDados;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroService(ConsumoApi consumoApi,
                        ConverteDados converteDados,
                        LivroRepository livroRepository,
                        AutorRepository autorRepository) {
        this.consumoApi = consumoApi;
        this.converteDados = converteDados;
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void buscarLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do livro:");
        String titulo = scanner.nextLine();

        String json = consumoApi.obterDados(titulo);
        DadosResultado resultado = converteDados.obterDados(json, DadosResultado.class);

        if (resultado == null || resultado.getResults() == null || resultado.getResults().isEmpty()) {
            System.out.println("Livro não encontrado!");
            return;
        }

        DadosLivro dadosLivro = resultado.getResults().get(0);
        if (dadosLivro.getAuthors() == null || dadosLivro.getAuthors().isEmpty()) {
            System.out.println("O livro encontrado não possui autor cadastrado na API.");
            return;
        }

        DadosAutor dadosAutor = dadosLivro.getAuthors().get(0);

        Autor autor = new Autor();
        autor.setNome(dadosAutor.getName());
        autor.setAnoNascimento(dadosAutor.getBirth_year());
        autor.setAnoFalecimento(dadosAutor.getDeath_year());
        autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setTitulo(dadosLivro.getTitle());
        livro.setIdioma(primeiroIdioma(dadosLivro));
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

    public void autoresVivosNoAno(int ano) {
        List<Autor> autores = autorRepository.findAll().stream()
                .filter(a -> a.getAnoNascimento() != null && a.getAnoNascimento() <= ano)
                .filter(a -> a.getAnoFalecimento() == null || a.getAnoFalecimento() >= ano)
                .toList();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano " + ano + ".");
            return;
        }

        autores.forEach(a -> System.out.println(
                "Autor: " + a.getNome() +
                        " (" + a.getAnoNascimento() +
                        " - " + a.getAnoFalecimento() + ")"
        ));
    }

    public void livrosPorIdioma(String idioma) {
        List<Livro> livros = livroRepository.findByIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma " + idioma + ".");
            return;
        }

        livros.forEach(l -> System.out.println(
                "Livro: " + l.getTitulo() +
                        " | Autor: " + l.getAutor().getNome()
        ));
    }

    private String primeiroIdioma(DadosLivro livro) {
        if (livro.getLanguages() == null || livro.getLanguages().isEmpty()) {
            return "desconhecido";
        }
        return livro.getLanguages().get(0);
    }
}
