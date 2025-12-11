package com.connect.connect.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {

	@GetMapping("/cadAluno")
	public String cadastroAluno() {
		return "cadAluno";
	}


	@GetMapping("/exportFicha")
	public String exportarficha() {
		return "exportFicha";
	}
	
	@GetMapping("/ficha-aluno")
	public String fichaAluno() {
		return "ficha-aluno";
	}
}
