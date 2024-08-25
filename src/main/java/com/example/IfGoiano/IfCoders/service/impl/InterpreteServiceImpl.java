package com.example.IfGoiano.IfCoders.service.impl;


import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.InterpreteMapper;
import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;

import com.example.IfGoiano.IfCoders.service.InterpreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterpreteServiceImpl implements InterpreteService {

    @Autowired
    InterpreteRepository interpreteRepository;

    @Autowired
    InterpreteMapper interpreteMapper;


    public InterpreteOutputDTO save(InterpreteInputDTO interpreteInputDTO) {
        var entity = interpreteMapper.toInterpreteEntity(interpreteInputDTO);

        return interpreteMapper.toInterpreteOutputDTO(interpreteRepository.save(entity));
    }

    public InterpreteOutputDTO findById(Long id) {
        var interprete = interpreteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));

        return interpreteMapper.toInterpreteOutputDTO(interprete);
    }

    public InterpreteOutputDTO update(InterpreteInputDTO interpreteInputDTO, Long id) {
        var interprete = interpreteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interprete not found Id :"+ id));


        updateIntrepreDetails(interprete, interpreteInputDTO);


        return interpreteMapper.toInterpreteOutputDTO(interpreteRepository.save(interprete));
    }

    public List<InterpreteOutputDTO> findAll() {
        List<InterpreteOutputDTO> listIntrepretes = new ArrayList<>();

        this.interpreteRepository.findAll().stream().
                forEach(interpreteEntity -> listIntrepretes.add(interpreteMapper.toInterpreteOutputDTO(interpreteEntity)));

        return listIntrepretes;
    }

    public void delete(Long id) {
        var interprete = interpreteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));

        interpreteRepository.delete(interprete);
    }


    public void updateIntrepreDetails(InterpreteEntity interprete, InterpreteInputDTO interpreteOutputDTO) {
        interprete.setId(interpreteOutputDTO.getId());
        // interprete.setLibras(interpreteOutputDTO.getLibras());
        interprete.setBiografia(interpreteOutputDTO.getBiografia());
        interprete.setSalary(interpreteOutputDTO.getSalary());
        interprete.setEspecialidade(interpreteOutputDTO.getEspecialidade());
        //interprete.setComentarios(interpreteOutputDTO.getComentarios());
        interprete.setLogin(interpreteOutputDTO.getLogin());
        interprete.setMatricula(interpreteOutputDTO.getMatricula());
        interprete.setNome(interpreteOutputDTO.getNome());
        interprete.setSenha(interpreteOutputDTO.getSenha());
    }

}
