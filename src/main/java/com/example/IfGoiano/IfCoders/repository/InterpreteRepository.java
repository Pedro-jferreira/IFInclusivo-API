package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterpreteRepository extends JpaRepository<InterpreteEntity, Long> {
    Optional<InterpreteEntity> findByLogin(String login);

}
