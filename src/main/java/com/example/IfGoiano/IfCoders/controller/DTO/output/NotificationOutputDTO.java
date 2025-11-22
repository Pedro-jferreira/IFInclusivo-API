package com.example.IfGoiano.IfCoders.controller.DTO.output;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationOutputDTO {
    private Long id;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
    private String palavraLibras;
}