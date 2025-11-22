package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.output.NotificationOutputDTO;
import com.example.IfGoiano.IfCoders.entity.NotificationEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationMapper {

    public NotificationOutputDTO toOutputDTO(NotificationEntity notification) {
        NotificationOutputDTO dto = new NotificationOutputDTO();
        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setRead(notification.isRead());
        dto.setCreatedAt(notification.getCreatedAt());
        if (notification.getLibras() != null) {
            dto.setPalavraLibras(notification.getLibras().getPalavra());
        }
        return dto;
    }

    public List<NotificationOutputDTO> toOutputDTOList(List<NotificationEntity> notifications) {
        return notifications.stream()
                .map(this::toOutputDTO)
                .collect(Collectors.toList());
    }
}