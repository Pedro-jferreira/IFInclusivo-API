package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import lombok.Data;

import java.util.List;

@Data
public class TopicoOutputDTO {
    private Long id;
    private SimpleProfessorDTO professor;
    private String tema;
    private String descripcion;
    private List<SimplePublicacaoDTO> publicacoes;
}
