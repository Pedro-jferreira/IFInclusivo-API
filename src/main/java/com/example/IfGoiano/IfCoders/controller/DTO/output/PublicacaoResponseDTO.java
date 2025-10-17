package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.PublicacaoUsuarioDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.TipoPublicacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO completo com todos os detalhes de uma publicação específica.
 * Usado para a publicação "foco" na árvore de visualização.
 */
@Data
public class PublicacaoResponseDTO {
    private Long id;
    private String titulo;
    private String texto;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;
    private PublicacaoUsuarioDTO usuario;
    private TipoPublicacao tipo;
    private Set<Categorias> categorias;
    private int totalLikes;
    private int totalRespostas;
    private boolean curtidoPeloUsuario;
    private Long respostaEscolhidaId;
}