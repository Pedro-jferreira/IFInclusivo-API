package com.example.IfGoiano.IfCoders.service.impl;


import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.entity.CursoEntity;
import com.example.IfGoiano.IfCoders.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoEntity> findAll() {
        try {
            return cursoRepository.findAll();
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all cursos: " + e);
        }
    }

    public CursoEntity findById(Long id) {
        try {
            Optional<CursoEntity> curso = cursoRepository.findById(id);
            return curso.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching curso: " + e);
        }
    }

    public CursoEntity save(CursoEntity cursoEntity) {
        try {
            return cursoRepository.save(cursoEntity);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new cursoEntity: " + e);
        }
    }

    public CursoEntity update(Long id, CursoEntity cursoEntityDetails) {
        try {
            CursoEntity cursoEntity = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateCursoDetails(cursoEntity, cursoEntityDetails);
            return cursoRepository.save(cursoEntity);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the curso: " + e);
        }
    }

    public void delete(Long id) {
        try {
            CursoEntity cursoEntity = findById(id);
            cursoRepository.delete(cursoEntity);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the curso: " + e);
        }
    }

    public void updateCursoDetails (CursoEntity cursoEntity, CursoEntity cursoEntityDetails){
        cursoEntity.setNome(cursoEntityDetails.getNome());
    }
}
