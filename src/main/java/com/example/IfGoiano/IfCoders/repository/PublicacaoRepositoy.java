package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface PublicacaoRepositoy extends JpaRepository<PublicacaoEntity, Long>,
        JpaSpecificationExecutor<PublicacaoEntity> {

    Page<PublicacaoEntity> findPublicacaoEntitiesByUsuarioOrderByDataCriacaoDesc(UsuarioEntity usuario, Pageable pageable);

    @Query("""
         SELECT DISTINCT p
    FROM PublicacaoEntity p
    JOIN p.categorias c
    WHERE LOWER(p.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))
    AND (:categorias IS NULL OR c IN :categorias)
    ORDER BY p.dataCriacao DESC
    """)
    List<PublicacaoEntity> findByTituloAndOptionalCategorias(
            @Param("titulo") String titulo,
            @Param("categorias") Set<Categorias> categorias
    );

}