package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepositoy extends JpaRepository<TopicoEntity,Long> {

    Page<TopicoEntity> findByCategoria(Categorias categoria, Pageable pageable);

    Page<TopicoEntity> findByTemaStartingWithIgnoreCase(String termo, Pageable pageable);

    @Query("SELECT DISTINCT t FROM TopicoEntity t " +
            "LEFT JOIN t.publicacoes p " +
            "WHERE LOWER(t.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "OR LOWER(t.tema) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "OR LOWER(t.descripcion) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "OR LOWER(p.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "OR LOWER(p.text) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<TopicoEntity> searchTopicByTermDeeply(@Param("termo") String termo, Pageable pageable);

}
