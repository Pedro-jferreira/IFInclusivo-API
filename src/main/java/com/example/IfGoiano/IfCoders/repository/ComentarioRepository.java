package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<ComentarioRepository> findByComentarioPai(Comentario comentarioPai);
}
