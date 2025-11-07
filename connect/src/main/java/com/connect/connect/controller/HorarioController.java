package com.connect.connect.controller;


import java.io.UnsupportedEncodingException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.connect.connect.model.Horario;
import com.connect.connect.model.Professor;
import com.connect.connect.repository.RepositorioHorario;
import com.connect.connect.repository.RepositorioProfessor;
import com.connect.connect.service.CookieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



@Controller
public class HorarioController {
	@Autowired
    private RepositorioHorario repositorioHorario;

    @Autowired
    private RepositorioProfessor repositorioProfessor;


    @GetMapping("/horarioProf")
    public String getHorarioPage(Model model, HttpServletRequest request) throws UnsupportedEncodingException {

        String profIdCookie = CookieService.getCookie(request, "professorId");
        

        if (profIdCookie == null) {
            return "redirect:/login";
        }

        long profId = Long.parseLong(profIdCookie);
        Professor professor = repositorioProfessor.findById(profId); 

        if (professor == null) {

            return "redirect:/login";
        }


        java.util.List<Horario> horarios = repositorioHorario.findByProfessor(professor);

        model.addAttribute("horario", new Horario()); 
        model.addAttribute("horarios", horarios); 
        model.addAttribute("professorNome", CookieService.getCookie(request, "professorNome")); 
        
        return "horarioProf"; 
    }

    @PostMapping("/horarioProf")
    public String cadastrarHorario(@Valid Horario horario, BindingResult result, HttpServletRequest request, Model model) throws UnsupportedEncodingException {


        String profIdCookie = CookieService.getCookie(request, "professorId");
        if (profIdCookie == null) {
            return "redirect:/login";
        }
        
        long profId = Long.parseLong(profIdCookie);
        Professor professor = repositorioProfessor.findById(profId); //

        if (professor == null) {
            return "redirect:/login";
        }


        if (result.hasErrors()) {

            java.util.List<Horario> horarios = repositorioHorario.findByProfessor(professor);
            model.addAttribute("horarios", horarios);
            model.addAttribute("professorNome", CookieService.getCookie(request, "professorNome"));

            return "horarioProf";
        }


        horario.setProfessor(professor);

        repositorioHorario.save(horario);


        return "redirect:/horarioProf";
    }


    @GetMapping("/horario/excluir/{id_horario}")
    public String excluirHorario(
            @PathVariable("id_horario") Long id_horario, 
            HttpServletRequest request, 
            RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {


        String profIdCookie = CookieService.getCookie(request, "professorId");
        if (profIdCookie == null) {
            return "redirect:/login";
        }
        
        long profLogadoId = Long.parseLong(profIdCookie);


        Optional<Horario> horarioOptional = repositorioHorario.findById(id_horario);

        if (horarioOptional.isPresent()) {
            Horario horario = horarioOptional.get();


            if (horario.getProfessor().getCod_Professor() == profLogadoId) {

                repositorioHorario.delete(horario);
                redirectAttributes.addFlashAttribute("sucesso", "Horário excluído com sucesso!");
            } else {

                redirectAttributes.addFlashAttribute("erro", "Você não tem permissão para excluir este horário.");
            }
        } else {

            redirectAttributes.addFlashAttribute("erro", "Horário não encontrado.");
        }


        return "redirect:/horarioProf";
    }
}