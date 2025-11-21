package com.example.projeto_cadastro.controller; // <--- CORREÇÃO 1: O teu pacote real

// CORREÇÃO 2: Importar as tuas classes (e não as de 'com.escola')
import com.example.projeto_cadastro.model.Aluno;
import com.example.projeto_cadastro.repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    // Rota para a página de listagem
    @GetMapping("/area-aluno")
    public String listarAlunos(Model model) {
        List<Aluno> lista = alunoRepository.findAll();
        model.addAttribute("alunos", lista);
        return "area-aluno"; // Vai procurar area-aluno.html em 'templates'
    }

    // Rota para abrir o formulário
    @GetMapping("/cadastrar-aluno")
    public String formCadastro(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "cadastrar-aluno"; // Vai procurar cadastrar-aluno.html em 'templates'
    }

    // Rota que salva os dados do formulário
    @PostMapping("/salvar-aluno")
    public String salvarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
        return "redirect:/area-aluno"; // Após salvar, recarrega a página de lista
    }
}