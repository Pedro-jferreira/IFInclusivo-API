package com.example.IfGoiano.IfCoders.controller.DTO.output;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class MessageOutputDTO {
    private Long id;
    private String text;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private Boolean visualizado;
    private SimpleUsuarioDTO userEnvia;
    private SimpleUsuarioDTO userRecebe;
}
