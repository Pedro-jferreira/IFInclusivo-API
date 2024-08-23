package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.model.Interprete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterpreteRepository extends JpaRepository<Interprete, Long> {

}
