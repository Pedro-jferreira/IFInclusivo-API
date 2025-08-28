package com.example.IfGoiano.IfCoders.controller.DTO.input;

import lombok.Data;

@Data
public class MessageInputDTO {
    private Long IdUserEnvia;
    private Long IdUserRecebe;
    private String text;
}
