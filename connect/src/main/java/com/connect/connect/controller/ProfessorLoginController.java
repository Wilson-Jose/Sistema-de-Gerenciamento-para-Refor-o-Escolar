package com.connect.connect.controller;

import com.connect.connect.model.Professor;
// ATENÇÃO: Se você estiver usando o RepositorioProfessor antigo, mude a linha abaixo
import com.connect.connect.repository.RepositorioProfessor; 
import com.connect.connect.service.CookieService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfessorLoginController {

    @Autowired 
    private RepositorioProfessor professorRepository; // Usando o seu repositório existente

    @GetMapping("/login-prof")
    public String loginPage() {
        return "loginProf"; 
    }

    @PostMapping("/logar-prof")
    public String logar(String email, String senha, Model model, HttpServletResponse response) {
        // Usando o método login que criamos no RepositorioProfessor
        Professor prof = professorRepository.login(email, senha);

        if (prof != null) {
            try {
                // Aqui usamos getCod_Professor() que agora existe no Model
                CookieService.setCookie(response, "professorId", String.valueOf(prof.getCod_Professor()), 3600);
                return "redirect:/area-prof"; 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("erro", "Email ou senha inválidos!");
        return "loginProf";
    }

    @GetMapping("/cadastro-prof")
    public String formCadastro() {
        return "cadastroProf"; 
    }

    @PostMapping("/salvar-prof")
    public String salvar(Professor professor) {
        professorRepository.save(professor);
        return "redirect:/login-prof";
    }

    @GetMapping("/area-prof")
    public String areaProfessor() {
        return "home"; 
    }
    
    @GetMapping("/sair-prof")
    public String sair(HttpServletResponse response) {
        try {
            CookieService.setCookie(response, "professorId", "", 0);
        } catch (Exception e) {}
        return "redirect:/";
    }
}