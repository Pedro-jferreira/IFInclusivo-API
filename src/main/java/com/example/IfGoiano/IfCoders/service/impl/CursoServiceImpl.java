package com.example.IfGoiano.IfCoders.service.impl;


import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.CursoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.CursoMapper;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.CursoRepository;
import com.example.IfGoiano.IfCoders.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    CursoMapper mapper;

    @Override
    public List<CursoOutputDTO> findAll() {
        return cursoRepository.findAll().stream().map(mapper::toCursoOutputDTO).collect(Collectors.toList());
    }

    @Override
    public CursoOutputDTO findById(Long id) {
        var curso = cursoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        return mapper.toCursoOutputDTO(curso);
    }

    @Override
    @Transactional
    public CursoOutputDTO save(CursoInputDTO cursoEntity) {
         return findById(cursoRepository.save(mapper.toCursoEntity(cursoEntity)).getId());
    }

    @Override
    @Transactional
    public CursoOutputDTO update(Long id, CursoInputDTO cursoEntityDetails) {
        var cursoEntity = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        mapper.updateCursoEntiryFromDTO(cursoEntityDetails, cursoEntity);
        return mapper.toCursoOutputDTO(cursoRepository.save(cursoEntity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cursoRepository.delete(mapper.toCursoEntity(findById(id)));
    }
}
