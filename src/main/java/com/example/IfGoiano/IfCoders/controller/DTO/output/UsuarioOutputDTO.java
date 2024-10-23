package com.example.IfGoiano.IfCoders.controller.DTO.output;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import lombok.Data;
import java.util.List;

@Data
public class UsuarioOutputDTO {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private Long matricula;
    private String biografia;
    private ConfigAcblOutputDTO configAcessibilidadeEntity;
    private List<SimpleComentarioDTO> comentarios;
    private List<SimplePublicacaoDTO> publicacaoEntities;

}
