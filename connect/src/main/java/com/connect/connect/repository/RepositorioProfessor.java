package com.connect.connect.repository;

import com.connect.connect.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProfessor extends JpaRepository<Professor, Long> {
    
    @Query(value = "SELECT * FROM professor WHERE email_professor = :email AND senha_professor = :senha", nativeQuery = true)
    Professor login(String email, String senha);
}