package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.output.NotificationOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.NotificationMapper;
import com.example.IfGoiano.IfCoders.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationMapper notificationMapper;

    @GetMapping("/unread/{usuarioId}")
    public ResponseEntity<List<NotificationOutputDTO>> getUnreadNotifications(@PathVariable Long usuarioId) {
        var notifications = notificationService.getUnreadNotifications(usuarioId);
        return ResponseEntity.ok(notificationMapper.toOutputDTOList(notifications));
    }

    @GetMapping("/all/{usuarioId}")
    public ResponseEntity<List<NotificationOutputDTO>> getAllNotifications(@PathVariable Long usuarioId) {
        var notifications = notificationService.getAllNotifications(usuarioId);
        return ResponseEntity.ok(notificationMapper.toOutputDTOList(notifications));
    }

    @PutMapping("/read/{notificationId}")
    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok().build();
    }
}