package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {

    Optional<AlunoEntity> findByLogin(String login);
    
    @Query("SELECT a FROM AlunoEntity a WHERE " +
           "LOWER(CAST(a.matricula AS string)) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(a.nome) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<AlunoEntity> buscarAlunosPorTermo(@Param("termo") String termo);
}

