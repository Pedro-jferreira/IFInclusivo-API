package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.entity.NotificationEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.NotificationRepository;
import com.example.IfGoiano.IfCoders.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void createLibrasApprovedNotification(LibrasEntity libras) {
        for (UsuarioEntity usuario : libras.getSugeriu()) {
            NotificationEntity notification = new NotificationEntity();
            notification.setMessage("Sua sugestão da palavra '" + libras.getPalavra() + "' foi aprovada!");
            notification.setUsuario(usuario);
            notification.setLibras(libras);
            notificationRepository.save(notification);
        }
    }

    @Override
    public List<NotificationEntity> getUnreadNotifications(Long usuarioId) {
        return notificationRepository.findByUsuarioIdAndIsReadFalseOrderByCreatedAtDesc(usuarioId);
    }

    @Override
    public List<NotificationEntity> getAllNotifications(Long usuarioId) {
        return notificationRepository.findByUsuarioIdOrderByCreatedAtDesc(usuarioId);
    }

    @Override
    public void markAsRead(Long notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    @Override
    public void createLibrasReproveNotification(LibrasEntity libras) {
        for (UsuarioEntity usuario : libras.getSugeriu()) {
            NotificationEntity notification = new NotificationEntity();
            notification.setMessage("Sua sugestão da palavra '" + libras.getPalavra() + "foi reprovada pois " +
                    libras.getJustificativa());
            notification.setUsuario(usuario);
            notification.setLibras(libras);
            notificationRepository.save(notification);
        }
    }
}