package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.CursoMapper;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import com.example.IfGoiano.IfCoders.repository.CursoRepository;
import com.example.IfGoiano.IfCoders.service.AlunoService;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import com.example.IfGoiano.IfCoders.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoMapper mapper;
    @Autowired
    CursoService cursoService;
    @Autowired
    CursoMapper cursoMapper;
    @Autowired
    ConfigAcessibilidadeService configAcessibilidadeService;
    @Autowired
    ConfigAcblMapper configAcblMapper;


    @Override
    public List<AlunoOutputDTO> findAll() {
        return alunoRepository.findAll().stream().map(mapper::toAlunoOutputDTO).collect(Collectors.toList());
    }

    @Override
    public AlunoOutputDTO findById(Long id) {
        var aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return mapper.toAlunoOutputDTO(aluno);
    }

    @Override
    @Transactional
    public AlunoOutputDTO save(AlunoInputDTO aluno, Long idCurso, Long idConfigAc) {
        var curso = cursoService.findById(idCurso);
        var acessibilidade = configAcessibilidadeService.findById(idConfigAc);
        AlunoEntity a = mapper.toAlunoEntity(aluno);
        a.setCurso(cursoMapper.toCursoEntity(curso));
        a.setConfigAcessibilidadeEntity(configAcblMapper.toConfigAcblEntity(acessibilidade));
        return findById( alunoRepository.save(a).getId());
    }

    @Override
    @Transactional
    public AlunoOutputDTO update(Long id, AlunoInputDTO alunoDetails) {
        var aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        mapper.updateAlunoEntityFromDTO( alunoDetails,aluno);
        return mapper.toAlunoOutputDTO(alunoRepository.save(aluno));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        alunoRepository.delete(mapper.toAlunoEntity(findById(id)));
    }

    @Override
    public boolean existsById(Long id) {
        return alunoRepository.existsById(id);
    }


}
