package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    Optional<ProfessorEntity> findByLogin(String login);
}
