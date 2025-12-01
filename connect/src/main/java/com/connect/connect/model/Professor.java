	package com.connect.connect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Professor {

	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_Professor;
	
	
	@NotEmpty(message = "O nome é obrigatório.")
	@Pattern(regexp = "^[A-Za-zÁ-ÖØ-öø-ÿ\\s]+$", message = "O nome deve conter apenas letras")
	private String nome_Professor;
	
	@NotEmpty(message = "O CPF é obrigatório")
	@Column(unique = true)
	@Size(min = 11, max = 11, message = "O CPF deve conter apenas 11 numeros")
	@Pattern(regexp = "\\d+", message = "O CPF não deve conter letras")
	private String cpf_Professor;
	
	@NotEmpty(message = "O email é obrigatorio")
	@Column(unique = true)
	@Email(message = "O Email deve ser válido")
	private String email_Professor;
	
	@NotNull(message = "O telefone é obrigatorio")
	@Min(value = 1000000000L, message = "O numero deve ter pelo menos 10 digitos (DDD + numero)")
	@Max(value = 99999999999L,  message = "O numero deve ter no maximo 11 digitos (DDD + numero)")
	private long telefone_Professor;

	@NotEmpty(message = "A senha é obrigatorio")
	@Size(min = 6, message = "A senha deve ter no minimo 6 caracteres.")
	private String senha_Professor;
	
	public String getNome_Professor() {
		return nome_Professor;
	}

	public void setNome_Professor(String nome_Professor) {
		this.nome_Professor = nome_Professor;
	}

	public String getCpf_Professor() {
		return cpf_Professor;
	}

	public void setCpf_Professor(String cpf_Professor) {
		this.cpf_Professor = cpf_Professor;
	}

	public String getEmail_Professor() {
		return email_Professor;
	}

	public void setEmail_Professor(String email_Professor) {
		this.email_Professor = email_Professor;
	}

	public long getTelefone_Professor() {
		return telefone_Professor;
	}

	public void setTelefone_Professor(long telefone_Professor) {
		this.telefone_Professor = telefone_Professor;
	}

	public Long getCod_Professor() {
		return cod_Professor;
	}

	public String getSenha_Professor() {
		return senha_Professor;
	}

	public void setSenha_Professor(String senha_Professor) {
		this.senha_Professor = senha_Professor;
	}
	
	
	
	
}
