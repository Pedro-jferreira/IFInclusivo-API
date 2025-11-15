package com.example.IfGoiano.IfCoders.service.impl;

// Imports do JUnit e Mockito
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; // <-- CORREÇÃO 1 (Import)
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension; // <-- CORREÇÃO 1 (Import)
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Imports de DTOs e Mappers
import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoResponseDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PublicacaoServiceImplTest {

    @InjectMocks
    private PublicacaoServiceImpl service;

    @Mock
    private PublicacaoRepositoy repositoy;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PublicacaoMapper mapper;

    private UsuarioEntity mockUsuario;
    private PublicacaoEntity mockPublicacaoEntity;
    private PublicacaoRequestDTO mockRequestDTO;
    private PublicacaoResponseDTO mockResponseDTO;

    @BeforeEach
    void setUp() {
        mockUsuario = new UsuarioEntity();
        mockUsuario.setId(1L);
        mockUsuario.setLogin("Isaias");

        mockRequestDTO = new PublicacaoRequestDTO();
        mockRequestDTO.setTexto("Texto da nova pulicação");
        mockRequestDTO.setTitulo("Este é um título de teste");

        mockPublicacaoEntity = new PublicacaoEntity();
        mockPublicacaoEntity.setId(10L);
        mockPublicacaoEntity.setTexto("Texto da  nova publicação");
        mockPublicacaoEntity.setUsuario(mockUsuario);

        mockResponseDTO = new PublicacaoResponseDTO();
        mockResponseDTO.setId(10L);
        mockResponseDTO.setTexto("Texto da  nova publicação");
    }

    @Test
    @DisplayName("Deve salvar uma nova publicacao com sucesso")
    void save() {
        // --- Arrange ---
        when(usuarioRepository.findByLogin("Isaias")).thenReturn(Optional.of(mockUsuario));
        when(mapper.toEntity(mockRequestDTO)).thenReturn(mockPublicacaoEntity); // Assumindo que toEntity existe
        when(repositoy.save(any(PublicacaoEntity.class))).thenReturn(mockPublicacaoEntity);
        when(mapper.toDetalhadaDTO(mockPublicacaoEntity, mockUsuario)).thenReturn(mockResponseDTO);

        PublicacaoResponseDTO result = service.save(mockRequestDTO, "Isaias");

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Texto da  nova publicação", result.getTexto()); // Verificando o DTO de resposta

        verify(usuarioRepository, times(1)).findByLogin("Isaias");
        verify(repositoy, times(1)).save(mockPublicacaoEntity);

        verify(mapper, times(1)).toDetalhadaDTO(mockPublicacaoEntity, mockUsuario);

        assertEquals("Isaias", mockPublicacaoEntity.getUsuario().getLogin());
    }

    @Test
    void findAll() {
    }

    @Test
    void findPublicacoesByUserId() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void sugerirTitulos() {
    }

    @Test
    void toggleLike() {
    }

    @Test
    void delete() {
    }
}