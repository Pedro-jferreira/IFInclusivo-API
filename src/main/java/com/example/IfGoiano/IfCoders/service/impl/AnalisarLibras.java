package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.RequestAnalisePalavra;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.InterpreteMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.exception.BadRequestException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnalisarLibras {


    @Autowired
    private LibrasServiceImpl librasService;

    @Autowired
    private LibrasMapper librasMapper;

    @Autowired
    private InterpreteRepository interpreteRepository;

    @Autowired
    private InterpreteMapper interpreteMapper;

    @Transactional
    public LibrasOutputDTO analisarPalavra(RequestAnalisePalavra requestAnalisePalavra, Long idInterprete) {
        var libras = this.librasService.findByPalavra(requestAnalisePalavra.getPalavra());

        var interpreteAnalise = this.interpreteRepository.findById(idInterprete).orElseThrow(
                () -> new ResourceNotFoundException("Interprete not found"));

        if (libras.getStatus() != Status.EMANALISE) {
            throw new BadRequestException("Esssa palavra ja esta aprovada");
        }

        libras.setStatus(requestAnalisePalavra.getStatus());
        libras.setJustificativa(requestAnalisePalavra.getJustificativa());
        libras.setCategorias(requestAnalisePalavra.getCategorias());
        libras.setUrl(requestAnalisePalavra.getUrl());
        libras.setFoto(requestAnalisePalavra.getFoto());
        libras.setVideo(requestAnalisePalavra.getVideo());
        libras.getInterprete().add(interpreteMapper.toSimpleInterpreteDTO(interpreteAnalise));

        return  librasMapper.toLibrasOutputDTO(librasService.mapper.toLibrasEntity(libras));


    }
}
