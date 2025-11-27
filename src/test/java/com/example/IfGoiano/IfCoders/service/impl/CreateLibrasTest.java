package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTOCreated;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.InterpreteMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateLibrasTest {

    @InjectMocks
    private CreateLibras createLibras;

    @Mock
    private LibrasRepository librasRepository;

    @Mock
    private InterpreteRepository interpreteRepository;

    @Mock
    private LibrasMapper librasMapper;

    @Mock
    private InterpreteMapper interpreteMapper;

    @Mock
    private UploadFiles uploadFiles;

    private LibrasInputDTOCreated mockInputDTO;
    private LibrasEntity mockLibrasEntity;
    private InterpreteEntity mockInterpreteEntity;
    private LibrasOutputDTO mockOutputDTO;

    @BeforeEach
    void setUp() {

        mockInputDTO = new LibrasInputDTOCreated();
        mockInputDTO.setPalavra("Teste");
        mockInputDTO.setDescricao("Descrição Teste");

        mockLibrasEntity = new LibrasEntity();
        mockLibrasEntity.setId(1L);
        mockLibrasEntity.setPalavra("Teste");

        mockLibrasEntity.setInterprete(new ArrayList<>());

        mockInterpreteEntity = new InterpreteEntity();
        mockInterpreteEntity.setId(10L);
        mockInterpreteEntity.setNome("Intérprete Teste");

        mockInterpreteEntity.setLibras(new ArrayList<>());

        mockOutputDTO = new LibrasOutputDTO();
        mockOutputDTO.setId(1L);
        mockOutputDTO.setPalavra("Teste");
        mockOutputDTO.setStatus(Status.EMANALISE);
    }

    @Test
    @DisplayName("Deve criar Libras com sucesso e associar ao Intérprete")
    void createLibras_ShouldCreateSuccessfully_WhenInterpreteExists() throws IOException {

        Long idInterprete = 10L;

        when(interpreteRepository.findById(idInterprete)).thenReturn(Optional.of(mockInterpreteEntity));
        when(librasMapper.toLibrasEntity(mockInputDTO)).thenReturn(mockLibrasEntity);
        when(librasMapper.toLibrasOutputDTO(mockLibrasEntity)).thenReturn(mockOutputDTO);

        LibrasOutputDTO result = createLibras.createLibras(mockInputDTO, idInterprete);

        assertNotNull(result);
        assertEquals(Status.EMANALISE, result.getStatus());

        assertTrue(mockInterpreteEntity.getLibras().contains(mockLibrasEntity), "A lista de libras do intérprete deve conter a nova libra");
        assertTrue(mockLibrasEntity.getInterprete().contains(mockInterpreteEntity), "A lista de intérpretes da libra deve conter o intérprete");

        verify(librasRepository).save(mockLibrasEntity);
        verify(interpreteRepository).save(mockInterpreteEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException se o Intérprete não existir")
    void createLibras_ShouldThrowException_WhenInterpreteNotFound() {

        Long idInterprete = 99L;
        when(interpreteRepository.findById(idInterprete)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            createLibras.createLibras(mockInputDTO, idInterprete);
        });

        verify(librasRepository, never()).save(any());
        verify(interpreteRepository, never()).save(any());
    }
}