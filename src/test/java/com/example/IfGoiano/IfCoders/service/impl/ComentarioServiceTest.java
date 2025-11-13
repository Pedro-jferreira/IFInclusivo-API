package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioResponseDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ComentarioMapper;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComentarioServiceTest {

    @InjectMocks
    private ComentarioService comentarioService;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private PublicacaoRepositoy publicacaoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    ComentarioMapper comentarioMapper;

    private UsuarioEntity mockUsuario;
    private PublicacaoEntity mockPublicacao;
    private ComentarioRequestDTO mockRequestDTO;
    private ComentarioEntity mockComentarioEntity;
    private ComentarioResponseDTO mockResponseDTO;

    @BeforeEach
    void setUp() {

        mockUsuario = new UsuarioEntity();
        mockUsuario.setId(1L);
        mockUsuario.setLogin("Isaias");

        mockPublicacao = new PublicacaoEntity();
        mockPublicacao.setId(10L);

        mockRequestDTO = new ComentarioRequestDTO();
        mockRequestDTO.setTexto("teste: que poste legal");

        mockComentarioEntity = new ComentarioEntity();
        mockComentarioEntity.setTexto("teste: muito bom");

        mockResponseDTO = new ComentarioResponseDTO();
        mockResponseDTO.setId(100L);
        mockResponseDTO.setTexto("teste: realmente muitto legal");
    }
    @Test
    @DisplayName("Deve adicionar um comentario com sucesso")
    void adicionarComentario_DeveSalvarComSucesso() {

        when(usuarioRepository.findByLogin("Isaias")).thenReturn(Optional.of(mockUsuario));
        when(publicacaoRepository.findById(10L)).thenReturn(Optional.of(mockPublicacao));
        when(comentarioMapper.toEntity(mockRequestDTO)).thenReturn(mockComentarioEntity);
        when(comentarioRepository.save(any(ComentarioEntity.class)))
                // ...retorne a entidade salva (vamos simular que o ID 100L foi gerado)
                .thenAnswer(invocation -> {
                    ComentarioEntity entitySalva = invocation.getArgument(0);
                    entitySalva.setId(100L); // Simula o ID sendo gerado pelo DB
                    return entitySalva;
                });
        when(comentarioMapper.toResponseDTO(any(ComentarioEntity.class), eq(mockUsuario)))
                // ...retorne nosso mockResponseDTO
                .thenReturn(mockResponseDTO);
        ComentarioResponseDTO result = comentarioService.adicionarComentario(10L, mockRequestDTO, "Isaias");

        // Verificar se os resultados são os esperados
        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("teste: realmente muitto legal", result.getTexto());

        // Verificar se os mocks foram chamados (bom para garantir o fluxo)
        verify(usuarioRepository, times(1)).findByLogin("Isaias");
        verify(publicacaoRepository, times(1)).findById(10L);
        verify(comentarioRepository, times(1)).save(any(ComentarioEntity.class));
        verify(comentarioMapper, times(1)).toResponseDTO(any(ComentarioEntity.class), eq(mockUsuario));

        // Verifica se o usuário e a publicação foram definidos na entidade ANTES de salvar
        assertEquals("Isaias", mockComentarioEntity.getUsuario().getLogin());
        assertEquals(10L, mockComentarioEntity.getPublicacao().getId());
    }

    @Test
    void editarComentario() {
    }

    @Test
    void excluirComentario() {
    }

    @Test
    void toggleCurtir() {
    }

    @Test
    void listarComentariosPublicacao() {
    }

    @Test
    void listarRespostasComentario() {
    }
}