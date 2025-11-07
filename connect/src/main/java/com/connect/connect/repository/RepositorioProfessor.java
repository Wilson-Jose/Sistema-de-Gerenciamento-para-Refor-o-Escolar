package com.connect.connect.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.connect.connect.model.Professor;

public interface RepositorioProfessor extends CrudRepository<Professor, Long> {

	Professor findById(long cod_Professor);
	
	@Query(value="select * from  connect.professor where email_Professor = :email_Professor and senha_Professor = :senha_Professor", nativeQuery = true)
	public Professor login(String email_Professor, String senha_Professor); 
}
