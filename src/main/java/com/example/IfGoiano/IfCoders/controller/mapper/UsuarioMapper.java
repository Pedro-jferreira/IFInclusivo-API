package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {


    public UsuarioEntity toEntity(UsuarioInputDTO usuarioInputDTO) {
        if (usuarioInputDTO == null) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(usuarioInputDTO.getNome());
        usuarioEntity.setLogin(usuarioInputDTO.getLogin());
        usuarioEntity.setSenha(usuarioInputDTO.getSenha());
        usuarioEntity.setMatricula(usuarioInputDTO.getMatricula());
        usuarioEntity.setBiografia(usuarioInputDTO.getBiografia());

        return usuarioEntity;
    }


    public UsuarioInputDTO toInputDTO(UsuarioEntity usuarioEntity) {
        if (usuarioEntity == null) {
            return null;
        }

        UsuarioInputDTO usuarioInputDTO = new UsuarioInputDTO();
        usuarioInputDTO.setNome(usuarioEntity.getNome());
        usuarioInputDTO.setLogin(usuarioEntity.getLogin());
        usuarioInputDTO.setSenha(usuarioEntity.getSenha());
        usuarioInputDTO.setMatricula(usuarioEntity.getMatricula());
        usuarioInputDTO.setBiografia(usuarioEntity.getBiografia());

        return usuarioInputDTO;
    }
    public UsuarioOutputDTO toOutputDTO(UsuarioEntity usuarioEntity) {
        if (usuarioEntity == null) {
            return null;
        }

        UsuarioOutputDTO usuarioOutputDTO = new UsuarioOutputDTO();
        usuarioOutputDTO.setId(usuarioEntity.getId());
        usuarioOutputDTO.setNome(usuarioEntity.getNome());
        usuarioOutputDTO.setLogin(usuarioEntity.getLogin());
        usuarioOutputDTO.setSenha(usuarioEntity.getSenha());
        usuarioOutputDTO.setMatricula(usuarioEntity.getMatricula());
        usuarioOutputDTO.setBiografia(usuarioEntity.getBiografia());
        usuarioOutputDTO.setDataCriacao(usuarioEntity.getDataCriacao());

        // Se a entidade possuir um relacionamento de ConfigAcblEntity
        if (usuarioEntity.getConfigAcessibilidadeEntity() != null) {
            ConfigAcblOutputDTO configDto = new ConfigAcblOutputDTO();
            configDto.setAudicao(usuarioEntity.getConfigAcessibilidadeEntity().getAudicao());
            configDto.setTema(usuarioEntity.getConfigAcessibilidadeEntity().getTema());
            configDto.setZoom(usuarioEntity.getConfigAcessibilidadeEntity().getZoom());
            usuarioOutputDTO.setConfigAcessibilidadeEntity(configDto);
        }

        return usuarioOutputDTO;
    }

    // Mapeia de UsuarioOutputDTO para UsuarioEntity
    public UsuarioEntity toEntity(UsuarioOutputDTO usuarioOutputDTO) {
        if (usuarioOutputDTO == null) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(usuarioOutputDTO.getId());
        usuarioEntity.setNome(usuarioOutputDTO.getNome());
        usuarioEntity.setLogin(usuarioOutputDTO.getLogin());
        usuarioEntity.setSenha(usuarioOutputDTO.getSenha());
        usuarioEntity.setMatricula(usuarioOutputDTO.getMatricula());
        usuarioEntity.setBiografia(usuarioOutputDTO.getBiografia());
        usuarioEntity.setDataCriacao(usuarioOutputDTO.getDataCriacao());

        // Se o DTO possuir ConfigAcblOutputDTO
        if (usuarioOutputDTO.getConfigAcessibilidadeEntity() != null) {
            ConfigAcessibilidadeEntity configEntity = new ConfigAcessibilidadeEntity();
            configEntity.setAudicao(usuarioOutputDTO.getConfigAcessibilidadeEntity().getAudicao());
            configEntity.setZoom(usuarioOutputDTO.getConfigAcessibilidadeEntity().getZoom());
            configEntity.setTema(usuarioOutputDTO.getConfigAcessibilidadeEntity().getTema());
            configEntity.setId(usuarioOutputDTO.getConfigAcessibilidadeEntity().getId());
            configEntity.setUsuario(usuarioEntity);

            usuarioEntity.setConfigAcessibilidadeEntity(configEntity);
        }

        return usuarioEntity;
    }
    public void updateUsuarioEntityFromDTO(UsuarioInputDTO usuarioInputDTO, UsuarioEntity usuarioEntity) {
        if (usuarioInputDTO == null || usuarioEntity == null) {
            return;
        }


        usuarioEntity.setNome(usuarioInputDTO.getNome());
        usuarioEntity.setLogin(usuarioInputDTO.getLogin());
        usuarioEntity.setSenha(usuarioInputDTO.getSenha());
        usuarioEntity.setMatricula(usuarioInputDTO.getMatricula());
        usuarioEntity.setBiografia(usuarioInputDTO.getBiografia());




    }
    public SimpleUsuarioDTO toSimpleDTO(UsuarioEntity usuarioEntity) {
        if (usuarioEntity == null) {
            return null;
        }
        SimpleUsuarioDTO simpleUsuarioDTO = new SimpleUsuarioDTO();
        simpleUsuarioDTO.setId(usuarioEntity.getId());
        simpleUsuarioDTO.setNome(usuarioEntity.getNome());
        simpleUsuarioDTO.setMatricula(usuarioEntity.getMatricula());
        simpleUsuarioDTO.setDataCriacao(usuarioEntity.getDataCriacao());
        return simpleUsuarioDTO;
    }
}