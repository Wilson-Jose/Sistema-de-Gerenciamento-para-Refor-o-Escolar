package com.connect.connect.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.connect.connect.model.InstituicaoUser;

public interface RepositorioInstituicao extends CrudRepository<InstituicaoUser, Long> {


    @Query(value="SELECT * FROM usuario_instituicao WHERE email = :email AND senha = :senha", nativeQuery = true)
    public InstituicaoUser login(String email, String senha);
}