package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfessorOutputDTO extends UsuarioOutputDTO {
    private String formacao;
    private List<SimpleTopicoDTO> topicos = new ArrayList<>();
}
