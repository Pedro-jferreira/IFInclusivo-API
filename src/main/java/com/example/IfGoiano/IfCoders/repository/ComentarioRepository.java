package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {

    List<ComentarioRepository> findByComentarioPai(ComentarioEntity comentarioPai);
}
