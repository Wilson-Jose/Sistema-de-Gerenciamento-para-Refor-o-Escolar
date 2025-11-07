	package com.connect.connect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_Professor;
	
	@NotEmpty
	private String nome_Professor;
	
	@NotEmpty
	private String cpf_Professor;
	
	@NotEmpty
	private String email_Professor;
	
	@NotNull
	private long telefone_Professor;

	@NotEmpty
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
