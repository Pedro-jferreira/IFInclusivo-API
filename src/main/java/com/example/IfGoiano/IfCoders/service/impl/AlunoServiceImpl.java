package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoMapper;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;

import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import com.example.IfGoiano.IfCoders.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoMapper mapper;

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
    public AlunoOutputDTO save(AlunoInputDTO aluno) {
         alunoRepository.save(mapper.toAlunoEntity(aluno));
         return findById(aluno.getId());
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
}
