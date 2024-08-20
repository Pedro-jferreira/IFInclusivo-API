package com.example.IfGoiano.IfCoders.mapper;


import com.example.IfGoiano.IfCoders.DTO.ComentarioDTO;
import com.example.IfGoiano.IfCoders.model.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {PublicacaoMapper.class, ResolveuProblemaMapper.class})
public interface ComentarioMapper {
    ComentarioMapper INSTANCE = Mappers.getMapper(ComentarioMapper.class);

    @Mapping(source = "publicacao", target = "publicacaoDTO")
    @Mapping(source = "comentarioPai", target = "comentarioPaiDTO")
    @Mapping(source = "resolveuProblemas", target = "resolveuProblemaDTOS")
    ComentarioDTO comentarioToComentarioDTO(Comentario comentario);

    @Mapping(source = "publicacaoDTO", target = "publicacao")
    @Mapping(source = "comentarioPaiDTO", target = "comentarioPai")
    @Mapping(source = "resolveuProblemaDTOS", target = "resolveuProblemas")
    Comentario comentarioDTOToComentario(ComentarioDTO comentarioDTO);

    @Mapping(source = "publicacaoDTO", target = "publicacao")
    @Mapping(source = "comentarioPaiDTO", target = "comentarioPai")
    @Mapping(source = "resolveuProblemaDTOS", target = "resolveuProblemas")
    void updateComentarioFromDTO(ComentarioDTO comentarioDTO, @MappingTarget Comentario comentario);
}
