package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleProfessorDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleTopicoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessorMapper {
    @Autowired
    ModelMapper modelMapper;


    public ProfessorEntity toProfessorEntity(ProfessorInputDTO professorInputDTO){
        return modelMapper.map(professorInputDTO, ProfessorEntity.class);
    }
    public ProfessorEntity toProfessorEntity(ProfessorOutputDTO professorOutputDTO){
        return modelMapper.map(professorOutputDTO, ProfessorEntity.class);
    }

    public ProfessorOutputDTO toProfessorOutputDTO(ProfessorEntity professorEntity){
        ProfessorOutputDTO p = new ProfessorOutputDTO();
        p.setId(professorEntity.getId());
        p.setNome(professorEntity.getNome());
        p.setBiografia(professorEntity.getBiografia());
        p.setLogin(professorEntity.getLogin());
        p.setSenha(professorEntity.getSenha());
        p.setMatricula(professorEntity.getMatricula());
        p.setDataCriacao(professorEntity.getDataCriacao());
        p.setFormacao(professorEntity.getFormacao());
        ConfigAcblOutputDTO a = new ConfigAcblOutputDTO();
        a.setId(professorEntity.getConfigAcessibilidadeEntity().getId());
        a.setTema(professorEntity.getConfigAcessibilidadeEntity().getTema());
        a.setAudicao(professorEntity.getConfigAcessibilidadeEntity().getAudicao());
        a.setZoom(professorEntity.getConfigAcessibilidadeEntity().getZoom());
        p.setConfigAcessibilidadeEntity(a);

        List<SimpleTopicoDTO> st = new ArrayList<>();
        for (TopicoEntity t :professorEntity.getTopicos()) {
            SimpleTopicoDTO aux = new SimpleTopicoDTO();
            aux.setId(t.getId());
            aux.setTitulo(t.getTitulo());
            aux.setDescricao(t.getDescricao());
            aux.setDataCriacao(t.getDataCriacao());
            aux.setCategoria(t.getCategoria());
            SimpleProfessorDTO sp = new SimpleProfessorDTO();
            sp.setId(t.getProfessor().getId());
            sp.setNome(t.getProfessor().getNome());
            sp.setMatricula(t.getProfessor().getMatricula());
            sp.setBiografia(t.getProfessor().getBiografia());
            sp.setDataCriacao(t.getProfessor().getDataCriacao());
            aux.setProfessor(sp);
            st.add(aux);
        }
        p.setTopicos(st);

        return p;
    }

    public SimpleProfessorDTO toSimpleProfessorDTO(ProfessorEntity professorEntity){
        return modelMapper.map(professorEntity, SimpleProfessorDTO.class);
    }

    public void updateProfessorEntityFromDTO(ProfessorInputDTO professorDeitails, ProfessorEntity professorEntity){
        modelMapper.map(professorDeitails, professorEntity);

    }


}
