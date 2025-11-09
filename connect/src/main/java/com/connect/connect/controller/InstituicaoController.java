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

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class InstituicaoController {

    @Autowired
    private RepositorioInstituicao instituicaoRepo;


    
    
    @GetMapping("/")
    public String loginInstituicaoPage() {
        return "loginInstituicao"; 
    }


    @PostMapping("/logarInstituicao")
    public String logarInstituicao(InstituicaoUser user, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        
        InstituicaoUser userLogado = this.instituicaoRepo.login(user.getEmail(), user.getSenha());

        if (userLogado != null) {

            CookieService.setCookie(response, "instituicaoId", String.valueOf(userLogado.getId()), 10000);
            

            return "redirect:/login";
        }

        model.addAttribute("erro", "Usuário da instituição inválido!");
        return "loginInstituicao";
    }
}