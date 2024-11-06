package com.alura.literalura;

import com.alura.literalura.principal.Menu;
import com.alura.literalura.repositorio.LibroRepositorio;
import com.alura.literalura.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepositorio repositoryLibro;
	@Autowired
	private PersonaRepositorio repositoryPersona;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu menu;
		menu = new Menu(repositoryLibro, repositoryPersona);
		menu.mostrarMenu();

	}
}
