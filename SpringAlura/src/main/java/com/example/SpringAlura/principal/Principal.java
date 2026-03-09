package com.example.SpringAlura.principal;

import com.example.SpringAlura.service.ConsumoAPI;
import com.example.SpringAlura.service.ConverteDados;
import com.example.SpringAlura.repository.LivroRepository;
import com.example.SpringAlura.repository.AutorRepository;

import java.util.Scanner;

public class Principal {

    private final ConsumoAPI consumoAPI;
    private final ConverteDados converteDados;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public Principal(ConsumoAPI consumoAPI, ConverteDados converteDados,
                     LivroRepository livroRepository, AutorRepository autorRepository) {
        this.consumoAPI = consumoAPI;
        this.converteDados = converteDados;
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while(opcao != 0) {
            System.out.println("\n1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores");
            System.out.println("4 - Listar autores vivos em determinado ano");
            System.out.println("5 - Listar livros por idioma");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            if(scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // limpa o buffer
            } else {
                System.out.println("Opção inválida!");
                scanner.nextLine();
                continue;
            }

            switch(opcao) {
                case 1:
                    System.out.print("Digite o nome do livro: ");
                    String titulo = scanner.nextLine();
                    consumoAPI.buscarLivroPorTitulo(titulo);
                    break;
                case 2:
                    consumoAPI.listarLivros();
                    break;
                case 3:
                    consumoAPI.listarAutores();
                    break;
                case 4:
                    System.out.print("Digite o ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
                    consumoAPI.autoresVivosNoAno(ano);
                    break;
                case 5:
                    System.out.print("Digite o idioma: ");
                    String idioma = scanner.nextLine();
                    consumoAPI.livrosPorIdioma(idioma);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}