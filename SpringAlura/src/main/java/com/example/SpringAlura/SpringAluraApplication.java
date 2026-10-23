package com.example.SpringAlura;

import com.example.SpringAlura.principal.Principal;
import com.example.SpringAlura.service.LivroService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAluraApplication.class, args);
	}

	@Bean
	CommandLineRunner executarMenu(LivroService livroService,
								   @Value("${app.menu.enabled:false}") boolean menuEnabled) {
		return args -> {
			if (menuEnabled) {
				new Principal(livroService).exibeMenu();
			}
		};
	}
}
