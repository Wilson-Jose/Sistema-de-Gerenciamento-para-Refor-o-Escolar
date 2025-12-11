package com.connect.connect.controller;

import com.connect.connect.model.Aluno;
import com.connect.connect.model.Responsavel;
import com.connect.connect.repository.AlunoRepository;
import com.connect.connect.repository.ResponsavelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class AlunoController {

    @Autowired private AlunoRepository alunoRepository;
    @Autowired private ResponsavelRepository responsavelRepository;

    // --- PARTE 1: CADASTRO DO RESPONSÁVEL ---

    @GetMapping("/cadastrar-responsavel")
    public String formResponsavel(Responsavel responsavel) {
        return "cadastrar-responsavel";
    }

    @PostMapping("/salvar-responsavel")
    public String salvarResponsavel(@Valid Responsavel responsavel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "cadastrar-responsavel";
        }

        try {
            // Limpa o CPF (remove pontuação)
            String cpfLimpo = responsavel.getCpfResp().replaceAll("[^0-9]", "");
            responsavel.setCpfResp(cpfLimpo);

            // Verifica se já existe
            if (responsavelRepository.findByCpfResp(cpfLimpo).isPresent()) {
                model.addAttribute("erro", "Este CPF de responsável já está cadastrado!");
                return "cadastrar-responsavel";
            }

            responsavelRepository.save(responsavel);
            
            // Redireciona para HOME conforme pedido
            return "redirect:/home"; 

        } catch (Exception e) {
            e.printStackTrace();
            return "cadastrar-responsavel";
        }
    }

    // --- PARTE 2: CADASTRO DO ALUNO ---

    @GetMapping("/cadastrar-aluno")
    public String formAluno(Aluno aluno) {
        return "cadastrar-aluno";
    }

    @PostMapping("/salvar-aluno")
    public String salvarAluno(@Valid Aluno aluno, BindingResult result, @RequestParam("cpfResponsavelVinculo") String cpfRespVinculo, Model model) {
        
        if (result.hasErrors()) {
            return "cadastrar-aluno";
        }

        try {
            // 1. Limpa o CPF do vínculo
            String cpfRespLimpo = cpfRespVinculo.replaceAll("[^0-9]", "");
            
            // 2. Busca o Responsável no banco
            Responsavel resp = responsavelRepository.findByCpfResp(cpfRespLimpo).orElse(null);

            if (resp == null) {
                // Se não achar o pai, barra o cadastro e avisa
                model.addAttribute("erro", "Responsável não encontrado! Cadastre o responsável primeiro.");
                return "cadastrar-aluno";
            }

            // 3. Prepara e Salva o Aluno
            String cpfAlunoLimpo = aluno.getCpfAluno().replaceAll("[^0-9]", "");
            aluno.setCpfAluno(cpfAlunoLimpo);
            aluno.setStatusAluno("ATIVO");
            
            // AQUI ACONTECE O VÍNCULO MÁGICO
            aluno.setResponsavel(resp);

            alunoRepository.save(aluno);

            return "redirect:/login-aluno";

        } catch (Exception e) {
            e.printStackTrace();
            return "cadastrar-aluno";
        }
    }

    // --- LISTAGEM (Mantida igual) ---
    @GetMapping("/area-aluno")
    public String listarAlunos(@RequestParam(name = "busca", required = false) String busca, Model model) {
        List<Aluno> lista;
        if (busca != null && !busca.isEmpty()) {
            lista = alunoRepository.findByNomeAlunoContainingIgnoreCase(busca);
        } else {
            lista = alunoRepository.findAll();
        }
        model.addAttribute("alunos", lista);
        return "area-aluno";
    }
}