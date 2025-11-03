package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTOCreated;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.InterpreteMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;

@Service
public class CreateLibras {
    @Autowired
    private LibrasRepository librasRepository;

    @Autowired
    private InterpreteRepository interpreteRepository;

    @Autowired
    private InterpreteMapper interpreteMapper;

    @Autowired
    LibrasMapper mapper;

    @Autowired
    private UploadFiles uploadFiles;


    @Transactional
    public LibrasOutputDTO createLibras(LibrasInputDTOCreated librasInputDTO, Long idInterprete) throws IOException {
        var interprete = this.interpreteRepository.findById(idInterprete).orElseThrow(() -> new ResourceNotFoundException("Interprete not found"));
        LibrasEntity librasEntity = mapper.toLibrasEntity(librasInputDTO);

        // Define status como EM_ANALISE por padrão
        librasEntity.setStatus(Status.EMANALISE);

        interprete.getLibras().add(librasEntity);
        librasEntity.getInterprete().add(interprete);

        this.librasRepository.save(librasEntity);
        this.interpreteRepository.save(interprete);
        return mapper.toLibrasOutputDTO(librasEntity);
    }
}
