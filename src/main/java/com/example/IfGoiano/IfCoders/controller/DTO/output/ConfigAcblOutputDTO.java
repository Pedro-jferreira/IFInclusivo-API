package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.TemaCSS;
import lombok.Data;

@Data
public class ConfigAcblOutputDTO {
    private Long id;
    private String audicao;
    private TemaCSS tema;
    private String zoom;
}
