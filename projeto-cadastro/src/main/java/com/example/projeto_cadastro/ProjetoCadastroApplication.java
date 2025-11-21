package com.example.projeto_cadastro; // <--- TEM DE SER EXATAMENTE ASSIM

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Esta é a anotação mágica que liga tudo
public class ProjetoCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoCadastroApplication.class, args);
	}

}