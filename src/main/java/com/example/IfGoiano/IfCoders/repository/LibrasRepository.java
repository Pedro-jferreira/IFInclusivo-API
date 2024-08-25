package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrasRepository extends JpaRepository<LibrasEntity, Long> {
}
