package com.example.IfGoiano.IfCoders.controller.DTO.output;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageOutputDTO {
    private Long id;
    private String text;
    private Timestamp dateTime;
    private Boolean visualizado;
    private SimpleUsuarioDTO userEnvia;
    private SimpleUsuarioDTO userRecebe;
}
