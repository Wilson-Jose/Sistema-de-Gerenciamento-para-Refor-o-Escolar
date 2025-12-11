package com.connect.connect.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_Professor; // Ajustado para bater com seu controller

    private String nome_Professor;

    @Column(unique = true)
    private String email_Professor;

    private String senha_Professor;
    
    private String cpf_Professor;
    private Long telefone_Professor;

    // --- GETTERS E SETTERS (Com o underline, para o seu Controller não quebrar) ---

    public Long getCod_Professor() { return cod_Professor; }
    public void setCod_Professor(Long cod_Professor) { this.cod_Professor = cod_Professor; }
    
    // Alias para o novo controller achar (Método de compatibilidade)
    public Long getIdProfessor() { return cod_Professor; } 

    public String getNome_Professor() { return nome_Professor; }
    public void setNome_Professor(String nome_Professor) { this.nome_Professor = nome_Professor; }

    public String getEmail_Professor() { return email_Professor; }
    public void setEmail_Professor(String email_Professor) { this.email_Professor = email_Professor; }

    public String getSenha_Professor() { return senha_Professor; }
    public void setSenha_Professor(String senha_Professor) { this.senha_Professor = senha_Professor; }
    
    public String getCpf_Professor() { return cpf_Professor; }
    public void setCpf_Professor(String cpf_Professor) { this.cpf_Professor = cpf_Professor; }
    
    public Long getTelefone_Professor() { return telefone_Professor; }
    public void setTelefone_Professor(Long telefone_Professor) { this.telefone_Professor = telefone_Professor; }
}