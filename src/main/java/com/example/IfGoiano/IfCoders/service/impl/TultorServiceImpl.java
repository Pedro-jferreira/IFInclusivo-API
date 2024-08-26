package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TutorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TutorOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.TutorMapper;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.TutorRepository;
import com.example.IfGoiano.IfCoders.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class TultorServiceImpl implements TutorService {

    @Autowired
    private TutorRepository tultorRepository;

    @Autowired
    private TutorMapper tutorMapper;


    public TutorOutputDTO save(TutorInputDTO tultorDTO) {
        var tutor = tutorMapper.toTutorEntity(tultorDTO);
        tutorMapper.toTutorOutputDTO(tultorRepository.save(tutor));

        this.tultorRepository.save(tutor);

        return tutorMapper.toTutorOutputDTO(tutor);

    }

    public TutorOutputDTO findById(Long id){
        var tultor = tultorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tultor not found"));

        return tutorMapper.toTutorOutputDTO(tultor);
    }

    public List<TutorOutputDTO> findAll(){
        List<TutorOutputDTO> list = new ArrayList<>();

        this.tultorRepository.findAll().stream().forEach(tultor -> list.add(tutorMapper.toTutorOutputDTO(tultor)));
        return list;
    }

    public TutorOutputDTO update(TutorInputDTO tutorDTO, Long id) {
        var tultor = tultorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tultor not found"));

       tutorMapper.updateTutorEntityFromDTO(tutorDTO,tultor);

       tultorRepository.save(tultor);

       return tutorMapper.toTutorOutputDTO(tultor);
    }

    public void delete(Long id) {
        this.tultorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tultor not found Id: " + id));
        tultorRepository.deleteById(id);

    }


}
