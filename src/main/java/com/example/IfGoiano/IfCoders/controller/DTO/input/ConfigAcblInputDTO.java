package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.Enums.TemaCSS;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;
@Data
public class ConfigAcblInputDTO {
    private String audicao;
    private TemaCSS tema;
    private String zoom;

}
