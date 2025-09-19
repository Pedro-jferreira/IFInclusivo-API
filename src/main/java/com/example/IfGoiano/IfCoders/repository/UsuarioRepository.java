package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByLogin(String login);

    Optional<UsuarioEntity> findByMatricula(Long matricula);

}
