package com.connect.connect.repository;

import com.connect.connect.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByNomeAlunoContainingIgnoreCase(String nome);
}