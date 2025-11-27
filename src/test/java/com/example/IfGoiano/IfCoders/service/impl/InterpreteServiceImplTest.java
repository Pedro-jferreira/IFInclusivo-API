package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.RequestAnalisePalavra;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.InterpreteUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.InterpreteMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.InterpreteRepository;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InterpreteServiceImplTest {

    @InjectMocks
    private InterpreteServiceImpl service;

    @Mock
    private InterpreteRepository repository;

    @Mock
    private InterpreteMapper mapper;

    @Mock
    private ConfigAcessibilidadeService configAcessibilidadeService;

    @Mock
    private ConfigAcblMapper configAcblMapper;

    @Mock
    private LibrasRepository librasRepository;

    @Mock
    private LibrasMapper librasMapper;

    private InterpreteEntity mockEntity;
    private InterpreteInputDTO mockInputDTO;
    private InterpreteOutputDTO mockOutputDTO;
    private ConfigAcblOutputDTO mockConfigDTO;
    private ConfigAcessibilidadeEntity mockConfigEntity;

    @BeforeEach
    void setUp() {

        mockEntity = new InterpreteEntity();
        mockEntity.setId(1L);
        mockEntity.setNome("Interprete Teste");
        mockEntity.setLogin("interprete.teste");
        mockEntity.setSalary(3000.0);

        mockInputDTO = new InterpreteInputDTO();
        mockInputDTO.setNome("Interprete Teste");
        mockInputDTO.setSalary(3000.0);

        mockOutputDTO = new InterpreteOutputDTO();
        mockOutputDTO.setId(1L);
        mockOutputDTO.setNome("Interprete Teste");

        mockConfigDTO = new ConfigAcblOutputDTO();
        mockConfigDTO.setId(10L);
        mockConfigEntity = new ConfigAcessibilidadeEntity();
        mockConfigEntity.setId(10L);
    }

    @Test
    @DisplayName("Deve retornar lista de todos os intérpretes")
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(mockEntity));
        when(mapper.toInterpreteOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        List<InterpreteOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia no 'findAll' se não houver dados")
    void findAll_ShouldReturnEmptyList_WhenNoData() {
        when(repository.findAll()).thenReturn(List.of());

        List<InterpreteOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findAll();
        verify(mapper, never()).toInterpreteOutputDTO(any());
    }

    @Test
    @DisplayName("Deve buscar intérprete por ID com sucesso")
    void findById_ShouldReturnInterprete_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toInterpreteOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        InterpreteOutputDTO result = service.findById(1L);

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
    @DisplayName("Deve salvar novo intérprete com sucesso")
    void save_ShouldSaveInterprete_WhenValid() {
        Long idConfigAc = 10L;

        when(configAcessibilidadeService.findById(idConfigAc)).thenReturn(mockConfigDTO);
        when(mapper.toInterpreteEntity(mockInputDTO)).thenReturn(mockEntity);
        when(configAcblMapper.toConfigAcblEntity(mockConfigDTO)).thenReturn(mockConfigEntity);
        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toInterpreteOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        InterpreteOutputDTO result = service.save(mockInputDTO, idConfigAc);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).save(mockEntity);
        assertEquals(mockConfigEntity, mockEntity.getConfigAcessibilidadeEntity());
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException no 'save' se config acessibilidade não existir")
    void save_ShouldThrowException_WhenConfigNotFound() {
        Long idConfigAc = 99L;
        when(configAcessibilidadeService.findById(idConfigAc))
                .thenThrow(new ResourceNotFoundException("Config not found"));

        assertThrows(ResourceNotFoundException.class, () -> {
            service.save(mockInputDTO, idConfigAc);
        });

        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve atualizar intérprete com sucesso")
    void update_ShouldUpdateInterprete_WhenUserExists() {
        String username = "interprete.teste";
        InterpreteUpdateDTO updateDTO = new InterpreteUpdateDTO();
        updateDTO.setSalary(4000.0);

        when(repository.findByLogin(username)).thenReturn(Optional.of(mockEntity));
        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(mapper.toInterpreteOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        InterpreteOutputDTO result = service.update(updateDTO, username);

        assertNotNull(result);
        verify(repository).findByLogin(username);
        verify(mapper).updateInterpreteEntityFromDTO(updateDTO, mockEntity);
        verify(repository).save(mockEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar usuário inexistente")
    void update_ShouldThrowException_WhenUserNotFound() {
        String username = "fantasma";
        InterpreteUpdateDTO updateDTO = new InterpreteUpdateDTO();

        when(repository.findByLogin(username)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(updateDTO, username);
        });

        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar intérprete por ID com sucesso")
    void delete_ShouldDelete_WhenExists() {
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

    @Test
    @DisplayName("Deve retornar histórico de libras sugeridas (status EMANALISE)")
    void historicoLibrasSugeridas_ShouldReturnPage() {

        Pageable pageable = PageRequest.of(0, 10);
        LibrasEntity librasEntity = new LibrasEntity();
        librasEntity.setId(100L);
        librasEntity.setStatus(Status.EMANALISE);

        Page<LibrasEntity> page = new PageImpl<>(List.of(librasEntity));

        LibrasOutputDTO librasOutputDTO = new LibrasOutputDTO();
        librasOutputDTO.setId(100L);

        when(librasRepository.findByStatus(Status.EMANALISE, pageable)).thenReturn(page);
        when(librasMapper.toLibrasOutputDTO(librasEntity)).thenReturn(librasOutputDTO);

        Page<LibrasOutputDTO> result = service.historicoLibrasSugeridas(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(100L, result.getContent().get(0).getId());
        verify(librasRepository).findByStatus(Status.EMANALISE, pageable);
    }

    @Test
    @DisplayName("analisarPalavra deve retornar null (implementação atual)")
    void analisarPalavra_ShouldReturnNull() {

        LibrasOutputDTO result = service.analisarPalavra(new RequestAnalisePalavra(), 1L);
        assertNull(result);
    }
}