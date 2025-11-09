package com.connect.connect.controller;

import java.io.UnsupportedEncodingException;



import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; 


import com.connect.connect.model.Professor;
import com.connect.connect.repository.RepositorioProfessor;
import com.connect.connect.service.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	private RepositorioProfessor ur;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/index")
	public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		model.addAttribute("nome",CookieService.getCookie(request, "professorNome"));
		return "index";
	}
	@PostMapping("/logar")
	public String loginProf(Professor professor, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
		Professor profLogado = this.ur.login(professor.getEmail_Professor(), professor.getSenha_Professor());
		if(profLogado != null) {
			CookieService.setCookie(response, "professorId", String.valueOf(profLogado.getCod_Professor()), 10000);
			CookieService.setCookie(response, "professorNome", String.valueOf(profLogado.getNome_Professor()), 10000);
			
			return "redirect:/index";
		}
		model.addAttribute("erro", "Usuario invalido!");
		return "/login";
	}
	
	
	@GetMapping("/sair")
	public String sair(Professor professor, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
		CookieService.setCookie(response, "professorId", "", 0);
			
			return "login";
		}
	
	
	@GetMapping("/sairProf")
	public String sairDaTelaLoginProfessor(HttpServletResponse response) throws UnsupportedEncodingException {

		CookieService.setCookie(response, "professorId", "", 0);   
		CookieService.setCookie(response, "instituicaoId", "", 0); 
			
		return "redirect:/"; 
	}
	
	
	

	@GetMapping("/cadastroProf")
	public String cadastro() {
		return "cadastro";
	}
	
	@PostMapping("/cadastroProf")
	public String cadastroProfessor(@Valid Professor professor, BindingResult result) {
		
		if(result.hasErrors()) {
			return "cadastro"; 
		}
		
		ur.save(professor);
		
		return "redirect:/login"; 
	}
}