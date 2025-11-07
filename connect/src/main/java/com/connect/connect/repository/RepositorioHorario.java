package com.connect.connect.repository;


import org.springframework.data.repository.CrudRepository;

import com.connect.connect.model.Horario;
import com.connect.connect.model.Professor;

import java.util.List;


public interface RepositorioHorario extends CrudRepository<Horario, Long> {
    

    List<Horario> findByProfessor(Professor professor);

}
