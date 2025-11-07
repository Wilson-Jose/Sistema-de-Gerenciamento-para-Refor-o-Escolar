package com.connect.connect.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;



@Entity
public class Horario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id_horario;

    @NotEmpty(message = "O dia da semana é obrigatório")
    private String diaSemana;

    @NotEmpty(message = "A hora de início é obrigatória")
    private String horaInicio; 

    @NotEmpty(message = "A hora de fim é obrigatória")
    private String horaFim;

    @ManyToOne
    @JoinColumn(name = "cod_Professor") 
    private Professor professor;



    public Long getId_horario() {
        return id_horario;
    }

    public void setId_horario(Long id_horario) {
        this.id_horario = id_horario;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}