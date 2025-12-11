package com.connect.connect.model;

import jakarta.persistence.*;

@Entity
@Table(name = "responsavel")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResp;

    @Column(nullable = false)
    private String nomeResp;

    @Column(nullable = false, unique = true, length = 14)
    private String cpfResp;

    @Column(nullable = false)
    private String emailResp;

    @Column(nullable = false)
    private String telResp;

    private String endereco;

    // --- GETTERS E SETTERS MANUAIS (Para garantir que funcione) ---

    public Long getIdResp() { return idResp; }
    public void setIdResp(Long idResp) { this.idResp = idResp; }

    public String getNomeResp() { return nomeResp; }
    public void setNomeResp(String nomeResp) { this.nomeResp = nomeResp; }

    public String getCpfResp() { return cpfResp; }
    public void setCpfResp(String cpfResp) { this.cpfResp = cpfResp; }

    public String getEmailResp() { return emailResp; }
    public void setEmailResp(String emailResp) { this.emailResp = emailResp; }

    public String getTelResp() { return telResp; }
    public void setTelResp(String telResp) { this.telResp = telResp; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}