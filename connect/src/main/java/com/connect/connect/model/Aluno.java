package com.connect.connect.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAluno;

    @Column(nullable = false)
    private String nomeAluno;

    @Column(nullable = false, unique = true, length = 14)
    private String cpfAluno;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "grau_ensino", nullable = false)
    private String grauEnsino;

    @Column(name = "instituicao_ensino", nullable = false)
    private String instituicaoEnsino;

    @Column(length = 500)
    private String observacao;

    @Column(nullable = false)
    private String statusAluno = "ATIVO";

    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "id_resp", nullable = false)
    private Responsavel responsavel;

    // --- GETTERS E SETTERS MANUAIS ---

    public Long getIdAluno() { return idAluno; }
    public void setIdAluno(Long idAluno) { this.idAluno = idAluno; }

    public String getNomeAluno() { return nomeAluno; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }

    public String getCpfAluno() { return cpfAluno; }
    public void setCpfAluno(String cpfAluno) { this.cpfAluno = cpfAluno; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getGrauEnsino() { return grauEnsino; }
    public void setGrauEnsino(String grauEnsino) { this.grauEnsino = grauEnsino; }

    public String getInstituicaoEnsino() { return instituicaoEnsino; }
    public void setInstituicaoEnsino(String instituicaoEnsino) { this.instituicaoEnsino = instituicaoEnsino; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public String getStatusAluno() { return statusAluno; }
    public void setStatusAluno(String statusAluno) { this.statusAluno = statusAluno; }

    public Responsavel getResponsavel() { return responsavel; }
    public void setResponsavel(Responsavel responsavel) { this.responsavel = responsavel; }
}