package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.RequestAnalisePalavra;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.exception.BadRequestException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class AnalisarLibrasTest {

    @InjectMocks
    private AnalisarLibras analisarLibras;

    @Mock
    private LibrasRepository librasRepository;

    @Mock
    private LibrasMapper librasMapper;

    @Mock
    private InterpreteRepository interpreteRepository;

    private RequestAnalisePalavra requestAnalisePalavra;
    private LibrasEntity librasEntity;
    private InterpreteEntity interpreteEntity;
    private LibrasOutputDTO librasOutputDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        requestAnalisePalavra = new RequestAnalisePalavra();
        requestAnalisePalavra.setPalavra("teste");
        requestAnalisePalavra.setStatus(Status.APROVADO);
        requestAnalisePalavra.setJustificativa("Justificação Teste");
        requestAnalisePalavra.setCategorias(Categorias.PROGRAMACAO);
        requestAnalisePalavra.setUrl("http://url.com");
        requestAnalisePalavra.setFoto("foto.jpg");
        requestAnalisePalavra.setVideo("video.mp4");

        librasEntity = new LibrasEntity();
        librasEntity.setPalavra("teste");
        librasEntity.setStatus(Status.EMANALISE);
        librasEntity.setInterprete(new ArrayList<>());

        interpreteEntity = new InterpreteEntity();
        interpreteEntity.setId(1L);
        interpreteEntity.setLibras(new ArrayList<>());

        librasOutputDTO = new LibrasOutputDTO();
        librasOutputDTO.setPalavra("teste");
    }

    @Test
    void analisarPalavraTest_Success() {
        when(librasRepository.findByPalavra("teste")).thenReturn(Optional.of(librasEntity));
        when(interpreteRepository.findById(1L)).thenReturn(Optional.of(interpreteEntity));
        when(librasMapper.toLibrasOutputDTO(librasEntity)).thenReturn(librasOutputDTO);


        LibrasOutputDTO result = analisarLibras.analisarPalavra(requestAnalisePalavra, 1L);


        verify(librasRepository, times(1)).findByPalavra("teste");
        verify(interpreteRepository, times(1)).findById(1L);
        verify(librasRepository, times(1)).save(librasEntity);
        verify(interpreteRepository, times(1)).save(interpreteEntity);
        verify(librasMapper, times(1)).toLibrasOutputDTO(librasEntity);

        assertNotNull(result);
        assertEquals("teste", result.getPalavra());
    }

    @Test
    void analisarPalavraTest_InterpreteNotFound() {
        when(librasRepository.findByPalavra("teste")).thenReturn(Optional.of(librasEntity));
        when(interpreteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            analisarLibras.analisarPalavra(requestAnalisePalavra, 1L);
        });

        verify(interpreteRepository, times(1)).findById(1L);
    }

    @Test
    void analisarPalavraTest_BadRequestException() {
        librasEntity.setStatus(Status.APROVADO); // Alterando o status para simular erro
        when(librasRepository.findByPalavra("teste")).thenReturn(Optional.of(librasEntity));
        when(interpreteRepository.findById(1L)).thenReturn(Optional.of(interpreteEntity));

        assertThrows(BadRequestException.class, () -> {
            analisarLibras.analisarPalavra(requestAnalisePalavra, 1L);
        });

        verify(librasRepository, times(1)).findByPalavra("teste");
    }
}

