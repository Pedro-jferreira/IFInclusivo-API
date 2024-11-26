package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrasRepository extends JpaRepository<LibrasEntity, Long> {
    Page<LibrasEntity> findByCategoria(Categorias categoria, Pageable pageable);

    Page<LibrasEntity> findByPalavraContainingIgnoreCaseAndStatus(String termo, Status status, Pageable pageable);


    @Query("SELECT l FROM LibrasEntity l " +
            "WHERE (LOWER(l.palavra) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "OR LOWER(l.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) " +
            "OR LOWER(l.categorias) LIKE LOWER(CONCAT('%', :termo, '%'))) " +
            "AND l.status = :status")
    Page<LibrasEntity> searchLibrasByTermDeeply(@Param("termo") String termo,
                                                @Param("status") Status status,
                                                Pageable pageable);

}
