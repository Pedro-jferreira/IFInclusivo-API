package com.example.IfGoiano.IfCoders.service.impl;


import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.RequestAnalisePalavra;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.InterpreteMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import com.example.IfGoiano.IfCoders.service.InterpreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    ConfigAcessibilidadeService configAcessibilidadeService;
    @Autowired
    ConfigAcblMapper configAcblMapper;

    @Autowired
    LibrasRepository librasRepository;

    @Autowired
    LibrasMapper mapper;

    @Override
    public List<InterpreteOutputDTO> findAll() {
        List<InterpreteOutputDTO> listIntrepretes = new ArrayList<>();
        this.interpreteRepository.findAll().stream().forEach(interpreteEntity -> listIntrepretes.add(interpreteMapper.toInterpreteOutputDTO(interpreteEntity)));

        return listIntrepretes;
    }

    @Override
    @Transactional
    public InterpreteOutputDTO findById(Long id) {
        var interprete = interpreteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));

        return interpreteMapper.toInterpreteOutputDTO(interprete);
    }

    @Override
    @Transactional
    public InterpreteOutputDTO save(InterpreteInputDTO interpreteInputDTO, Long idConfigAc) {
        var acessibilidade = configAcessibilidadeService.findById(idConfigAc);
        InterpreteEntity i = interpreteMapper.toInterpreteEntity(interpreteInputDTO);
        i.setConfigAcessibilidadeEntity(configAcblMapper.toConfigAcblEntity(acessibilidade));
        return findById(interpreteRepository.save(i).getId());
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
    @Transactional
    public Page<LibrasOutputDTO> historicoLibrasSugeridas(Pageable pageable){
       return this.librasRepository.findByStatus(Status.EMANALISE, pageable).map(mapper::toLibrasOutputDTO);
    }

    @Override
    public LibrasOutputDTO analisarPalavra(RequestAnalisePalavra requestAnalisePalavra, Long idInterprete) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return interpreteRepository.existsById(id);
    }
}
