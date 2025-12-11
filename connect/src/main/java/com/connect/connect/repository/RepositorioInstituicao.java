package com.connect.connect.repository;

import com.connect.connect.model.InstituicaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioInstituicao extends JpaRepository<InstituicaoUser, Long> {

    // Busca exata por email e senha no banco
    @Query(value = "SELECT * FROM usuario_instituicao WHERE email = :email AND senha = :senha", nativeQuery = true)
    InstituicaoUser login(String email, String senha);
}