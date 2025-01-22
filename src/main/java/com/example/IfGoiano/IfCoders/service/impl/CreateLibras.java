package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.InterpreteMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateLibras {
    @Autowired
    private LibrasRepository librasRepository;

    @Autowired
    private InterpreteServiceImpl interpreteServiceImpl;

    @Autowired
    private InterpreteMapper interpreteMapper;

    @Autowired
    LibrasMapper mapper;


    public LibrasOutputDTO createLibras(LibrasInputDTO librasInputDTO, Long idInterprete) {
        var interprete = this.interpreteServiceImpl.findById(idInterprete);//No interprete service ja lança uma exeção

        LibrasEntity librasEntity = mapper.toLibrasEntity(librasInputDTO);
        librasEntity.getInterprete().add(interpreteMapper.toInterpreteEntity(interprete));
        this.librasRepository.save(librasEntity);
        return mapper.toLibrasOutputDTO(librasEntity);
    }
}
