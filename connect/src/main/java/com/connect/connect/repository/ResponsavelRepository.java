package com.connect.connect.repository;

import com.connect.connect.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    // Adicione esta linha para buscar por CPF
    Optional<Responsavel> findByCpfResp(String cpf);
}