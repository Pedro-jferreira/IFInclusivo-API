package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleLibrasDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.*;
import com.example.IfGoiano.IfCoders.controller.DTO.output.*;
import com.example.IfGoiano.IfCoders.entity.*;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper {


    public UsuarioEntity toEntity(UsuarioInputDTO usuarioInputDTO) {
        if (usuarioInputDTO == null) {
            return null;
        }

        UsuarioEntity usuarioEntity ;

        if (usuarioInputDTO instanceof ProfessorInputDTO) {
            usuarioEntity = new ProfessorEntity();
        } else if (usuarioInputDTO instanceof InterpreteInputDTO) {
            usuarioEntity = new InterpreteEntity();
        } else if (usuarioInputDTO instanceof TutorInputDTO) {
            usuarioEntity = new TutorEntity();
        } else if (usuarioInputDTO instanceof AlunoNapneInputDTO) {
            usuarioEntity = new AlunoNapneEntity();
        } else if (usuarioInputDTO instanceof AlunoInputDTO) {
            usuarioEntity = new AlunoEntity();
        } else throw new IllegalArgumentException("parce no usuario entitya paritir do input  1 deto falhou");

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

        UsuarioOutputDTO usuarioOutputDTO;

        // Verifica o tipo da entidade e instancia o DTO correspondente
        if (usuarioEntity instanceof ProfessorEntity) {
            usuarioOutputDTO = new ProfessorOutputDTO();
        } else if (usuarioEntity instanceof InterpreteEntity) {
            InterpreteOutputDTO interpreteOutputDTO = new InterpreteOutputDTO();
            interpreteOutputDTO.setSalary(((InterpreteEntity) usuarioEntity).getSalary());
            List<SimpleLibrasDTO> librasDTOS = new ArrayList<>();
            for (LibrasEntity librasEntity : ((InterpreteEntity) usuarioEntity).getLibras()) {
                SimpleLibrasDTO sl = new SimpleLibrasDTO();
                sl.setId(librasEntity.getId());
                sl.setCategorias(librasEntity.getCategorias());
                sl.setPalavra(librasEntity.getPalavra());
                librasDTOS.add(sl);
            }
            interpreteOutputDTO.setLibras(librasDTOS);
            usuarioOutputDTO = interpreteOutputDTO;
        } else if (usuarioEntity instanceof TutorEntity) {
            TutorOutputDTO tutorOutputDTO = new TutorOutputDTO();
            tutorOutputDTO.setEspecialidade(((TutorEntity) usuarioEntity).getEspecialidade());
            usuarioOutputDTO = tutorOutputDTO;
        } else if (usuarioEntity instanceof AlunoNapneEntity) {
            usuarioOutputDTO = new AlunoNapneOutputDTO();
        } else if (usuarioEntity instanceof AlunoEntity) {
            usuarioOutputDTO = new AlunoOutputDTO();
        }else throw new IllegalArgumentException("parce to output  a partir do usuario entity 2 nao conseguiu identificar o tipo");

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

    public UsuarioEntity toEntity(UsuarioOutputDTO usuarioOutputDTO) {
        if (usuarioOutputDTO == null) {
            return null;
        }

        UsuarioEntity usuarioEntity;

        if (usuarioOutputDTO instanceof ProfessorOutputDTO) {
            usuarioEntity = new ProfessorEntity();
        } else if (usuarioOutputDTO instanceof InterpreteOutputDTO) {
            usuarioEntity = new InterpreteEntity();
        } else if (usuarioOutputDTO instanceof TutorOutputDTO) {
            usuarioEntity = new TutorEntity();
        } else if (usuarioOutputDTO instanceof AlunoNapneOutputDTO) {
            usuarioEntity = new AlunoNapneEntity();
        } else if (usuarioOutputDTO instanceof AlunoOutputDTO) {
            usuarioEntity = new AlunoEntity();
        } else throw new IllegalArgumentException("parce de to entity a partir do output falhou 3");

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