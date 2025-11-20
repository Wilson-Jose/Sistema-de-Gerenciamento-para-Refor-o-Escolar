package com.escola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EscolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscolaApplication.class, args);
		System.out.println("--- SISTEMA ESCOLAR INICIADO COM SUCESSO! ---");
		System.out.println("Acesse: http://localhost:8080/area-aluno");
	}

}