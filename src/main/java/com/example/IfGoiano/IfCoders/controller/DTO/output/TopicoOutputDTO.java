package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import lombok.Data;

import java.util.List;

@Data
public class TopicoOutputDTO {
    private Long id;
    private SimpleProfessorDTO professor;
    private String tema;
    private Categorias descripcion;
    private List<SimplePublicacaoDTO> publicacoes;
}
