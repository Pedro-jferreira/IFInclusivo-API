package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ProfessorMapper;
import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;

import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ProfessorRepository;
import com.example.IfGoiano.IfCoders.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    ProfessorMapper professorMapper;

    public ProfessorOutputDTO save(ProfessorInputDTO professorInput) {
        var professor = this.professorMapper.toProfessorEntity(professorInput);
        this.professorRepository.save(professor);

        return this.professorMapper.toProfessorOutputDTO(professor);
    }

    public List<ProfessorOutputDTO> findAll() {
        List<ProfessorOutputDTO> listProfessorOutputDTO = new ArrayList<>();

        this.professorRepository.findAll().forEach(professor -> listProfessorOutputDTO.add(professorMapper.toProfessorOutputDTO(professor))); // adicionar exeção caso esteja vazia

        return listProfessorOutputDTO;
    }


    public ProfessorOutputDTO findById(Long id) {
        var profesor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return this.professorMapper.toProfessorOutputDTO(profesor);
    }

    public void delete(Long id) {
        var profe = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found"));

        this.professorRepository.deleteById(id);
    }




    public ProfessorOutputDTO update(ProfessorInputDTO professor, Long id) {
        var profe = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found"));

        this.updateProfessor(profe, professor);
        this.professorRepository.save(profe);
      return this.professorMapper.toProfessorOutputDTO(profe);

    }

    public void updateProfessor(ProfessorEntity professor, ProfessorInputDTO professorInput){
        professor.setId(professorInput.getId());
        professor.setNome(professorInput.getNome());
        professor.setBiografia(professorInput.getBiografia());
        //professor.setComentarios(professorInput.getComentarios());
        professor.setFormacao(professorInput.getFormacao());
        professor.setMatricula(professorInput.getMatricula());
        professor.setLogin(professorInput.getLogin());
        professor.setSenha(professorInput.getSenha());
    }
}