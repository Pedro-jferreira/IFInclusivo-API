package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
