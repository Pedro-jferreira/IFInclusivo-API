package com.example.IfGoiano.IfCoders.repository;


import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrasRepository extends JpaRepository<LibrasEntity, Long> {

    Page<LibrasEntity> findAll(Pageable pageable);

    Page<LibrasEntity> findByPalavra(String palavra, Pageable pageable);

    Page<LibrasEntity> findByStatus(Status status, Pageable pageable);


    @Query("SELECT DISTINCT l.palavra FROM LibrasEntity l " +
            "WHERE LOWER(l.palavra) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(l.descricao) LIKE LOWER(CONCAT('%',:search, '%')) ")
    List<String> searchLibrasByDeeply(@Param("search") String search);

    @Query("SELECT l FROM LibrasEntity l WHERE l.categorias = :categoria AND l.id != :excludeId")
    Page<LibrasEntity> findRelatedByCategoria(@Param("categoria") Categorias categoria, @Param("excludeId") Long excludeId, Pageable pageable);

    Optional<LibrasEntity> findByPalavra(String palavra);

    Page<LibrasEntity> findByCategorias(Categorias categoria, Pageable pageable);

    @Query("SELECT l FROM LibrasEntity l WHERE " +
           "(:termo IS NULL OR LOWER(l.palavra) LIKE LOWER(CONCAT('%', :termo, '%'))) AND " +
           "(:status IS NULL OR l.status = :status)")
    Page<LibrasEntity> buscarComFiltros(@Param("termo") String termo, @Param("status") Status status, Pageable pageable);

}
