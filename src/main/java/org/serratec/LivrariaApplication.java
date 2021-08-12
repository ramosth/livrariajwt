package org.serratec;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LivrariaApplication {

	// TODO Professor coloca a classificação do livro, ficou faltando.
	public static void main(String[] args) {
		Locale.setDefault(new Locale("pt", "BR")); 
		SpringApplication.run (LivrariaApplication.class, args);
	}
}
