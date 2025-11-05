package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<TutorEntity, Long> {
    Optional<TutorEntity> findByLogin(String login);
}
