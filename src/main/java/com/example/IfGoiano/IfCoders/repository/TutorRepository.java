package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.model.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<TutorEntity, Long> {
}
