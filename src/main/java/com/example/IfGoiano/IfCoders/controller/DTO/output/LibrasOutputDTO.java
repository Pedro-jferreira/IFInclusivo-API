package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleInterpreteDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import lombok.Data;

import java.util.List;
@Data
public class LibrasOutputDTO {
    private Long id;
    private String palavra;
    private String descricao;
    private String url;
    private String video;
    private String foto;
    private String justificativa;
    private Status status;
    private Categorias categorias;
    private List<SimpleUsuarioDTO> sugeriu;
    private List<SimpleInterpreteDTO> interprete;

}
