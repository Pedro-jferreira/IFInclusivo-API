package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {
    Page<ComentarioEntity> findByPublicacaoIdAndParentIsNull(Long publicacaoId, Pageable pageable);

    Page<ComentarioEntity> findByParentId(Long parentId, Pageable pageable);

    Page<ComentarioEntity> findByParentIdOrderByDataCriacaoAsc(Long parentId, Pageable pageable);

    Page<ComentarioEntity> findByPublicacaoIdAndParentIsNullOrderByDataCriacaoDesc(Long publicacaoId, Pageable pageable);

    @Query("""
    SELECT c FROM ComentarioEntity c
    LEFT JOIN c.likeBy l
    LEFT JOIN c.respostas r
    WHERE c.publicacao.id = :publicacaoId
    AND c.parent IS NULL
    GROUP BY c
    ORDER BY COUNT(DISTINCT l) + COUNT(DISTINCT r) DESC, c.dataCriacao DESC
    """)
    Page<ComentarioEntity> findByPublicacaoIdAndParentIsNullOrderByRelevancia(
            @Param("publicacaoId") Long publicacaoId,
            Pageable pageable);


}