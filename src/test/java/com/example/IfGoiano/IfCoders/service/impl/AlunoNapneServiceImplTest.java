package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoNapneInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.AlunoNapneUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoNapneMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.entity.AlunoNapneEntity;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.AlunoNapneRepository;
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
class AlunoNapneServiceImplTest {

    @InjectMocks
    private AlunoNapneServiceImpl service;

    @Mock
    private AlunoNapneRepository repository;

    @Mock
    private AlunoNapneMapper mapper;

    @Mock
    private ConfigAcessibilidadeService configAcessibilidadeService;

    @Mock
    private ConfigAcblMapper configAcblMapper;

    private AlunoNapneEntity mockEntity;
    private AlunoNapneInputDTO mockInputDTO;
    private AlunoNapneOutputDTO mockOutputDTO;
    private ConfigAcblOutputDTO mockConfigDTO;
    private ConfigAcessibilidadeEntity mockConfigEntity;

    @BeforeEach
    void setUp() {

        mockEntity = new AlunoNapneEntity();
        mockEntity.setId(1L);
        mockEntity.setNome("Aluno Napne Teste");
        mockEntity.setLogin("aluno.napne");
        mockEntity.setCondicao("TDAH");

        mockInputDTO = new AlunoNapneInputDTO();
        mockInputDTO.setNome("Aluno Napne Teste");
        mockInputDTO.setCondicao("TDAH");

        mockOutputDTO = new AlunoNapneOutputDTO();
        mockOutputDTO.setId(1L);
        mockOutputDTO.setNome("Aluno Napne Teste");

        mockConfigDTO = new ConfigAcblOutputDTO();
        mockConfigDTO.setId(10L);

        mockConfigEntity = new ConfigAcessibilidadeEntity();
        mockConfigEntity.setId(10L);
    }

    @Test
    @DisplayName("Deve retornar lista de todos os alunos Napne")
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(mockEntity));
        when(mapper.toAlunoNapneOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        List<AlunoNapneOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findAll();
        verify(mapper).toAlunoNapneOutputDTO(mockEntity);
    }

    @Test
    @DisplayName("Deve buscar aluno por ID com sucesso")
    void findById() {

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toAlunoNapneOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        AlunoNapneOutputDTO result = service.findById(1L);

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
    @DisplayName("Deve salvar novo aluno Napne com sucesso")
    void save_ShouldSaveAluno_WhenValid() {

        Long idConfigAc = 10L;

        when(configAcessibilidadeService.findById(idConfigAc)).thenReturn(mockConfigDTO);
        when(mapper.toAlunoNapneEntity(mockInputDTO)).thenReturn(mockEntity);
        when(configAcblMapper.toConfigAcblEntity(mockConfigDTO)).thenReturn(mockConfigEntity);
        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toAlunoNapneOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        AlunoNapneOutputDTO result = service.save(mockInputDTO, idConfigAc);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(configAcessibilidadeService).findById(idConfigAc);
        verify(repository).save(mockEntity);

        assertEquals(mockConfigEntity, mockEntity.getConfigAcessibilidadeEntity());
    }

    @Test
    @DisplayName("Deve atualizar aluno Napne com sucesso")
    void update() {

        String username = "aluno.napne";
        AlunoNapneUpdateDTO updateDTO = new AlunoNapneUpdateDTO();
        updateDTO.setNome("Nome Atualizado");

        when(repository.findByLogin(username)).thenReturn(Optional.of(mockEntity));
        doNothing().when(mapper).updateAlunoNapneEntiryFromDTO(updateDTO, mockEntity);
        when(mapper.toAlunoNapneOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        AlunoNapneOutputDTO result = service.update(updateDTO, username);

        assertNotNull(result);
        verify(repository).findByLogin(username);
        verify(mapper).updateAlunoNapneEntiryFromDTO(updateDTO, mockEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar usuário inexistente")
    void update_ShouldThrowException_WhenUserNotFound() {

        String username = "fantasma";
        AlunoNapneUpdateDTO updateDTO = new AlunoNapneUpdateDTO();

        when(repository.findByLogin(username)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(updateDTO, username);
        });

        verify(mapper, never()).updateAlunoNapneEntiryFromDTO(any(), any());
    }

    @Test
    @DisplayName("Deve deletar aluno por ID com sucesso")
    void delete() {

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));

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
    void existsById() {
        when(repository.existsById(1L)).thenReturn(true);
        assertTrue(service.existsById(1L));
    }
}