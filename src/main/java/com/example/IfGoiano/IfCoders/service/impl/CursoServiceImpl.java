package com.example.IfGoiano.IfCoders.service.impl;


import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.CursoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.CursoMapper;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;

import com.example.IfGoiano.IfCoders.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    CursoMapper mapper;

    public List<CursoOutputDTO> findAll() {
        try {
            return cursoRepository.findAll().stream().map(mapper::toCursoOutputDTO).collect(Collectors.toList());
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all cursos: " + e);
        }
    }

    public CursoOutputDTO findById(Long id) {
        try {
            var curso = cursoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
            return mapper.toCursoOutputDTO(curso);

        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching curso: " + e);
        }
    }

    public CursoOutputDTO save(CursoInputDTO cursoEntity) {
        try {
             cursoRepository.save(mapper.toCursoEntity(cursoEntity));
             return findById(cursoEntity.getId());
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new cursoEntity: " + e);
        }
    }

    public CursoOutputDTO update(Long id, CursoInputDTO cursoEntityDetails) {
        try {
            var cursoEntity = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            mapper.updateCursoEntiryFromDTO(cursoEntityDetails, cursoEntity);
            return mapper.toCursoOutputDTO(cursoRepository.save(cursoEntity));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the curso: " + e);
        }
    }

    public void delete(Long id) {
        try {
            cursoRepository.delete(mapper.toCursoEntity(findById(id)));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the curso: " + e);
        }
    }


}
