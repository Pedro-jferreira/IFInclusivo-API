package com.example.IfGoiano.IfCoders.service.impl;

// Imports do JUnit e Mockito
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoResponseDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    private UsuarioEntity mockOutroUsuario;
    private PublicacaoEntity mockPublicacaoEntity;
    private PublicacaoRequestDTO mockRequestDTO;
    private PublicacaoResponseDTO mockResponseDTO;

    @BeforeEach
    void setUp() {

        mockUsuario = new UsuarioEntity();
        mockUsuario.setId(1L);
        mockUsuario.setLogin("Isaias");
        mockUsuario.setLikes(new HashSet<>());

        mockOutroUsuario = new UsuarioEntity();
        mockOutroUsuario.setId(2L);
        mockOutroUsuario.setLogin("OutroUsuario");

        mockRequestDTO = new PublicacaoRequestDTO();
        mockRequestDTO.setTexto("Texto da nova pulicação");
        mockRequestDTO.setTitulo("Este é um título de teste");

        mockPublicacaoEntity = new PublicacaoEntity();
        mockPublicacaoEntity.setId(10L);
        mockPublicacaoEntity.setTexto("Texto da  nova publicação");
        mockPublicacaoEntity.setUsuario(mockUsuario);
        mockPublicacaoEntity.setLikeBy(new HashSet<>());

        mockResponseDTO = new PublicacaoResponseDTO();
        mockResponseDTO.setId(10L);
        mockResponseDTO.setTexto("Texto da  nova publicação");
    }

    @Test
    @DisplayName("Deve salvar uma nova publicacao com sucesso")
    void save() {

        when(usuarioRepository.findByLogin("Isaias")).thenReturn(Optional.of(mockUsuario));
        when(mapper.toEntity(mockRequestDTO)).thenReturn(mockPublicacaoEntity);
        when(repositoy.save(any(PublicacaoEntity.class))).thenReturn(mockPublicacaoEntity);
        when(mapper.toDetalhadaDTO(mockPublicacaoEntity, mockUsuario)).thenReturn(mockResponseDTO);

        PublicacaoResponseDTO result = service.save(mockRequestDTO, "Isaias");

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Texto da  nova publicação", result.getTexto());

        verify(usuarioRepository, times(1)).findByLogin("Isaias");
        verify(repositoy, times(1)).save(mockPublicacaoEntity);

        verify(mapper, times(1)).toDetalhadaDTO(mockPublicacaoEntity, mockUsuario);

        assertEquals("Isaias", mockPublicacaoEntity.getUsuario().getLogin());
    }

    @Test
    @DisplayName("Deve retornar uma pagina de publicações")
    void findAll() {

        Pageable pageable = PageRequest.of(0, 10);
        String userName = "Isaias";
        Ordenacao ordenacao = Ordenacao.RELEVANCIA;

        List<PublicacaoEntity> listaDePublicacoes = List.of(mockPublicacaoEntity);

        Page<PublicacaoEntity> mockPage = new PageImpl<>(listaDePublicacoes, pageable, 1);

        when(usuarioRepository.findByLogin(userName)).thenReturn(Optional.of(mockUsuario));

        when(repositoy.findAll(any(Specification.class), any(Pageable.class))).thenReturn(mockPage);

        when(mapper.toDetalhadaDTO(mockPublicacaoEntity, mockUsuario)).thenReturn(mockResponseDTO);

        Page<PublicacaoResponseDTO> result = service.findAll(null, ordenacao, pageable, null, userName);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getContent().size());
        assertEquals(10L, result.getContent().get(0).getId());

        verify(usuarioRepository, times(1)).findByLogin(userName);
        verify(repositoy, times(1)).findAll(any(Specification.class), any(Pageable.class));
        verify(mapper, times(1)).toDetalhadaDTO(mockPublicacaoEntity, mockUsuario);
    }

    @Test
    @DisplayName("Deve encontrar publicações por ID de usuário")
    void findPublicacoesByUserId() {

        Long userId = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        List<PublicacaoEntity> listaDePublicacoes = List.of(mockPublicacaoEntity);
        Page<PublicacaoEntity> mockPage = new PageImpl<>(listaDePublicacoes, pageable, 1);

        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(mockUsuario));
        when(repositoy.findPublicacaoEntitiesByUsuarioOrderByDataCriacaoDesc(mockUsuario, pageable)).thenReturn(mockPage);
        when(mapper.toDetalhadaDTO(mockPublicacaoEntity, mockUsuario)).thenReturn(mockResponseDTO);

        Page<PublicacaoResponseDTO> result = service.findPublicacoesByUserId(pageable, userId);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());

        verify(usuarioRepository, times(1)).findById(userId);
        verify(repositoy, times(1)).findPublicacaoEntitiesByUsuarioOrderByDataCriacaoDesc(mockUsuario, pageable);
        verify(mapper, times(1)).toDetalhadaDTO(mockPublicacaoEntity, mockUsuario);
    }

    @Test
    @DisplayName("Deve encontrar por ID com sucesso")
    void findById_ShouldReturnPublication_WhenFound() {
        String username = "Isaias";
        Long pubId = 10L;

        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockUsuario));
        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));
        when(mapper.toDetalhadaDTO(mockPublicacaoEntity, mockUsuario)).thenReturn(mockResponseDTO);

        PublicacaoResponseDTO result = service.findById(pubId, username);

        assertNotNull(result);
        assertEquals(pubId, result.getId());
        verify(repositoy).findById(pubId);
        verify(mapper).toDetalhadaDTO(mockPublicacaoEntity, mockUsuario);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao não encontrar ID")
    void findById_ShouldThrowException_WhenNotFound() {
        String username = "Isaias";
        Long pubId = 99L;

        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockUsuario));
        when(repositoy.findById(pubId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(pubId, username);
        });

        verify(mapper, never()).toDetalhadaDTO(any(), any());
    }

    @Test
    @DisplayName("Deve atualizar uma publicacao com sucesso")
    void update_ShouldUpdatePublication_WhenUserIsOwner() {
        String username = "Isaias";
        Long pubId = 10L;

        PublicacaoEntity entidadeAtualizada = new PublicacaoEntity();
        entidadeAtualizada.setId(pubId);
        entidadeAtualizada.setUsuario(mockUsuario);
        entidadeAtualizada.setTitulo("Titulo Atualizado");
        entidadeAtualizada.setTexto("Texto da nova pulicação");

        PublicacaoResponseDTO responseAtualizado = new PublicacaoResponseDTO();
        responseAtualizado.setId(pubId);
        responseAtualizado.setTitulo("Titulo Atualizado");


        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockUsuario));
        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));
        when(repositoy.save(any(PublicacaoEntity.class))).thenReturn(entidadeAtualizada);
        when(mapper.toDetalhadaDTO(entidadeAtualizada, mockUsuario)).thenReturn(responseAtualizado);

        PublicacaoRequestDTO requestAtualizado = new PublicacaoRequestDTO();
        requestAtualizado.setTitulo("Titulo Atualizado");
        requestAtualizado.setTexto("Texto da nova pulicação");
        requestAtualizado.setCategorias(new HashSet<>());

        PublicacaoResponseDTO result = service.update(pubId, requestAtualizado, username);

        assertNotNull(result);
        assertEquals("Titulo Atualizado", result.getTitulo());

        verify(repositoy).save(any(PublicacaoEntity.class));
    }

    @Test
    @DisplayName("Deve lançar AccessDeniedException ao tentar atualizar publicacao de outro usuario")
    void update_ShouldThrowAccessDenied_WhenUserIsNotOwner() {
        String username = "OutroUsuario";
        Long pubId = 10L;

        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockOutroUsuario));
        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));

        assertThrows(AccessDeniedException.class, () -> {
            service.update(pubId, mockRequestDTO, username);
        });

        verify(repositoy, never()).save(any());
    }

    @Test
    @DisplayName("Deve sugerir titulos com base na query")
    void sugerirTitulos_ShouldReturnListOfTitles() {
        String query = "teste";

        mockPublicacaoEntity.setTitulo("Este é um título de teste");

        when(repositoy.findByTituloAndOptionalCategorias(query, null)).thenReturn(List.of(mockPublicacaoEntity));

        List<String> result = service.sugerirTitulos(query, null);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Este é um título de teste", result.get(0));

        verify(repositoy).findByTituloAndOptionalCategorias(query, null);
    }

    @Test
    @DisplayName("Deve adicionar like quando nao curtido (retornar true)")
    void toggleLike_ShouldAddLike_WhenNotLiked() {
        String username = "Isaias";
        Long pubId = 10L;

        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));
        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockUsuario));

        boolean result = service.toggleLike(pubId, username);

        assertTrue(result);
        assertTrue(mockPublicacaoEntity.getLikeBy().contains(mockUsuario));
        assertTrue(mockUsuario.getLikes().contains(mockPublicacaoEntity));

        verify(repositoy).save(mockPublicacaoEntity);
        verify(usuarioRepository).save(mockUsuario);
    }

    @Test
    @DisplayName("Deve remover like quando ja curtido (retornar false)")
    void toggleLike_ShouldRemoveLike_WhenAlreadyLiked() {
        String username = "Isaias";
        Long pubId = 10L;

        mockPublicacaoEntity.getLikeBy().add(mockUsuario);
        mockUsuario.getLikes().add(mockPublicacaoEntity);

        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));
        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockUsuario));

        boolean result = service.toggleLike(pubId, username);

        assertFalse(result); // Deve descurtiu
        assertFalse(mockPublicacaoEntity.getLikeBy().contains(mockUsuario));
        assertFalse(mockUsuario.getLikes().contains(mockPublicacaoEntity));

        verify(repositoy).save(mockPublicacaoEntity);
        verify(usuarioRepository).save(mockUsuario);
    }

    @Test
    @DisplayName("Deve deletar uma publicacao com sucesso")
    void delete_ShouldDeletePublication_WhenUserIsOwner() {
        String username = "Isaias";
        Long pubId = 10L;

        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));
        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockUsuario));

        assertDoesNotThrow(() -> {
            service.delete(pubId, username);
        });

        verify(repositoy, times(1)).delete(mockPublicacaoEntity);
    }

    @Test
    @DisplayName("Deve lançar AccessDeniedException ao tentar deletar publicacao de outro usuario")
    void delete_ShouldThrowAccessDenied_WhenUserIsNotOwner() {
        String username = "OutroUsuario";
        Long pubId = 10L;

        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity)); // Dono é mockUsuario (ID 1)
        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockOutroUsuario)); // Usuário é ID 2

        assertThrows(AccessDeniedException.class, () -> {
            service.delete(pubId, username);
        });

        verify(repositoy, never()).delete(any(PublicacaoEntity.class));
    }
}