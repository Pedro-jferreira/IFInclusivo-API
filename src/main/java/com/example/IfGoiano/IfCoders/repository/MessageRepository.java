package com.example.IfGoiano.IfCoders.repository;




import com.example.IfGoiano.IfCoders.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT m FROM MessageEntity m " +
            "WHERE (m.userEnvia.id = :userEnvia AND m.userRecebe.id = :userRecebe) " +
            "   OR (m.userEnvia.id = :userRecebe AND m.userRecebe.id = :userEnvia) " )
    List<MessageEntity> getExchangedMessages(@Param("userEnvia") Long userEnvia, @Param("userRecebe") Long userRecebe);
}
