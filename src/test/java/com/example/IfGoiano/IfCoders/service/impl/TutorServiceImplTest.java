package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TutorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.TutorUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TutorOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.TutorMapper;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.TutorEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.TutorRepository;
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
class TutorServiceImplTest {

    @InjectMocks
    private TutorServiceImpl service;

    @Mock
    private TutorRepository repository;

    @Mock
    private TutorMapper mapper;

    @Mock
    private ConfigAcessibilidadeService configAcessibilidadeService;

    @Mock
    private ConfigAcblMapper configAcblMapper;

    private TutorEntity mockEntity;
    private TutorInputDTO mockInputDTO;
    private TutorOutputDTO mockOutputDTO;
    private ConfigAcblOutputDTO mockConfigDTO;
    private ConfigAcessibilidadeEntity mockConfigEntity;

    @BeforeEach
    void setUp() {

        mockEntity = new TutorEntity();
        mockEntity.setId(1L);
        mockEntity.setNome("Tutor Teste");
        mockEntity.setLogin("tutor.teste");
        mockEntity.setEspecialidade("Matemática");

        mockInputDTO = new TutorInputDTO();
        mockInputDTO.setNome("Tutor Teste");
        mockInputDTO.setEspecialidade("Matemática");

        mockOutputDTO = new TutorOutputDTO();
        mockOutputDTO.setId(1L);
        mockOutputDTO.setNome("Tutor Teste");
        mockOutputDTO.setEspecialidade("Matemática");

        mockConfigDTO = new ConfigAcblOutputDTO();
        mockConfigDTO.setId(10L);
        mockConfigEntity = new ConfigAcessibilidadeEntity();
        mockConfigEntity.setId(10L);
    }

    @Test
    @DisplayName("Deve retornar lista de todos os tutores")
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(mockEntity));
        when(mapper.toTutorOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        List<TutorOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findAll();
        verify(mapper).toTutorOutputDTO(mockEntity);
    }

    @Test
    @DisplayName("Deve retornar lista vazia se não houver tutores")
    void findAll_ShouldReturnEmptyList_WhenNoData() {
        when(repository.findAll()).thenReturn(List.of());

        List<TutorOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findAll();
        verify(mapper, never()).toTutorOutputDTO(any());
    }

    @Test
    @DisplayName("Deve buscar tutor por ID com sucesso")
    void findById_ShouldReturnTutor_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toTutorOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        TutorOutputDTO result = service.findById(1L);

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
    @DisplayName("Deve salvar novo tutor com sucesso")
    void save_ShouldSaveTutor_WhenValid() {
        Long idConfigAc = 10L;

        when(configAcessibilidadeService.findById(idConfigAc)).thenReturn(mockConfigDTO);
        when(mapper.toTutorEntity(mockInputDTO)).thenReturn(mockEntity);
        when(configAcblMapper.toConfigAcblEntity(mockConfigDTO)).thenReturn(mockConfigEntity);
        when(repository.save(mockEntity)).thenReturn(mockEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toTutorOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        TutorOutputDTO result = service.save(mockInputDTO, idConfigAc);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(repository).save(mockEntity);

        assertEquals(mockConfigEntity, mockEntity.getConfigAcessibilidadeEntity());
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

    @Test
    @DisplayName("Deve atualizar tutor com sucesso")
    void update_ShouldUpdateTutor_WhenUserExists() {
        String username = "tutor.teste";
        TutorUpdateDTO updateDTO = new TutorUpdateDTO();
        updateDTO.setNome("Nome Atualizado");

        when(repository.findByLogin(username)).thenReturn(Optional.of(mockEntity));
        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(mapper.toTutorOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        TutorOutputDTO result = service.update(updateDTO, username);

        assertNotNull(result);
        verify(repository).findByLogin(username);
        verify(mapper).updateTutorEntityFromDTO(updateDTO, mockEntity);
        verify(repository).save(mockEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar usuário inexistente")
    void update_ShouldThrowException_WhenUserNotFound() {
        String username = "fantasma";
        TutorUpdateDTO updateDTO = new TutorUpdateDTO();

        when(repository.findByLogin(username)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(updateDTO, username);
        });

        verify(mapper, never()).updateTutorEntityFromDTO(any(), any());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar tutor por ID com sucesso")
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

    @Test
    @DisplayName("Deve retornar false no 'existsById' se o ID não existir")
    void existsById_ShouldReturnFalse_WhenNotExists() {
        when(repository.existsById(99L)).thenReturn(false);
        boolean result = service.existsById(99L);
        assertFalse(result);
    }
}