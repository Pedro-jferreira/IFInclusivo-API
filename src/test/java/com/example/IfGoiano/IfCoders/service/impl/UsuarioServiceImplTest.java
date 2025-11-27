package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ConfigAcblOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ConfigAcblMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.Enums.Role;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.ConfigAcessibilidadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl service;

    @Mock
    private UsuarioRepository repository;

    @Mock
    private UsuarioMapper mapper;

    @Mock
    private ConfigAcessibilidadeService configAcessibilidadeService;

    @Mock
    private ConfigAcblMapper configAcblMapper;

    private UsuarioEntity mockEntity;
    private UsuarioInputDTO mockInputDTO;
    private UsuarioOutputDTO mockOutputDTO;
    private ConfigAcblOutputDTO mockConfigDTO;
    private ConfigAcessibilidadeEntity mockConfigEntity;

    @BeforeEach
    void setUp() {

        mockEntity = new UsuarioEntity();
        mockEntity.setId(1L);
        mockEntity.setNome("Usuario Teste");
        mockEntity.setLogin("usuario.teste");

        mockInputDTO = new UsuarioInputDTO();
        mockInputDTO.setNome("Usuario Teste");
        mockInputDTO.setLogin("usuario.teste");

        mockOutputDTO = new UsuarioOutputDTO();
        mockOutputDTO.setId(1L);
        mockOutputDTO.setNome("Usuario Teste");

        mockConfigDTO = new ConfigAcblOutputDTO();
        mockConfigDTO.setId(10L);
        mockConfigEntity = new ConfigAcessibilidadeEntity();
        mockConfigEntity.setId(10L);
    }

    @Test
    @DisplayName("Deve retornar lista de todos os usuários")
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(mockEntity));
        when(mapper.toOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        List<UsuarioOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findAll();
        verify(mapper).toOutputDTO(mockEntity);
    }

    @Test
    @DisplayName("Deve retornar lista vazia se não houver usuários")
    void findAll_ShouldReturnEmptyList_WhenNoData() {
        when(repository.findAll()).thenReturn(List.of());

        List<UsuarioOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findAll();
        verify(mapper, never()).toOutputDTO(any());
    }

    @Test
    @DisplayName("Deve buscar usuário por ID com sucesso")
    void findById_ShouldReturnUsuario_WhenExists() {

        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        UsuarioOutputDTO result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Deve salvar novo usuário com sucesso")
    void save_ShouldSaveUsuario_WhenValid() {
        Long idConfigAc = 10L;

        when(configAcessibilidadeService.findById(idConfigAc)).thenReturn(mockConfigDTO);
        when(mapper.toEntity(mockInputDTO)).thenReturn(mockEntity);
        when(configAcblMapper.toConfigAcblEntity(mockConfigDTO)).thenReturn(mockConfigEntity);
        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(repository.findById(1L)).thenReturn(Optional.of(mockEntity));
        when(mapper.toOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        UsuarioOutputDTO result = service.save(mockInputDTO, idConfigAc);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(repository).save(mockEntity);
        assertEquals(mockConfigEntity, mockEntity.getConfigAcessibilidadeEntity());
    }

    @Test
    @DisplayName("Deve atualizar usuário com sucesso")
    void update_ShouldUpdateUsuario_WhenExists() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(mockEntity));
        when(repository.save(mockEntity)).thenReturn(mockEntity);
        when(mapper.toOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        UsuarioOutputDTO result = service.update(mockInputDTO, id);

        assertNotNull(result);
        verify(repository).findById(id);
        verify(mapper).updateUsuarioEntityFromDTO(mockInputDTO, mockEntity);
        verify(repository).save(mockEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao atualizar ID inexistente")
    void update_ShouldThrowException_WhenNotFound() {
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.update(mockInputDTO, id);
        });

        assertEquals("usuario nao disponivel", exception.getMessage());
        verify(mapper, never()).updateUsuarioEntityFromDTO(any(), any());
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve filtrar usuários por nome e role (Search)")
    void searchUsers_ShouldReturnFilteredList() {
        String name = "Teste";
        Role role = Role.ROLE_ALUNO;

        when(repository.findAll(any(Specification.class))).thenReturn(List.of(mockEntity));
        when(mapper.toOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        List<UsuarioOutputDTO> result = service.searchUsers(name, role);

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(repository).findAll(any(Specification.class));
    }

    @Test
    @DisplayName("Deve deletar usuário por ID")
    void delete_ShouldDelete_WhenCalled() {

        service.delete(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve verificar se existe por ID")
    void existsById_ShouldReturnTrue_WhenExists() {
        when(repository.existsById(1L)).thenReturn(true);
        assertTrue(service.existsById(1L));
    }
    @Test
    @DisplayName("Deve retornar todos os usuários se os filtros de busca forem nulos")
    void searchUsers_ShouldReturnAll_WhenFiltersAreNull() {

        when(repository.findAll(any(Specification.class))).thenReturn(List.of(mockEntity));
        when(mapper.toOutputDTO(mockEntity)).thenReturn(mockOutputDTO);

        List<UsuarioOutputDTO> result = service.searchUsers(null, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository).findAll(any(Specification.class));
    }
    @Test
    @DisplayName("Deve retornar lista vazia no 'searchUsers' se nenhum usuário for encontrado")
    void searchUsers_ShouldReturnEmptyList_WhenNoMatch() {

        when(repository.findAll(any(Specification.class))).thenReturn(List.of());

        List<UsuarioOutputDTO> result = service.searchUsers("Nome Inexistente", Role.ROLE_ALUNO);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository).findAll(any(Specification.class));
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException no 'save' se a Configuração de Acessibilidade não for encontrada")
    void save_ShouldThrowException_WhenConfigNotFound() {

        Long idConfigInexistente = 999L;
        when(configAcessibilidadeService.findById(idConfigInexistente))
                .thenThrow(new ResourceNotFoundException("Config not found"));

        assertThrows(ResourceNotFoundException.class, () -> {
            service.save(mockInputDTO, idConfigInexistente);
        });

        verify(repository, never()).save(any());
    }
    @Test
    @DisplayName("Deve lançar NoSuchElementException no 'findById' se o ID não existir (comportamento atual)")
    void findById_ShouldThrowException_WhenNotFound() {

        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(java.util.NoSuchElementException.class, () -> {
            service.findById(id);
        });

        verify(mapper, never()).toOutputDTO(any());
    }

    @Test
    @DisplayName("Deve retornar false no 'existsById' se o ID não existir")
    void existsById_ShouldReturnFalse_WhenNotExists() {

        when(repository.existsById(99L)).thenReturn(false);

        boolean result = service.existsById(99L);

        assertFalse(result);
    }

}