package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoResponseDTO;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface PublicacaoMapper {

    PublicacaoEntity toEntity(PublicacaoRequestDTO dto);

    @Mapping(target = "totalLikes", expression = "java(entity.getLikeBy() != null ? entity.getLikeBy().size() : 0)")
    @Mapping(target = "totalRespostas", expression = "java(entity.getComentarios() != null ? entity.getComentarios().size() : 0)")
    @Mapping(target = "respostaEscolhidaId", source = "comentarioEscolhido.id")
    @Mapping(target = "tipo", source = "tipo")
    PublicacaoResponseDTO toDetalhadaDTO(PublicacaoEntity entity, @Context UsuarioEntity usuarioLogado);

    List<PublicacaoResponseDTO> toDetalhadaDTOList(List<PublicacaoEntity> entities, @Context UsuarioEntity usuarioLogado);

    @AfterMapping
    default void setCurtidoPeloUsuario(PublicacaoEntity entity, @MappingTarget PublicacaoResponseDTO dto, @Context UsuarioEntity usuarioLogado) {
        if (usuarioLogado == null || entity.getLikeBy() == null) {
            dto.setCurtidoPeloUsuario(false);
            return;
        }
        boolean curtido = entity.getLikeBy().stream()
                .anyMatch(usuario -> usuario.getId().equals(usuarioLogado.getId()));
        dto.setCurtidoPeloUsuario(curtido);
    }
}