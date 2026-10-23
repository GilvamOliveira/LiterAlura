package com.example.SpringAlura.principal;

import com.example.SpringAlura.service.LivroService;

import java.util.Scanner;

public class Principal {

    private final LivroService livroService;

    public Principal(LivroService livroService) {
        this.livroService = livroService;
    }

    public void exibeMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores");
            System.out.println("4 - Listar autores vivos em determinado ano");
            System.out.println("5 - Listar livros por idioma");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Opção inválida!");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1:
                    livroService.buscarLivro();
                    break;
                case 2:
                    livroService.listarLivros();
                    break;
                case 3:
                    livroService.listarAutores();
                    break;
                case 4:
                    System.out.print("Digite o ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
                    livroService.autoresVivosNoAno(ano);
                    break;
                case 5:
                    System.out.print("Digite o idioma: ");
                    String idioma = scanner.nextLine();
                    livroService.livrosPorIdioma(idioma);
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
