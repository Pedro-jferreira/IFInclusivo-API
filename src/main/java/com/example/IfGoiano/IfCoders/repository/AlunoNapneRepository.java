package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoNapneRepository extends JpaRepository<AlunoNapneEntity,Long> {
    Optional<AlunoNapneEntity> findByLogin(String login);

}
