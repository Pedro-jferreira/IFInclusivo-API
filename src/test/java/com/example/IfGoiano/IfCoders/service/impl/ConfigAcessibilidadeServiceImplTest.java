package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ConfigAcblInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.Enums.TemaCSS;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ConfigAcessibilidadeRepository;
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
class ConfigAcessibilidadeServiceImplTest {

    @InjectMocks
    private ConfigAcessibilidadeServiceImpl service;

    @Mock
    private ConfigAcessibilidadeRepository repository;

    @Mock
    private ConfigAcblMapper mapper;

    private ConfigAcessibilidadeEntity mockEntity;
    private ConfigAcblInputDTO mockInputDTO;
    private ConfigAcblOutputDTO mockOutputDTO;

    @BeforeEach
    void setUp() {

        mockEntity = new ConfigAcessibilidadeEntity();
        mockEntity.setId(1L);
        mockEntity.setTema(TemaCSS.TEMA1);
        mockEntity.setZoom("100%");
        mockEntity.setAudicao("Normal");

        mockInputDTO = new ConfigAcblInputDTO();
        mockInputDTO.setTema(TemaCSS.TEMA1);
        mockInputDTO.setZoom("100%");
        mockInputDTO.setAudicao("Normal");

        mockOutputDTO = new ConfigAcblOutputDTO();
        mockOutputDTO.setId(1L);
        mockOutputDTO.setTema(TemaCSS.TEMA1);
        mockOutputDTO.setZoom("100%");
        mockOutputDTO.setAudicao("Normal");
    }

    @Test
    @DisplayName("Deve retornar lista de todas as configurações")
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(mockEntity));
        when(mapper.toConfigAcblOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        List<ConfigAcblOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findAll();
        verify(mapper).toConfigAcblOutputDTO(mockEntity);
    }

    @Test
    @DisplayName("Deve retornar lista vazia se não houver configurações")
    void findAll_ShouldReturnEmptyList_WhenNoData() {
        when(repository.findAll()).thenReturn(List.of());

        List<ConfigAcblOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findAll();
        verify(mapper, never()).toConfigAcblOutputDTO(any());
    }

    @Test
    @DisplayName("Deve buscar configuração por ID com sucesso")
    void findById_ShouldReturnConfig_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toConfigAcblOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        ConfigAcblOutputDTO result = service.findById(1L);

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
    @DisplayName("Deve salvar nova configuração com sucesso")
    void save_ShouldSaveConfig_WhenValid() {

        when(mapper.toConfigAcblEntity(mockInputDTO)).thenReturn(mockEntity);
        when(repository.save(mockEntity)).thenReturn(mockEntity);

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toConfigAcblOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        ConfigAcblOutputDTO result = service.save(mockInputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(repository).save(mockEntity);
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve atualizar configuração com sucesso")
    void update_ShouldUpdateConfig_WhenExists() {
        Long id = 1L;
        ConfigAcblInputDTO updateDTO = new ConfigAcblInputDTO();
        updateDTO.setZoom("150%");

        when(repository.findById(id)).thenReturn(Optional.of(mockEntity));
        doNothing().when(mapper).updateConfigAcblEntityFromDTO(updateDTO, mockEntity);
        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(mapper.toConfigAcblOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        ConfigAcblOutputDTO result = service.update(id, updateDTO);

        assertNotNull(result);
        verify(repository).findById(id);
        verify(mapper).updateConfigAcblEntityFromDTO(updateDTO, mockEntity);
        verify(repository).save(mockEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar ID inexistente")
    void update_ShouldThrowException_WhenNotFound() {
        Long id = 99L;
        ConfigAcblInputDTO updateDTO = new ConfigAcblInputDTO();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(id, updateDTO);
        });

        verify(mapper, never()).updateConfigAcblEntityFromDTO(any(), any());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar configuração por ID com sucesso")
    void delete_ShouldDelete_WhenExists() {

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toConfigAcblOutputDTO(mockEntity)).thenReturn(mockOutputDTO);
        when(mapper.toConfigAcblEntity(mockOutputDTO)).thenReturn(mockEntity);

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

    //git add src/test/java/com/example/IfGoiano/IfCoders/service/impl/AlunoServiceImplTest.java
    //git commit -m "test(AlunoServiceImpl): adiciona suíte de testes completa" -m "Cobre CRUD de Aluno, com atenção especial à lógica de conversão no método delete."
    //git push -u origin feature/testes-aluno-service