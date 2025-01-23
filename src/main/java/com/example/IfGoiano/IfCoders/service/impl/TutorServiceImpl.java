package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TutorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TutorOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.TutorMapper;
import com.example.IfGoiano.IfCoders.entity.TutorEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.TutorRepository;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import com.example.IfGoiano.IfCoders.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private TutorMapper tutorMapper;


    @Override
    public List<TutorOutputDTO> findAll(){
        List<TutorOutputDTO> list = new ArrayList<>();

        this.tutorRepository.findAll().stream().forEach(tutor -> list.add(tutorMapper.toTutorOutputDTO(tutor)));
        return list;
    }

    @Override
    public TutorOutputDTO findById(Long id){
        var tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor not found"));

        return tutorMapper.toTutorOutputDTO(tutor);
    }

    @Override
    @Transactional
    public TutorOutputDTO save(TutorInputDTO tutorDTO) {
        TutorEntity t = tutorMapper.toTutorEntity(tutorDTO);
        return findById(tutorRepository.save(t).getId());

    }

    @Override
    @Transactional
    public TutorOutputDTO update(TutorInputDTO tutorDTO, Long id) {
        var tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor not found"));

       tutorMapper.updateTutorEntityFromDTO(tutorDTO,tutor);

       tutorRepository.save(tutor);

       return tutorMapper.toTutorOutputDTO(tutor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.tutorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tutor not found Id: " + id));
        tutorRepository.deleteById(id);

    }

    @Override
    public boolean existsById(Long id) {
        return tutorRepository.existsById(id);
    }
}
