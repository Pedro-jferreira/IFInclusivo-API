package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import lombok.Data;


import java.util.Objects;
@Data
public class ComentarioInputDTO {
    private String content;
    private SimpleComentarioDTO comentarioPai;
}
