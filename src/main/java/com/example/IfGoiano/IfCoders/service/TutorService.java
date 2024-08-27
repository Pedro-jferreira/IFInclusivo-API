package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TutorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TutorOutputDTO;

import java.util.List;

public interface TutorService {

    List<TutorOutputDTO> findAll();

    TutorOutputDTO findById(Long id);

    TutorOutputDTO save(TutorInputDTO tutorInputDTO);

    TutorOutputDTO update(TutorInputDTO tutorInputDTO, Long id);

    void delete(Long id);

}
