package com.connect.connect.controller;

import java.io.UnsupportedEncodingException;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;


import com.connect.connect.service.CookieService;


import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SiteController {

	
	@GetMapping("/emitir-relatorio")
	public String emitirRelatorio() {
		throw new UnsupportedOperationException("Problema detectado, tente novamente mais tarde.");	
	}
	
	@ExceptionHandler(UnsupportedOperationException.class)
	public String tratarErroRelatorio(UnsupportedOperationException ex, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		model.addAttribute("erro", ex.getMessage());
		model.addAttribute("nome", CookieService.getCookie(request, "professorNome"));
		return "home";
	}
}
