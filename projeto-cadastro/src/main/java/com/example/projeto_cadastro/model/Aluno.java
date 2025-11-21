package com.example.projeto_cadastro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeAluno;
    
    // Mudámos de 'idade' para 'dataNascimento' para bater certo com o HTML
    private String dataNascimento; 

    private String nomeResponsavel;
    private String telefoneResponsavel;
    
    // Adicionámos este campo que faltava
    private String emailResponsavel; 

    // Construtor vazio obrigatório
    public Aluno() {}

    // --- GETTERS E SETTERS (Obrigatórios para o HTML funcionar) ---
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeAluno() { return nomeAluno; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public void setNomeResponsavel(String nomeResponsavel) { this.nomeResponsavel = nomeResponsavel; }

    public String getTelefoneResponsavel() { return telefoneResponsavel; }
    public void setTelefoneResponsavel(String telefoneResponsavel) { this.telefoneResponsavel = telefoneResponsavel; }

    public String getEmailResponsavel() { return emailResponsavel; }
    public void setEmailResponsavel(String emailResponsavel) { this.emailResponsavel = emailResponsavel; }
}