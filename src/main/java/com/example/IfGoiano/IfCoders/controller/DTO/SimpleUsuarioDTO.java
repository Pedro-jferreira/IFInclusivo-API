package com.example.IfGoiano.IfCoders.controller.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleUsuarioDTO {
    private Long id;
    private String nome;
    private Long matricula;
    private String biografia;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
}
