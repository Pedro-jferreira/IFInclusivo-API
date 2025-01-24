package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.*;
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

        usuarioEntity = getUsuarioEntity(usuarioInputDTO);

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
            ProfessorOutputDTO p = new ProfessorOutputDTO();
            p.setFormacao(((ProfessorEntity) usuarioEntity).getFormacao());
            usuarioOutputDTO = p;
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
            AlunoNapneOutputDTO a = getAlunoNapneOutputDTO((AlunoNapneEntity) usuarioEntity);
            usuarioOutputDTO = a;
        } else if (usuarioEntity instanceof AlunoEntity) {
            AlunoOutputDTO a = new AlunoOutputDTO();
            CursoInputDTO c = new CursoInputDTO();
            c.setNome(((AlunoEntity) usuarioEntity).getCurso().getNome());
            a.setCurso(c);
            usuarioOutputDTO = a;
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

        UsuarioEntity usuarioEntity = getUsuarioEntity(usuarioOutputDTO);

        usuarioEntity.setId(usuarioOutputDTO.getId());
        usuarioEntity.setNome(usuarioOutputDTO.getNome());
        usuarioEntity.setLogin(usuarioOutputDTO.getLogin());
        usuarioEntity.setSenha(usuarioOutputDTO.getSenha());
        usuarioEntity.setMatricula(usuarioOutputDTO.getMatricula());
        usuarioEntity.setBiografia(usuarioOutputDTO.getBiografia());
        usuarioEntity.setDataCriacao(usuarioOutputDTO.getDataCriacao());

        // Se o DTO possuir ConfigAcblOutputDTO
        if (usuarioOutputDTO.getConfigAcessibilidadeEntity() != null) {
            ConfigAcessibilidadeEntity configEntity = getConfigAcessibilidadeEntity(usuarioOutputDTO, usuarioEntity);

            usuarioEntity.setConfigAcessibilidadeEntity(configEntity);
        }

        return usuarioEntity;
    }



    public void updateUsuarioEntityFromDTO(UsuarioInputDTO usuarioInputDTO, UsuarioEntity usuarioEntity) {
        if (usuarioInputDTO == null || usuarioEntity == null) {
            return;
        }

        usuarioEntity = getUsuarioEntity(usuarioInputDTO);

        usuarioEntity.setId(usuarioEntity.getId());
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

        SimpleUsuarioDTO usuarioDTO;
        if (usuarioEntity instanceof AlunoEntity){
            SimpleAlunoDTO a = new SimpleAlunoDTO();
            a.setId(usuarioEntity.getId());
            a.setNome(usuarioEntity.getNome());
            a.setMatricula(usuarioEntity.getMatricula());
            a.setBiografia(usuarioEntity.getBiografia());
            a.setDataCriacao(usuarioEntity.getDataCriacao());
            CursoInputDTO c = new CursoInputDTO();
            c.setNome(((AlunoEntity) usuarioEntity).getCurso().getNome());
            a.setCurso(c);
            usuarioDTO= a;
        }else if (usuarioEntity instanceof AlunoNapneEntity){
            SimpleAlunoNapneDTO a = new SimpleAlunoNapneDTO();
            a.setId(usuarioEntity.getId());
            a.setNome(usuarioEntity.getNome());
            a.setMatricula(usuarioEntity.getMatricula());
            a.setBiografia(usuarioEntity.getBiografia());
            a.setDataCriacao(usuarioEntity.getDataCriacao());
            CursoInputDTO c = new CursoInputDTO();
            c.setNome(((AlunoEntity) usuarioEntity).getCurso().getNome());
            a.setCurso(c);
            a.setCondicao(((AlunoNapneEntity) usuarioEntity).getCondicao());
            a.setNecessidadeEscolar(((AlunoNapneEntity) usuarioEntity).getNecessidadeEscolar());
            a.setNecessidadeEspecial(((AlunoNapneEntity) usuarioEntity).getNecessidadeEspecial());
            usuarioDTO=  a;
        } else if (usuarioEntity instanceof ProfessorEntity) {
            SimpleProfessorDTO p= new SimpleProfessorDTO();
            p.setId(usuarioEntity.getId());
            p.setNome(usuarioEntity.getNome());
            p.setMatricula(usuarioEntity.getMatricula());
            p.setBiografia(usuarioEntity.getBiografia());
            p.setDataCriacao(usuarioEntity.getDataCriacao());
            p.setFormacao(((ProfessorEntity) usuarioEntity).getFormacao());
            usuarioDTO=  p;

        } else if (usuarioEntity instanceof TutorEntity) {
            SimpleTutorDTO t= new SimpleTutorDTO();
            t.setId(usuarioEntity.getId());
            t.setNome(usuarioEntity.getNome());
            t.setMatricula(usuarioEntity.getMatricula());
            t.setBiografia(usuarioEntity.getBiografia());
            t.setDataCriacao(usuarioEntity.getDataCriacao());
            t.setEspecialidade(((TutorEntity) usuarioEntity).getEspecialidade());
            usuarioDTO=  t;
        } else if (usuarioEntity instanceof InterpreteEntity) {
            SimpleInterpreteDTO i= new SimpleInterpreteDTO();
            i.setId(usuarioEntity.getId());
            i.setNome(usuarioEntity.getNome());
            i.setMatricula(usuarioEntity.getMatricula());
            i.setBiografia(usuarioEntity.getBiografia());
            i.setDataCriacao(usuarioEntity.getDataCriacao());
            i.setSalary(((InterpreteEntity) usuarioEntity).getSalary());
            usuarioDTO=  i;
        }else throw new IllegalArgumentException("parce no usuario entity paritir do input  1 deto falhou");

        return usuarioDTO;
    }



    private UsuarioEntity getUsuarioEntity(UsuarioInputDTO usuarioInputDTO) {
        UsuarioEntity usuarioEntity;
        if (usuarioInputDTO instanceof ProfessorInputDTO) {
            ProfessorEntity p = new ProfessorEntity();
            p.setFormacao(((ProfessorInputDTO) usuarioInputDTO).getFormacao());
            usuarioEntity = p;
        } else if (usuarioInputDTO instanceof InterpreteInputDTO) {
            InterpreteEntity i = new InterpreteEntity();
            i.setSalary(((InterpreteInputDTO) usuarioInputDTO).getSalary());
            usuarioEntity = i;
        } else if (usuarioInputDTO instanceof TutorInputDTO) {
            TutorEntity t = new TutorEntity();
            t.setEspecialidade(((TutorInputDTO) usuarioInputDTO).getEspecialidade());
            usuarioEntity = t;
        } else if (usuarioInputDTO instanceof AlunoNapneInputDTO) {
            AlunoNapneEntity al = getAlunoNapneEntity((AlunoNapneInputDTO) usuarioInputDTO);
            usuarioEntity = al;
        } else if (usuarioInputDTO instanceof AlunoInputDTO) {
            usuarioEntity = new AlunoEntity();;
        } else throw new IllegalArgumentException("parce no usuario entitya paritir do input  1 deto falhou");
        return usuarioEntity;
    }

    private static AlunoNapneEntity getAlunoNapneEntity(AlunoNapneInputDTO usuarioInputDTO) {
        AlunoNapneEntity al = new AlunoNapneEntity();
        al.setAcompanhamento(usuarioInputDTO.getAcompanhamento());
        al.setCondicao(usuarioInputDTO.getCondicao());
        al.setLaudo(usuarioInputDTO.getLaudo());
        al.setNecessidadeEscolar(usuarioInputDTO.getNecessidadeEscolar());
        al.setNecessidadeEspecial(usuarioInputDTO.getNecessidadeEspecial());
        al.setSituacao(usuarioInputDTO.getSituacao());
        return al;
    }

    private static AlunoNapneOutputDTO getAlunoNapneOutputDTO(AlunoNapneEntity usuarioEntity) {
        AlunoNapneOutputDTO a  = new AlunoNapneOutputDTO();
        a.setAcompanhamento(usuarioEntity.getAcompanhamento());
        a.setCondicao(usuarioEntity.getCondicao());
        a.setLaudo(usuarioEntity.getLaudo());
        a.setNecessidadeEscolar(usuarioEntity.getNecessidadeEscolar());
        a.setNecessidadeEspecial(usuarioEntity.getNecessidadeEspecial());
        a.setSituacao(usuarioEntity.getSituacao());
        return a;
    }

    private static UsuarioEntity getUsuarioEntity(UsuarioOutputDTO usuarioOutputDTO) {
        UsuarioEntity usuarioEntity;

        if (usuarioOutputDTO instanceof ProfessorOutputDTO) {
            ProfessorEntity p = new ProfessorEntity();
            p.setFormacao(((ProfessorOutputDTO) usuarioOutputDTO).getFormacao());
            usuarioEntity = p;
        } else if (usuarioOutputDTO instanceof InterpreteOutputDTO) {
            InterpreteEntity i = new InterpreteEntity();
            i.setSalary(((InterpreteOutputDTO) usuarioOutputDTO).getSalary());
            usuarioEntity = i;
        } else if (usuarioOutputDTO instanceof TutorOutputDTO) {
            TutorEntity t = new TutorEntity();
            t.setEspecialidade(((TutorOutputDTO) usuarioOutputDTO).getEspecialidade());
            usuarioEntity = t;
        } else if (usuarioOutputDTO instanceof AlunoNapneOutputDTO) {
            AlunoNapneEntity al = getAlunoNapneEntity((AlunoNapneOutputDTO) usuarioOutputDTO);
            usuarioEntity = al;
        } else if (usuarioOutputDTO instanceof AlunoOutputDTO) {
            AlunoEntity al = new AlunoEntity();
            CursoEntity c = new CursoEntity();
            c.setNome(((AlunoOutputDTO) usuarioOutputDTO).getCurso().getNome());
            al.setCurso(c);
            usuarioEntity = al;
        } else throw new IllegalArgumentException("parce de to entity a partir do output falhou 3");
        return usuarioEntity;
    }

    private static AlunoNapneEntity getAlunoNapneEntity(AlunoNapneOutputDTO usuarioOutputDTO) {
        AlunoNapneEntity al = new AlunoNapneEntity();
        al.setAcompanhamento(usuarioOutputDTO.getAcompanhamento());
        al.setCondicao(usuarioOutputDTO.getCondicao());
        al.setLaudo(usuarioOutputDTO.getLaudo());
        al.setNecessidadeEscolar(usuarioOutputDTO.getNecessidadeEscolar());
        al.setNecessidadeEspecial(usuarioOutputDTO.getNecessidadeEspecial());
        al.setSituacao(usuarioOutputDTO.getSituacao());
        return al;
    }

    private static ConfigAcessibilidadeEntity getConfigAcessibilidadeEntity(UsuarioOutputDTO usuarioOutputDTO, UsuarioEntity usuarioEntity) {
        ConfigAcessibilidadeEntity configEntity = new ConfigAcessibilidadeEntity();
        configEntity.setAudicao(usuarioOutputDTO.getConfigAcessibilidadeEntity().getAudicao());
        configEntity.setZoom(usuarioOutputDTO.getConfigAcessibilidadeEntity().getZoom());
        configEntity.setTema(usuarioOutputDTO.getConfigAcessibilidadeEntity().getTema());
        configEntity.setId(usuarioOutputDTO.getConfigAcessibilidadeEntity().getId());
        configEntity.setUsuario(usuarioEntity);
        return configEntity;
    }

}