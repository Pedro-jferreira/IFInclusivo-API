package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {

    Optional<AlunoEntity> findByLogin(String login);
}

