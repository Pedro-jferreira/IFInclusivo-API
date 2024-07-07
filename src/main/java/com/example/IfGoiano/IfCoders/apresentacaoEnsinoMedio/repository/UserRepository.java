package com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.repository;

import com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.vistoPorUltimo = CURRENT_TIMESTAMP WHERE u.id = :id")
    void updateVistoPorUltimoById(Long id);

    @Query("SELECT DISTINCT u FROM UserEntity u " +
            "WHERE u.id IN (" +
            "    SELECT DISTINCT CASE " +
            "                     WHEN m.userEnvia.id = :userId THEN m.userRecebe.id " +
            "                     WHEN m.userRecebe.id = :userId THEN m.userEnvia.id " +
            "                END AS contactId " +
            "    FROM MessageEntity m " +
            "    WHERE m.userEnvia.id = :userId OR m.userRecebe.id = :userId" +
            ") AND u.id != :userId")
    List<UserEntity> findContactsByUserId(@Param("userId") Long userId);


}
