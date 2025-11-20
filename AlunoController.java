package com.escola.controller;

import com.escola.model.Aluno;
import com.escola.repository.AlunoRepository;
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

    // Lista os alunos na página 'area-aluno'
    @GetMapping("/area-aluno")
    public String listarAlunos(Model model) {
        List<Aluno> lista = alunoRepository.findAll();
        model.addAttribute("alunos", lista);
        return "area-aluno";
    }

    // Abre o formulário de cadastro
    @GetMapping("/cadastrar-aluno")
    public String formCadastro(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "cadastrar-aluno";
    }

    // Salva o aluno e volta para a lista
    @PostMapping("/salvar-aluno")
    public String salvarAluno(Aluno aluno) {
        alunoRepository.save(aluno);
        return "redirect:/area-aluno";
    }
}