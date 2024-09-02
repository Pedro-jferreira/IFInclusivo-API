package com.example.IfGoiano.IfCoders.service.impl;


import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.InterpreteMapper;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;
import com.example.IfGoiano.IfCoders.service.InterpreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class InterpreteServiceImpl implements InterpreteService {

    @Autowired
    InterpreteRepository interpreteRepository;

    @Autowired
    InterpreteMapper interpreteMapper;

    @Override
    public List<InterpreteOutputDTO> findAll() {
        List<InterpreteOutputDTO> listIntrepretes = new ArrayList<>();
        this.interpreteRepository.findAll().stream().forEach(interpreteEntity -> listIntrepretes.add(interpreteMapper.toInterpreteOutputDTO(interpreteEntity)));

        return listIntrepretes;
    }

    @Override
    public InterpreteOutputDTO findById(Long id) {
        var interprete = interpreteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));

        return interpreteMapper.toInterpreteOutputDTO(interprete);
    }

    @Override
    @Transactional
    public InterpreteOutputDTO save(InterpreteInputDTO interpreteInputDTO) {
        return findById(interpreteRepository.save(interpreteMapper.toInterpreteEntity(interpreteInputDTO)).getId());
    }

    @Override
    @Transactional
    public InterpreteOutputDTO update(InterpreteInputDTO interpreteInputDTO, Long id) {
        var interprete = interpreteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Interprete not found Id :"+ id));
        interpreteMapper.updateInterpreteEntityFromDTO(interpreteInputDTO, interprete);

        return interpreteMapper.toInterpreteOutputDTO(interpreteRepository.save(interprete));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var interprete = interpreteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));
        interpreteRepository.delete(interprete);
    }

    @Override
    public boolean existsById(Long id) {
        return interpreteRepository.existsById(id);
    }
}
