package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {

}

