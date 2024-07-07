package com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.repository;

import com.example.IfGoiano.IfCoders.apresentacaoEnsinoMedio.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

@Query("SELECT m from MessageEntity m where  m.userEnvia.id = :userEnvia and m.userRecebe.id = :userRecebe"  )
    List<MessageEntity> getExchangedMessages(@Param("userEnvia") Long userEnvia, @Param("userRecebe") Long userRecebe);
}
