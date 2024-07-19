package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.PK.ComentarioId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, ComentarioId> {

    List<ComentarioRepository> findByComentarioPai(Comentario comentarioPai);
}
