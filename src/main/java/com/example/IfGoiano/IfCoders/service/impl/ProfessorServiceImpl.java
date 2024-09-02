package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ProfessorMapper;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ProfessorRepository;
import com.example.IfGoiano.IfCoders.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    ProfessorMapper professorMapper;

    @Override
    public List<ProfessorOutputDTO> findAll() {
        List<ProfessorOutputDTO> listProfessorOutputDTO = new ArrayList<>();
        this.professorRepository.findAll().forEach(professor -> listProfessorOutputDTO.add(professorMapper.toProfessorOutputDTO(professor))); // adicionar exeção caso esteja vazia

        return listProfessorOutputDTO;
    }

    @Override
    public ProfessorOutputDTO findById(Long id) {
        var profesor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        return this.professorMapper.toProfessorOutputDTO(profesor);
    }

    @Override
    @Transactional
    public ProfessorOutputDTO save(ProfessorInputDTO professorInput) {
       return findById(professorRepository.save(professorMapper.toProfessorEntity(professorInput)).getId());
    }

    @Override
    @Transactional
    public ProfessorOutputDTO update(ProfessorInputDTO professor, Long id) {
        var profe = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor not found"));
        professorMapper.updateProfessorEntityFromDTO(professor,profe);
        this.professorRepository.save(profe);

        return this.professorMapper.toProfessorOutputDTO(profe);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var profe = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor not found"));

        this.professorRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return professorRepository.existsById(id);
    }
}