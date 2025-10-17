package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.ComentarioSimplesDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioResponseDTO;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface ComentarioMapper {

    ComentarioEntity toEntity(ComentarioRequestDTO dto);

    @Mapping(target = "totalLikes", expression = "java(entity.getLikeBy() != null ? entity.getLikeBy().size() : 0)")
    @Mapping(target = "totalRespostas", expression = "java(entity.getRespostas() != null ? entity.getRespostas().size() : 0)")
    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "usuarioMencionado", source = "usuarioMencionado")
    @Mapping(target ="publicacaoId", source = "publicacao.id")
    ComentarioResponseDTO toResponseDTO(ComentarioEntity entity, @Context UsuarioEntity usuarioLogado);

    @Mapping(target = "totalLikes", expression = "java(entity.getLikeBy() != null ? entity.getLikeBy().size() : 0)")
    ComentarioSimplesDTO toSimplesDTO(ComentarioEntity entity, @Context UsuarioEntity usuarioLogado);

    List<ComentarioResponseDTO> toResponseDTOList(List<ComentarioEntity> entities, @Context UsuarioEntity usuarioLogado);
    List<ComentarioSimplesDTO> toSimplesDTOList(List<ComentarioEntity> entities, @Context UsuarioEntity usuarioLogado);

    @AfterMapping
    default void setCurtidoPeloUsuario(ComentarioEntity entity, @MappingTarget ComentarioResponseDTO dto, @Context UsuarioEntity usuarioLogado) {
        if (usuarioLogado == null || entity.getLikeBy() == null) {
            dto.setCurtidoPeloUsuario(false);
            return;
        }
        boolean curtido = entity.getLikeBy().stream()
                .anyMatch(usuario -> usuario.getId().equals(usuarioLogado.getId()));
        dto.setCurtidoPeloUsuario(curtido);
    }

    @AfterMapping
    default void setCurtidoPeloUsuarioSimples(ComentarioEntity entity, @MappingTarget ComentarioSimplesDTO dto, @Context UsuarioEntity usuarioLogado) {
        if (usuarioLogado == null || entity.getLikeBy() == null) {
            dto.setCurtidoPeloUsuario(false);
            return;
        }
        boolean curtido = entity.getLikeBy().stream()
                .anyMatch(usuario -> usuario.getId().equals(usuarioLogado.getId()));
        dto.setCurtidoPeloUsuario(curtido);
    }
}