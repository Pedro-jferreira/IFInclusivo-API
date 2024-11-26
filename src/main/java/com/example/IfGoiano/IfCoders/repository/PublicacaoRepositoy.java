package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PublicacaoRepositoy extends JpaRepository<PublicacaoEntity,Long> {
    @Query("""
        SELECT p FROM PublicacaoEntity p
        WHERE 
            LOWER(p.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR
            LOWER(p.text) LIKE LOWER(CONCAT('%', :termo, '%'))
            ORDER BY p.dataCriacao DESC
    """)
    Page<PublicacaoEntity> searchPublicacaoByTermQuickly(@Param("termo") String termo, Pageable pageable);

    @Query("""
    SELECT DISTINCT p
    FROM PublicacaoEntity p
    LEFT JOIN p.comentarios c
    LEFT JOIN c.comentariosFilhos cf
    LEFT JOIN p.topico t
    WHERE
        LOWER(p.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR
        LOWER(p.text) LIKE LOWER(CONCAT('%', :termo, '%')) OR
        LOWER(c.content) LIKE LOWER(CONCAT('%', :termo, '%')) OR
        LOWER(cf.content) LIKE LOWER(CONCAT('%', :termo, '%')) OR
        LOWER(t.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR
        LOWER(t.tema) LIKE LOWER(CONCAT('%', :termo, '%')) OR
        LOWER(t.descripcion) LIKE LOWER(CONCAT('%', :termo, '%'))
    ORDER BY p.dataCriacao DESC
    """)
    Page<PublicacaoEntity> searchPublicacaoByTermDeeply(@Param("termo") String termo, Pageable pageable);

}
