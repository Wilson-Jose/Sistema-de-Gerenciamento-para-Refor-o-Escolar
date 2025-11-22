package com.connect.connect.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; // Importar java.util.List
import java.util.Map;   // Importar Map
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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
import com.fasterxml.jackson.core.JsonProcessingException; // Importar Jackson
import com.fasterxml.jackson.databind.ObjectMapper;     // Importar Jackson

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class HorarioController {
    @Autowired
    private RepositorioHorario repositorioHorario;

    @Autowired
    private RepositorioProfessor repositorioProfessor;


    private int convertDayToInteger(String diaSemana) {
        switch (diaSemana) {
            case "Domingo": return 0;
            case "Segunda-feira": return 1;
            case "Terça-feira": return 2;
            case "Quarta-feira": return 3;
            case "Quinta-feira": return 4;
            case "Sexta-feira": return 5;
            case "Sábado": return 6;
            default: return 0; 
        }
    }


    private String prepararEventosJson(List<Horario> horarios) {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> eventList = new ArrayList<>();

        for (Horario h : horarios) {
            Map<String, Object> event = new HashMap<>();
            event.put("title", h.getNomeEvento());
            event.put("daysOfWeek", new int[] { convertDayToInteger(h.getDiaSemana()) });
            event.put("startTime", h.getHoraInicio());
            event.put("endTime", h.getHoraFim());

            event.put("id", h.getId_horario()); 
            eventList.add(event);
        }

        try {

            return mapper.writeValueAsString(eventList);
        } catch (JsonProcessingException e) {

            return "[]";
        }
    }

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

        List<Horario> horarios = repositorioHorario.findByProfessor(professor);


        String eventsJson = prepararEventosJson(horarios);
        
        model.addAttribute("horario", new Horario());
        model.addAttribute("horarios", horarios);
        

        model.addAttribute("eventsJson", eventsJson); 


        model.addAttribute("nome", CookieService.getCookie(request, "professorNome"));

        return "horarioProf";
    }

    @PostMapping("/horarioProf")
    public String cadastrarHorario(@Valid Horario horario, BindingResult result, HttpServletRequest request, Model model)
            throws UnsupportedEncodingException {

        String profIdCookie = CookieService.getCookie(request, "professorId");
        if (profIdCookie == null) {
            return "redirect:/login";
        }

        long profId = Long.parseLong(profIdCookie);
        Professor professor = repositorioProfessor.findById(profId); 

        if (professor == null) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {


            List<Horario> horarios = repositorioHorario.findByProfessor(professor);
            

            String eventsJson = prepararEventosJson(horarios);
            model.addAttribute("eventsJson", eventsJson);

            model.addAttribute("horarios", horarios);
            

            model.addAttribute("nome", CookieService.getCookie(request, "professorNome"));

            return "horarioProf"; 
        }

        horario.setProfessor(professor);
        repositorioHorario.save(horario);

        return "redirect:/horarioProf";
    }

    @GetMapping("/horario/excluir/{id_horario}")
    public String excluirHorario(
            @PathVariable("id_horario") @NonNull Long id_horario,
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