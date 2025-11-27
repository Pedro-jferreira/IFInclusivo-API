package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.AlunoUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
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
class AlunoServiceImplTest {

    @InjectMocks
    private AlunoServiceImpl service;

    @Mock
    private AlunoRepository repository;

    @Mock
    private AlunoMapper mapper;

    @Mock
    private ConfigAcessibilidadeService configAcessibilidadeService;

    @Mock
    private ConfigAcblMapper configAcblMapper;

    private AlunoEntity mockEntity;
    private AlunoInputDTO mockInputDTO;
    private AlunoOutputDTO mockOutputDTO;
    private ConfigAcblOutputDTO mockConfigDTO;
    private ConfigAcessibilidadeEntity mockConfigEntity;

    @BeforeEach
    void setUp() {

        mockEntity = new AlunoEntity();
        mockEntity.setId(1L);
        mockEntity.setNome("Aluno Teste");
        mockEntity.setLogin("aluno.teste");

        mockInputDTO = new AlunoInputDTO();
        mockInputDTO.setNome("Aluno Teste");

        mockOutputDTO = new AlunoOutputDTO();
        mockOutputDTO.setId(1L);
        mockOutputDTO.setNome("Aluno Teste");

        mockConfigDTO = new ConfigAcblOutputDTO();
        mockConfigDTO.setId(10L);
        mockConfigEntity = new ConfigAcessibilidadeEntity();
        mockConfigEntity.setId(10L);
    }

    @Test
    @DisplayName("Deve retornar lista de todos os alunos")
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(mockEntity));
        when(mapper.toAlunoOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        List<AlunoOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Deve buscar aluno por ID com sucesso")
    void findById_ShouldReturnAluno_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toAlunoOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        AlunoOutputDTO result = service.findById(1L);

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
    @DisplayName("Deve salvar novo aluno com sucesso")
    void save_ShouldSaveAluno_WhenValid() {
        Long idConfigAc = 10L;

        when(configAcessibilidadeService.findById(idConfigAc)).thenReturn(mockConfigDTO);
        when(mapper.toAlunoEntity(mockInputDTO)).thenReturn(mockEntity);
        when(configAcblMapper.toConfigAcblEntity(mockConfigDTO)).thenReturn(mockConfigEntity);
        when(repository.save(mockEntity)).thenReturn(mockEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toAlunoOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        AlunoOutputDTO result = service.save(mockInputDTO, idConfigAc);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(repository).save(mockEntity);

        assertEquals(mockConfigEntity, mockEntity.getConfigAcessibilidadeEntity());
    }

    @Test
    @DisplayName("Deve atualizar aluno com sucesso")
    void update_ShouldUpdateAluno_WhenUserExists() {
        String username = "aluno.teste";
        AlunoUpdateDTO updateDTO = new AlunoUpdateDTO();
        updateDTO.setNome("Nome Atualizado");

        when(repository.findByLogin(username)).thenReturn(Optional.of(mockEntity));
        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(mapper.toAlunoOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        AlunoOutputDTO result = service.update(username, updateDTO);

        assertNotNull(result);
        verify(repository).findByLogin(username);
        verify(mapper).updateAlunoEntityFromDTO(updateDTO, mockEntity);
        verify(repository).save(mockEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar usuário inexistente")
    void update_ShouldThrowException_WhenUserNotFound() {
        String username = "fantasma";
        AlunoUpdateDTO updateDTO = new AlunoUpdateDTO();

        when(repository.findByLogin(username)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(username, updateDTO);
        });

        verify(mapper, never()).updateAlunoEntityFromDTO(any(), any());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar aluno por ID com sucesso")
    void delete_ShouldDelete_WhenExists() {

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toAlunoOutputDTO(mockEntity)).thenReturn(mockOutputDTO);
        when(mapper.toAlunoEntity(mockOutputDTO)).thenReturn(mockEntity);

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

    @Test
    @DisplayName("Deve verificar se existe por ID")
    void existsById_ShouldReturnTrue_WhenExists() {
        when(repository.existsById(1L)).thenReturn(true);
        assertTrue(service.existsById(1L));
    }

    @Test
    @DisplayName("Deve retornar lista vazia no 'findAll' se não houver alunos")
    void findAll_ShouldReturnEmptyList_WhenNoData() {
        when(repository.findAll()).thenReturn(List.of());

        List<AlunoOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findAll();
        verify(mapper, never()).toAlunoOutputDTO(any());
    }

    @Test
    @DisplayName("Deve retornar false no 'existsById' se o ID não existir")
    void existsById_ShouldReturnFalse_WhenNotExists() {
        when(repository.existsById(99L)).thenReturn(false);
        boolean result = service.existsById(99L);
        assertFalse(result);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException no 'save' se a Configuração de Acessibilidade não for encontrada")
    void save_ShouldThrowException_WhenConfigAcessibilidadeNotFound() {
        Long idConfigInexistente = 999L;
        when(configAcessibilidadeService.findById(idConfigInexistente))
                .thenThrow(new ResourceNotFoundException("Config not found"));

        assertThrows(ResourceNotFoundException.class, () -> {
            service.save(mockInputDTO, idConfigInexistente);
        });

        verify(repository, never()).save(any());
    }
}