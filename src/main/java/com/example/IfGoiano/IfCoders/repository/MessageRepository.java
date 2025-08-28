package com.example.IfGoiano.IfCoders.repository;




import com.example.IfGoiano.IfCoders.entity.MessageEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    // Mensagens trocadas entre dois usuários
    @Query("SELECT m FROM MessageEntity m " +
            "WHERE (m.userEnvia.id = :user1 AND m.userRecebe.id = :user2) " +
            "   OR (m.userEnvia.id = :user2 AND m.userRecebe.id = :user1) " +
            "ORDER BY m.dataCriacao ASC")
    List<MessageEntity> getConversation(@Param("user1") Long user1, @Param("user2") Long user2);

    // Todos os usuários que já trocaram mensagem com esse user
    @Query("SELECT DISTINCT CASE WHEN m.userEnvia.id = :userId THEN m.userRecebe " +
            " ELSE m.userEnvia END " +
            "FROM MessageEntity m WHERE m.userEnvia.id = :userId OR m.userRecebe.id = :userId")
    List<UsuarioEntity> getUsersWhoChattedWith(@Param("userId") Long userId);

    @Query("SELECT DISTINCT CASE WHEN m.userEnvia.id = :userId THEN m.userRecebe.id " +
            " ELSE m.userEnvia.id END " +
            "FROM MessageEntity m WHERE m.userEnvia.id = :userId OR m.userRecebe.id = :userId")
    List<Long> getIdsOfUsersWhoChattedWith(@Param("userId") Long userId);
}
