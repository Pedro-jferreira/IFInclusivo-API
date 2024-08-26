package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoMapper;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;

import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoMapper mapper;

    public List<AlunoOutputDTO> findAll() {
        try {
            return alunoRepository.findAll().stream().map(mapper::toAlunoOutputDTO).collect(Collectors.toList());
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all alunos: " + e);
        }
    }

    public AlunoOutputDTO findById(Long id) {
        try {
            var aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            return mapper.toAlunoOutputDTO(aluno);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching user: " + e);
        }
    }

    @Transactional
    public AlunoOutputDTO save(AlunoInputDTO aluno) {
        try {
             alunoRepository.save(mapper.toAlunoEntity(aluno));
             return findById(aluno.getId());
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new aluno: " + e);
        }
    }

    @Transactional
    public AlunoOutputDTO update(Long id, AlunoInputDTO alunoDetails) {
        try {
            var aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            mapper.updateAlunoEntityFromDTO( alunoDetails,aluno);
            return mapper.toAlunoOutputDTO(alunoRepository.save(aluno));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the aluno: " + e);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            alunoRepository.delete(mapper.toAlunoEntity(findById(id)));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the aluno: " + e);
        }
    }


}
