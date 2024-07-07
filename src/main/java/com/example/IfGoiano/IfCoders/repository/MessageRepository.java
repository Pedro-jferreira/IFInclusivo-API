package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {


}
