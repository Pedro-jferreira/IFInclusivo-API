package com.example.IfGoiano.IfCoders.service;


import com.example.IfGoiano.IfCoders.model.Curso;
import com.example.IfGoiano.IfCoders.model.Usuario;
import com.example.IfGoiano.IfCoders.repository.CursoRepository;
import com.example.IfGoiano.IfCoders.service.Exception.DataBaseException;
import com.example.IfGoiano.IfCoders.service.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Optional;

public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findAll() {
        try {
            return cursoRepository.findAll();
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all cursos: " + e);
        }
    }

    public Curso findById(Long id) {
        try {
            Optional<Curso> curso = cursoRepository.findById(id);
            return curso.orElseThrow(() -> new ResourceNotFoundException(id));
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching curso: " + e);
        }
    }

    public Curso save(Curso curso) {
        try {
            return cursoRepository.save(curso);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new curso: " + e);
        }
    }

    public Curso update(Long id, Curso cursoDetails) {
        try {
            Curso curso = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            updateCursoDetails(curso, cursoDetails);
            return cursoRepository.save(curso);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the curso: " + e);
        }
    }

    public void delete(Long id) {
        try {
            Curso curso = findById(id);
            cursoRepository.delete(curso);
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the curso: " + e);
        }
    }

    public void updateCursoDetails (Curso curso, Curso cursoDetails){
        curso.setNome(cursoDetails.getNome());
    }
}
