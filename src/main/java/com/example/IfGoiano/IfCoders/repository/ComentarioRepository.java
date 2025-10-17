package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<ComentarioEntity, Long> {
    Page<ComentarioEntity> findByPublicacaoIdAndParentIsNull(Long publicacaoId, Pageable pageable);

    Page<ComentarioEntity> findByParentId(Long parentId, Pageable pageable);

    Page<ComentarioEntity> findByParentIdOrderByDataCriacaoDesc(Long parentId, Pageable pageable);

}