package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.model.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
