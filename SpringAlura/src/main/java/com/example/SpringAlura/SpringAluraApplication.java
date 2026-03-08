package com.example.SpringAlura;

import com.example.SpringAlura.model.Autor;
import com.example.SpringAlura.model.Livro;
import com.example.SpringAlura.repository.AutorRepository;
import com.example.SpringAlura.repository.LivroRepository;
import com.example.SpringAlura.service.ConsumoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringAluraApplication implements CommandLineRunner {

	@Autowired
	private ConsumoAPI consumoAPI;

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SpringAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mostrarMenu();
	}

	private void mostrarMenu() throws Exception {
		int opcao = -1;

		while (opcao != 0) {
			System.out.println("\n--- MENU ---");
			System.out.println("1 - Buscar livro pelo título");
			System.out.println("2 - Listar livros registrados");
			System.out.println("3 - Listar autores");
			System.out.println("4 - Listar autores vivos em determinado ano");
			System.out.println("5 - Listar livros por idioma");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			opcao = Integer.parseInt(scanner.nextLine());

			switch (opcao) {
				case 1:
					System.out.print("Digite o título do livro: ");
					String titulo = scanner.nextLine();
					consumoAPI.buscarLivroPorTituloESalvar(titulo);
					System.out.println("Livro buscado e salvo com sucesso!");
					break;

				case 2:
					List<Livro> livros = livroRepository.findAll();
					System.out.println("\n--- LIVROS REGISTRADOS ---");
					livros.forEach(l -> System.out.println(l));
					break;

				case 3:
					List<Autor> autores = autorRepository.findAll();
					System.out.println("\n--- AUTORES ---");
					autores.forEach(a -> System.out.println(a));
					break;

				case 4:
					System.out.print("Digite o ano: ");
					int ano = Integer.parseInt(scanner.nextLine());
					List<Autor> autoresVivos = autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
					System.out.println("\n--- AUTORES VIVOS EM " + ano + " ---");
					autoresVivos.forEach(a -> System.out.println(a));
					break;

				case 5:
					System.out.print("Digite o idioma: ");
					String idioma = scanner.nextLine();
					List<Livro> livrosIdioma = livroRepository.findByIdioma(idioma);
					System.out.println("\n--- LIVROS EM " + idioma + " ---");
					livrosIdioma.forEach(l -> System.out.println(l));
					break;

				case 0:
					System.out.println("Saindo...");
					break;

				default:
					System.out.println("Opção inválida!");
			}
		}
	}
}