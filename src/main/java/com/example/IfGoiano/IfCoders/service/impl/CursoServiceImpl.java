package com.example.IfGoiano.IfCoders.service.impl;


import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.entity.CursoEntity;
import com.example.IfGoiano.IfCoders.repository.CursoRepository;
import com.example.IfGoiano.IfCoders.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoEntity> findAll() {
        return cursoRepository.findAll();
    }

    public CursoEntity findById(Long id) {
        Optional<CursoEntity> curso = cursoRepository.findById(id);
        return curso.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public CursoEntity save(CursoEntity curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public CursoEntity update(Long id, CursoEntity cursoDetails) {
        CursoEntity curso = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        updateCursoDetails(curso, cursoDetails);
        return cursoRepository.save(curso);
    }

    @Transactional
    public void delete(Long id) {
        CursoEntity curso = findById(id);
        cursoRepository.delete(curso);
    }

    private void updateCursoDetails (CursoEntity curso, CursoEntity cursoDetails) {
        curso.setNome(cursoDetails.getNome());
    }
}
