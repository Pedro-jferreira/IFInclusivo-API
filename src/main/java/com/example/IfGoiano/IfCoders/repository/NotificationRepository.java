package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findByUsuarioIdAndIsReadFalseOrderByCreatedAtDesc(Long usuarioId);
    List<NotificationEntity> findByUsuarioIdOrderByCreatedAtDesc(Long usuarioId);
}