package com.example.IfGoiano.IfCoders.controller.mapper;


import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComentarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SimpleComentarioDTO toSimpleComentarioDTO(ComentarioEntity comentarioEntity) {
        return modelMapper.map(comentarioEntity, SimpleComentarioDTO.class);
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

        if (comentarioInputDTO.getComentarioPai() != null && comentarioInputDTO.getComentarioPai().getId() != null) {
            ComentarioEntity comentarioPai = new ComentarioEntity();
            comentarioPai.setId(comentarioInputDTO.getComentarioPai().getId());
            comentarioEntity.setComentarioPai(comentarioPai);
        }

        return comentarioEntity;
    }



    public ComentarioOutputDTO toComentarioOutputDTO(ComentarioEntity comentarioEntity) {
        if (comentarioEntity == null) {
            return null;  // Tratar caso nulo
        }

        ComentarioOutputDTO comentarioOutputDTO = new ComentarioOutputDTO();

        // Mapeamento dos campos simples
        comentarioOutputDTO.setId(comentarioEntity.getId());
        comentarioOutputDTO.setContent(comentarioEntity.getContent());
        comentarioOutputDTO.setDataCriacao(comentarioEntity.getDataCriacao());

        // Mapeamento do campo 'usuario'
        if (comentarioEntity.getUsuario() != null) {
            SimpleUsuarioDTO usuarioDTO = new SimpleUsuarioDTO();
            usuarioDTO.setId(comentarioEntity.getUsuario().getId());
            usuarioDTO.setNome(comentarioEntity.getUsuario().getNome()); // Adicione mais campos se necessário
            comentarioOutputDTO.setUsuario(usuarioDTO);
        }

        // Mapeamento do campo 'publicacao'
        if (comentarioEntity.getPublicacao() != null) {
            SimplePublicacaoDTO publicacaoDTO = new SimplePublicacaoDTO();
            publicacaoDTO.setId(comentarioEntity.getPublicacao().getId());
            publicacaoDTO.setTitulo(comentarioEntity.getPublicacao().getTitulo()); // Adicione mais campos se necessário
            comentarioOutputDTO.setPublicacao(publicacaoDTO);
        }

        // Mapeamento do 'comentarioPai'
        if (comentarioEntity.getComentarioPai() != null) {
            SimpleComentarioDTO comentarioPaiDTO = new SimpleComentarioDTO();
            comentarioPaiDTO.setId(comentarioEntity.getComentarioPai().getId());
            comentarioOutputDTO.setComentarioPai(comentarioPaiDTO);
        }

        // Mapeamento dos 'comentariosFilhos'
        if (comentarioEntity.getComentariosFilhos() != null && !comentarioEntity.getComentariosFilhos().isEmpty()) {
            List<SimpleComentarioDTO> filhosDTO = new ArrayList<>();
            for (ComentarioEntity comentarioFilho : comentarioEntity.getComentariosFilhos()) {
                SimpleComentarioDTO comentarioFilhoDTO = new SimpleComentarioDTO();
                comentarioFilhoDTO.setId(comentarioFilho.getId());
                filhosDTO.add(comentarioFilhoDTO);
            }
            comentarioOutputDTO.setComentariosFilhos(filhosDTO);
        }

        // Mapeamento do 'usefulBy'
        if (comentarioEntity.getUsefulBy() != null && !comentarioEntity.getUsefulBy().isEmpty()) {
            List<SimpleUsuarioDTO> usefulByDTO = new ArrayList<>();
            for (UsuarioEntity usuarioEntity : comentarioEntity.getUsefulBy()) {
                SimpleUsuarioDTO usuarioDTO = new SimpleUsuarioDTO();
                usuarioDTO.setId(usuarioEntity.getId());
                usuarioDTO.setNome(usuarioEntity.getNome()); // Adicione mais campos se necessário
                usefulByDTO.add(usuarioDTO);
            }
            comentarioOutputDTO.setUsefulBy(usefulByDTO);
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