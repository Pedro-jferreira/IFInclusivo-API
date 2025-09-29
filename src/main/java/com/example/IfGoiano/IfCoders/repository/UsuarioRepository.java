package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>, JpaSpecificationExecutor<UsuarioEntity> {

    Optional<UsuarioEntity> findByLogin(String login);


    Optional<UsuarioEntity> findByMatricula(Long matricula);

}
