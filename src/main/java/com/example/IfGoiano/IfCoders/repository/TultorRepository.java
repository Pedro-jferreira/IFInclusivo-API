package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TultorRepository  extends JpaRepository<Tutor, Long> {
}
