package com.example.SpringAlura.principal;

import java.util.Scanner;

public class Principal {

    Scanner leitura = new Scanner(System.in);

    public void exibeMenu(){

        int opcao = -1;

        while(opcao != 0){

            System.out.println("""
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros por idioma
                    
                    0 - Sair
                    """);

            opcao = leitura.nextInt();
        }
    }
}