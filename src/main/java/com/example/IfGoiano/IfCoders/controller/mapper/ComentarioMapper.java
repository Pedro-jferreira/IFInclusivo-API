package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Lazy
    @Autowired
    private PublicacaoMapper publicacaoMapper;

    public SimpleComentarioDTO toSimpleComentarioDTO(ComentarioEntity comentarioEntity) {
        SimpleComentarioDTO dto = new SimpleComentarioDTO();
        dto.setId(comentarioEntity.getId());
        dto.setContent(comentarioEntity.getContent());
        dto.setDataCriacao(comentarioEntity.getDataCriacao());
        return dto;
    }

    public ComentarioEntity toComentarioEntity(SimpleComentarioDTO simpleComentarioDTO) {
        return modelMapper.map(simpleComentarioDTO, ComentarioEntity.class);
    }

    public ComentarioInputDTO toComentarioInputDTO(ComentarioEntity comentarioEntity) {
        return modelMapper.map(comentarioEntity, ComentarioInputDTO.class);
    }

    public ComentarioEntity toComentarioEntity(ComentarioInputDTO comentarioInputDTO) {
        if (comentarioInputDTO == null) {
            return null; // Tratar caso nulo
        }
        ComentarioEntity comentarioEntity = new ComentarioEntity();
        comentarioEntity.setContent(comentarioInputDTO.getContent());
        return comentarioEntity;
    }

    public ComentarioOutputDTO toComentarioOutputDTO(ComentarioEntity comentarioEntity) {
        if (comentarioEntity == null) {
            return null;
        }

        ComentarioOutputDTO comentarioOutputDTO = new ComentarioOutputDTO();


        comentarioOutputDTO.setId(comentarioEntity.getId());
        comentarioOutputDTO.setContent(comentarioEntity.getContent());
        comentarioOutputDTO.setDataCriacao(comentarioEntity.getDataCriacao());

        if (comentarioEntity.getUsuario() != null) {
            comentarioOutputDTO.setUsuario(usuarioMapper.toSimpleDTO(comentarioEntity.getUsuario()));
            System.out.println("errooo 000000");
        }

        // Mapeamento do campo 'publicacao'
        if (comentarioEntity.getPublicacao() != null) comentarioOutputDTO
                .setPublicacao(publicacaoMapper
                        .toSimplePublicacaoDTO(comentarioEntity
                                .getPublicacao()));


        // Mapeamento do 'comentarioPai'
        if (comentarioEntity.getComentarioPai() != null) {
            comentarioOutputDTO.setComentarioPai(toSimpleComentarioDTO(comentarioEntity.getComentarioPai()));
            System.out.println("\n\n\nerro 1111\n\n\n");
        }

        // Mapeamento dos 'comentariosFilhos'
        if (comentarioEntity.getComentariosFilhos() != null && !comentarioEntity.getComentariosFilhos().isEmpty()) {
            for (ComentarioEntity comentario : comentarioEntity.getComentariosFilhos()) {
                comentarioOutputDTO.getComentariosFilhos().add(toSimpleComentarioDTO(comentario));
            }

        }
        // Mapeamento do 'usefulBy'
        if (comentarioEntity.getUsefulBy() != null && !comentarioEntity.getUsefulBy().isEmpty()) {
            for (UsuarioEntity usuario : comentarioEntity.getUsefulBy()) {
                comentarioOutputDTO.getUsefulBy().add(usuarioMapper.toSimpleDTO(usuario));
                System.out.println("\n\n\nErrooo 2222\n\n\n");
            }
        }

        return comentarioOutputDTO;
    }

    public ComentarioEntity toComentarioEntity(ComentarioOutputDTO comentarioOutputDTO) {
        return modelMapper.map(comentarioOutputDTO, ComentarioEntity.class);
    }

    public void updateComentarioEntityFromDTO(ComentarioInputDTO comentarioDeitals, ComentarioEntity comentarioEntity) {
        modelMapper.map(comentarioDeitals, comentarioEntity);
    }

}