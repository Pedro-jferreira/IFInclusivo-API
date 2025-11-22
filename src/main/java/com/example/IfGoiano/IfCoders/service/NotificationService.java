package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.entity.NotificationEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;

import java.util.List;

public interface NotificationService {
    void createLibrasApprovedNotification(LibrasEntity libras);
    List<NotificationEntity> getUnreadNotifications(Long usuarioId);
    List<NotificationEntity> getAllNotifications(Long usuarioId);
    void markAsRead(Long notificationId);
    void createLibrasReproveNotification(LibrasEntity libras);
}