package com.example.IfGoiano.IfCoders.repository;


import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrasRepository extends JpaRepository<LibrasEntity, Long> {

    Page<LibrasEntity> findAll(Pageable pageable);

    Page<LibrasEntity> findByPalavra(String palavra, Pageable pageable);

    Page<LibrasEntity> findByStatus(Status status, Pageable pageable);


    @Query("SELECT DISTINCT l FROM LibrasEntity l " +
            "WHERE LOWER(l.palavra) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(l.descricao) LIKE LOWER(CONCAT('%',:search, '%')) ")
    Page<LibrasEntity> searchLibrasByDeeply(@Param("search") String search, Pageable pageable);

    LibrasEntity findByPalavra(String palavra);


}
