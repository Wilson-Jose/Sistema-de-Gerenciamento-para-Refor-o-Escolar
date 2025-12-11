package com.connect.connect.controller;

import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.connect.connect.model.InstituicaoUser;
import com.connect.connect.repository.RepositorioInstituicao;
import com.connect.connect.service.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class InstituicaoController {

    @Autowired
    private RepositorioInstituicao instituicaoRepo;

    @GetMapping("/")
    public String loginInstituicaoPage(HttpServletRequest request) throws UnsupportedEncodingException {
        // Se já tiver o cookie, joga direto para a Home (e não para o login)
        if (CookieService.getCookie(request, "instituicaoId") != null) {
            return "redirect:/home";
        }
        return "loginInstituicao"; 
    }

    @PostMapping("/logarInstituicao")
    public String logarInstituicao(InstituicaoUser user, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        
        InstituicaoUser userLogado = this.instituicaoRepo.login(user.getEmail(), user.getSenha());

        if (userLogado != null) {
            // Cria o cookie que dura bastante tempo (10000 segundos)
            CookieService.setCookie(response, "instituicaoId", String.valueOf(userLogado.getId()), 10000);
            
            // --- CORREÇÃO PRINCIPAL AQUI ---
            // Redireciona para a HOME (painel), não de volta para o login
            return "redirect:/home";
        }

        model.addAttribute("erro", "Usuário da instituição inválido!");
        return "loginInstituicao";
    }
}