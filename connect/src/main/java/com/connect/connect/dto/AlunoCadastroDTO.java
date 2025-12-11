package com.connect.connect.dto;

import java.time.LocalDate;

public record AlunoCadastroDTO(
    String nomeAluno,
    String cpfAluno,
    LocalDate dataNascimento,
    String grauEnsino,
    String instituicaoEnsino,
    String observacao,
    
    // Dados do Responsável
    String nomeResp,
    String cpfResp,
    String emailResp,
    String telResp,
    String endereco
) {}