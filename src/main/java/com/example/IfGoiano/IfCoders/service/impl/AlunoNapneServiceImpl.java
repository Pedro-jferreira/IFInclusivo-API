package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoNapneInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoNapneMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.CursoMapper;
import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.AlunoNapneRepository;
import com.example.IfGoiano.IfCoders.repository.CursoRepository;
import com.example.IfGoiano.IfCoders.service.AlunoNapneService;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import com.example.IfGoiano.IfCoders.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoNapneServiceImpl implements AlunoNapneService {

    @Autowired
    AlunoNapneRepository alunoNapneRepository;

    @Autowired
    private AlunoNapneMapper alunoNapneMapper;

    @Autowired
    CursoService cursoService;
    @Autowired
    CursoMapper cursoMapper;
    @Autowired
    ConfigAcessibilidadeService configAcessibilidadeService;
    @Autowired
    ConfigAcblMapper configAcblMapper;


    @Override
    public List<AlunoNapneOutputDTO> findAll(){
        List<AlunoNapneOutputDTO> listAlunos = new ArrayList<>();
        alunoNapneRepository.findAll().stream().forEach(alunoNapneEntity -> listAlunos.add(alunoNapneMapper.toAlunoNapneOutputDTO(alunoNapneEntity)));

        return listAlunos;
    }

    @Override
    public AlunoNapneOutputDTO findById(Long id){
        var aluno  = alunoNapneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return alunoNapneMapper.toAlunoNapneOutputDTO(aluno);
    }

    @Override
    @Transactional
    public AlunoNapneOutputDTO save(AlunoNapneInputDTO alunoNapneInput, Long idCurso, Long idConfigAc) {
        var curso = cursoService.findById(idCurso);
        var acessibilidade = configAcessibilidadeService.findById(idConfigAc);
        AlunoNapneEntity a = alunoNapneMapper.toAlunoNapneEntity(alunoNapneInput);
        a.setCurso(cursoMapper.toCursoEntity(curso));
        a.setConfigAcessibilidadeEntity(configAcblMapper.toConfigAcblEntity(acessibilidade));
        return findById(alunoNapneRepository.save(a).getId());
    }

    @Override
    @Transactional
    public AlunoNapneOutputDTO update(AlunoNapneInputDTO alunoNapne, Long id) {
        var entity = alunoNapneRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        alunoNapneMapper.updateAlunoNapneEntiryFromDTO(alunoNapne,entity);
        return alunoNapneMapper.toAlunoNapneOutputDTO(entity);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        var entity = alunoNapneRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        alunoNapneRepository.delete(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return alunoNapneRepository.existsById(id);
    }
}
