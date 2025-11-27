package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.ProfessorUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ProfessorOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.ProfessorMapper;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ProfessorRepository;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
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
class ProfessorServiceImplTest {

    @InjectMocks
    private ProfessorServiceImpl service;

    @Mock
    private ProfessorRepository repository;

    @Mock
    private ProfessorMapper mapper;

    @Mock
    private ConfigAcessibilidadeService configAcessibilidadeService;

    @Mock
    private ConfigAcblMapper configAcblMapper;

    private ProfessorEntity mockEntity;
    private ProfessorInputDTO mockInputDTO;
    private ProfessorOutputDTO mockOutputDTO;
    private ConfigAcblOutputDTO mockConfigDTO;
    private ConfigAcessibilidadeEntity mockConfigEntity;

    @BeforeEach
    void setUp() {

        mockEntity = new ProfessorEntity();
        mockEntity.setId(1L);
        mockEntity.setNome("Professor Teste");
        mockEntity.setLogin("prof.teste");
        mockEntity.setFormacao("Mestrado");

        mockInputDTO = new ProfessorInputDTO();
        mockInputDTO.setNome("Professor Teste");
        mockInputDTO.setFormacao("Mestrado");

        mockOutputDTO = new ProfessorOutputDTO();
        mockOutputDTO.setId(1L);
        mockOutputDTO.setNome("Professor Teste");

        mockConfigDTO = new ConfigAcblOutputDTO();
        mockConfigDTO.setId(10L);
        mockConfigEntity = new ConfigAcessibilidadeEntity();
        mockConfigEntity.setId(10L);
    }

    @Test
    @DisplayName("Deve retornar lista de todos os professores")
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(mockEntity));
        when(mapper.toProfessorOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        List<ProfessorOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Deve buscar professor por ID com sucesso")
    void findById_ShouldReturnProfessor_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toProfessorOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        ProfessorOutputDTO result = service.findById(1L);

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
    @DisplayName("Deve salvar novo professor com sucesso")
    void save_ShouldSaveProfessor_WhenValid() {
        Long idConfigAc = 10L;

        when(configAcessibilidadeService.findById(idConfigAc)).thenReturn(mockConfigDTO);
        when(mapper.toProfessorEntity(mockInputDTO)).thenReturn(mockEntity);
        when(configAcblMapper.toConfigAcblEntity(mockConfigDTO)).thenReturn(mockConfigEntity);
        when(repository.save(mockEntity)).thenReturn(mockEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toProfessorOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        ProfessorOutputDTO result = service.save(mockInputDTO, idConfigAc);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(repository).save(mockEntity);

        assertEquals(mockConfigEntity, mockEntity.getConfigAcessibilidadeEntity());
    }

    @Test
    @DisplayName("Deve atualizar professor com sucesso")
    void update_ShouldUpdateProfessor_WhenUserExists() {
        String username = "prof.teste";
        ProfessorUpdateDTO updateDTO = new ProfessorUpdateDTO();
        updateDTO.setNome("Nome Atualizado");

        when(repository.findByLogin(username)).thenReturn(Optional.of(mockEntity));
        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(mapper.toProfessorOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        ProfessorOutputDTO result = service.update(updateDTO, username);

        assertNotNull(result);
        verify(repository).findByLogin(username);
        verify(mapper).updateProfessorEntityFromDTO(updateDTO, mockEntity);
        verify(repository).save(mockEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar usuário inexistente")
    void update_ShouldThrowException_WhenUserNotFound() {
        String username = "fantasma";
        ProfessorUpdateDTO updateDTO = new ProfessorUpdateDTO();

        when(repository.findByLogin(username)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(updateDTO, username);
        });

        verify(mapper, never()).updateProfessorEntityFromDTO(any(), any());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar professor por ID com sucesso")
    void delete_ShouldDelete_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));

        service.delete(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao deletar ID inexistente")
    void delete_ShouldThrowException_WhenNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(99L);
        });

        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve verificar se existe por ID")
    void existsById_ShouldReturnTrue_WhenExists() {
        when(repository.existsById(1L)).thenReturn(true);
        assertTrue(service.existsById(1L));
    }
}