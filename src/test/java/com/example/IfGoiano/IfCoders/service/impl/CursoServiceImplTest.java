package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.CursoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.CursoMapper;
import com.example.IfGoiano.IfCoders.entity.CursoEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CursoServiceImplTest {

    @InjectMocks
    private CursoServiceImpl service;

    @Mock
    private CursoRepository repository;

    @Mock
    private CursoMapper mapper;

    private CursoEntity mockEntity;
    private CursoInputDTO mockInputDTO;
    private CursoOutputDTO mockOutputDTO;

    @BeforeEach
    void setUp() {

        mockEntity = new CursoEntity();
        mockEntity.setId(1L);
        mockEntity.setNome("Ciência da Computação");

        mockInputDTO = new CursoInputDTO();
        mockInputDTO.setNome("Ciência da Computação");

        mockOutputDTO = new CursoOutputDTO();
        mockOutputDTO.setId(1L);
        mockOutputDTO.setNome("Ciência da Computação");
    }

    @Test
    @DisplayName("Deve retornar lista de todos os cursos")
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(mockEntity));
        when(mapper.toCursoOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        List<CursoOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findAll();
        verify(mapper).toCursoOutputDTO(mockEntity);
    }

    @Test
    @DisplayName("Deve retornar lista vazia se não houver cursos")
    void findAll_ShouldReturnEmptyList_WhenNoData() {
        when(repository.findAll()).thenReturn(List.of());

        List<CursoOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findAll();
        verify(mapper, never()).toCursoOutputDTO(any());
    }

    @Test
    @DisplayName("Deve buscar curso por ID com sucesso")
    void findById_ShouldReturnCurso_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toCursoOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        CursoOutputDTO result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao buscar ID inexistente")
    void findById_ShouldThrowException_WhenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(99L);
        });
    }

    @Test
    @DisplayName("Deve salvar novo curso com sucesso")
    void save_ShouldSaveCurso_WhenValid() {

        when(mapper.toCursoEntity(mockInputDTO)).thenReturn(mockEntity);
        when(repository.save(mockEntity)).thenReturn(mockEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toCursoOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        CursoOutputDTO result = service.save(mockInputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).save(mockEntity);
    }

    @Test
    @DisplayName("Deve atualizar curso com sucesso")
    void update_ShouldUpdateCurso_WhenExists() {
        Long id = 1L;
        CursoInputDTO updateDTO = new CursoInputDTO();
        updateDTO.setNome("Engenharia de Software");

        when(repository.findById(id)).thenReturn(Optional.of(mockEntity));

        doNothing().when(mapper).updateCursoEntiryFromDTO(updateDTO, mockEntity);
        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(mapper.toCursoOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        CursoOutputDTO result = service.update(id, updateDTO);

        assertNotNull(result);
        verify(repository).findById(id);
        verify(mapper).updateCursoEntiryFromDTO(updateDTO, mockEntity);
        verify(repository).save(mockEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar ID inexistente")
    void update_ShouldThrowException_WhenNotFound() {
        Long id = 99L;
        CursoInputDTO updateDTO = new CursoInputDTO();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(id, updateDTO);
        });

        verify(mapper, never()).updateCursoEntiryFromDTO(any(), any());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar curso por ID com sucesso")
    void delete_ShouldDelete_WhenExists() {

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toCursoOutputDTO(mockEntity)).thenReturn(mockOutputDTO);
        when(mapper.toCursoEntity(mockOutputDTO)).thenReturn(mockEntity);

        service.delete(1L);

        verify(repository).delete(mockEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao deletar ID inexistente")
    void delete_ShouldThrowException_WhenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(99L);
        });

        verify(repository, never()).delete(any());
    }
}