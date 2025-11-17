package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioResponseDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.ComentarioMapper;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.action.internal.EntityActionVetoException;
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
    @DisplayName("Deve lançar exceção ao adicionar comentário se usuário não existir")
    void adicionarComentario_DeveLancarExcecao_QuandoUsuarioNaoEncontrado() {

        when(usuarioRepository.findByLogin("fantasma")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            comentarioService.adicionarComentario(10L, mockRequestDTO, "fantasma");
        });
        verify(comentarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve excluir um comentário com sucesso")
    void excluirComentario_DeveExcluirComSucesso_QuandoForAutor() {

        mockComentarioEntity.setUsuario(mockUsuario);
        when(comentarioRepository.findById(100L)).thenReturn(Optional.of(mockComentarioEntity));
        comentarioService.excluirComentario(100L, "Isaias");
        verify(comentarioRepository, times(1)).delete(mockComentarioEntity);

    }

    @Test
    @DisplayName("Deve lançar exceção de segurança ao tentar excluir comentário de outro usuário")
    void excluirComentario_DeveLancarExcecao_QuandoNaoForAutor() {

        mockComentarioEntity.setUsuario(mockUsuario);
        when(comentarioRepository.findById(100L)).thenReturn(Optional.of(mockComentarioEntity));
        assertThrows(SecurityException.class, () -> {
            comentarioService.excluirComentario(100L, "maria");
        });
        verify(comentarioRepository, never()).delete(any());
    }

    @Test
    @DisplayName("Deve ediatr um comentario com sucesso")
    void editarComentario_DeveEditarComSucesso() {

        Long comentarioId = 100L;
        String usernameDono = "Isaias";

        //novo comentario
        ComentarioRequestDTO updateRequest = new ComentarioRequestDTO();
        updateRequest.setTexto("Texto Atualizado");

        //antigo comentario
        mockComentarioEntity.setId(comentarioId);
        mockComentarioEntity.setTexto("Texto antigo");
        mockComentarioEntity.setUsuario(mockUsuario);

        //comportamento
        when(comentarioRepository.findById(comentarioId)).thenReturn(Optional.of(mockComentarioEntity));//retone comentario existente
        when(comentarioRepository.save(any(ComentarioEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));//atualiza comentario
        ComentarioResponseDTO responseDTOAtualizado = new ComentarioResponseDTO();
        responseDTOAtualizado.setId(comentarioId);
        responseDTOAtualizado.setTexto("Texto Atualizado");

        when(comentarioMapper.toResponseDTO(any(ComentarioEntity.class), eq(mockUsuario))).thenReturn(responseDTOAtualizado);

        ComentarioResponseDTO result = comentarioService.editarComentario(comentarioId, updateRequest, usernameDono);

        assertNotNull(result);
        assertEquals(comentarioId, result.getId());
        assertEquals("Texto Atualizado", result.getTexto());

        assertEquals("Texto Atualizado", mockComentarioEntity.getTexto());

        verify(comentarioRepository, times(1)).findById(comentarioId); // Verificamos se ele procurou o comentário
        verify(comentarioRepository, times(1)).save(mockComentarioEntity); // Verificamos se ele salvou o comentário
        verify(comentarioMapper, times(1)).toResponseDTO(mockComentarioEntity, mockUsuario); // Verificamos se ele mapeou a resposta

        verify(usuarioRepository, never()).findByLogin(anyString());
        verify(publicacaoRepository, never()).findById(anyLong());

    }
    @Test
    @DisplayName("ver se a exceção verifica quando tenta adicionar comentario sem ter uma publicação")
    void adicionarComentarioSemTerUmaPublicacao() {

        when(usuarioRepository.findByLogin(anyString())).thenReturn(Optional.of(mockUsuario));
        when(publicacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            comentarioService.adicionarComentario(99L, mockRequestDTO, "Isaias");
        });
    }

    @Test
    @DisplayName("editar comentario quando não existe comentario")
    void editarComentario_QuandoNaoExisteComentario() {

        when(comentarioRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            comentarioService.editarComentario(99L, mockRequestDTO, "Isaias");
        });
    }

    @Test
    @DisplayName("usuário não pode editar o comentário de outro")
    void editarComentario_DeveLancarExcecao_QuandoNaoForAutor() {

        Long comentarioId = 100L;
        String usernameDono = "Isaias";
        String usernameInvasor = "maria";

        ComentarioRequestDTO updateRequest = new ComentarioRequestDTO();
        updateRequest.setTexto("Texto Malicioso");

        mockComentarioEntity.setId(comentarioId);
        mockComentarioEntity.setUsuario(mockUsuario);

        when(comentarioRepository.findById(comentarioId)).thenReturn(Optional.of(mockComentarioEntity));

        assertThrows(SecurityException.class, () -> {
            comentarioService.editarComentario(comentarioId, updateRequest, usernameInvasor);
        });

        verify(comentarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("curtir um comentário pela primeira vez")
    void toggleCurtir() {


        when(comentarioRepository.findById(100L)).thenReturn(Optional.of(mockComentarioEntity));
        when(usuarioRepository.findByLogin("Isaias")).thenReturn(Optional.of(mockUsuario));
        when(comentarioRepository.save(any())).thenReturn(mockComentarioEntity);
        when(comentarioMapper.toResponseDTO(any(), any())).thenReturn(mockResponseDTO);

        comentarioService.toggleCurtir(100L, "Isaias");
        assertEquals(1, mockComentarioEntity.getLikeBy().size());
        //assertFalse(mockComentarioEntity.getLikeBy().contains(mockUsuario));
    }

    @Test
    @DisplayName("Garantir que toggleCurtir lança EntityNotFoundException se o comentário não existir")
    void toggleCurtir_DeveLancarExcecao_QuandoComentarioNaoEncontrado() {
        Long idComentarioFantasma = 99L;
        String username = "Isaias";

        when(comentarioRepository.findById(idComentarioFantasma)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            comentarioService.toggleCurtir(idComentarioFantasma, username);
        });

        verify(usuarioRepository, never()).findByLogin(anyString());
        verify(comentarioRepository, never()).save(any());
    }
    @Test
    @DisplayName("Garantir que toggleCurtir lança EntityNotFoundException se o usuário não existir")
    void toggleCurtir_DeveLancarExcecao_QuandoUsuarioNaoEncontrado() {

        Long comentarioId = 100L;
        String usernameFantasma = "fantasma";

        when(comentarioRepository.findById(comentarioId)).thenReturn(Optional.of(mockComentarioEntity));
        when(usuarioRepository.findByLogin(usernameFantasma)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            comentarioService.toggleCurtir(comentarioId, usernameFantasma);
        });

        verify(comentarioRepository, times(1)).findById(comentarioId);
        verify(usuarioRepository, times(1)).findByLogin(usernameFantasma);
        verify(comentarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("pode descurtir um comentário")
    void descurtir() {

        mockComentarioEntity.getLikeBy().add(mockUsuario);

        when(comentarioRepository.findById(100L)).thenReturn(Optional.of(mockComentarioEntity));
        when(usuarioRepository.findByLogin("Isaias")).thenReturn(Optional.of(mockUsuario));
        when(comentarioRepository.save(any())).thenReturn(mockComentarioEntity);
        when(comentarioMapper.toResponseDTO(any(), any())).thenReturn(mockResponseDTO);

        comentarioService.toggleCurtir(100L, "Isaias");
        assertEquals(0, mockComentarioEntity.getLikeBy().size());

    }

    @Test
    @DisplayName("verificar se esse comentário pai existe para adicianar um comentario")
    void adicionarComentarioSemPai() {

        mockRequestDTO.setParentId(99L);

        when(usuarioRepository.findByLogin(anyString())).thenReturn(Optional.of(mockUsuario));
        when(publicacaoRepository.findById(anyLong())).thenReturn(Optional.of(mockPublicacao));
        when(comentarioMapper.toEntity(mockRequestDTO)).thenReturn(mockComentarioEntity);
        when(comentarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            comentarioService.adicionarComentario(10L, mockRequestDTO, "Isaias");
        });
        verify(comentarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve listar os comentários de uma publicação (ordenado por RELEVANCIA)")
    void listarComentariosPublicacao() {

        Long publicacaoId = 10L;
        String username = "Isaias";
        Pageable pageable = PageRequest.of(0, 10);

        List<ComentarioEntity> listaDeComentario = List.of(mockComentarioEntity);
        Page<ComentarioEntity> mockPage = new PageImpl<>(listaDeComentario, pageable, 1);

        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockUsuario));
        when(comentarioRepository.findByPublicacaoIdAndParentIsNullOrderByRelevancia(publicacaoId, pageable)).thenReturn(mockPage);
        when(comentarioMapper.toResponseDTO(mockComentarioEntity, mockUsuario)).thenReturn(mockResponseDTO);

        Page<ComentarioResponseDTO> result = comentarioService.listarComentariosPublicacao(
                publicacaoId, Ordenacao.RELEVANCIA, pageable, username
        );

        assertNotNull(result);
        assertEquals(1, result.getTotalElements()); // A página tem 1 elemento
        assertEquals(100L, result.getContent().get(0).getId()); // O ID do comentário está correto

        verify(comentarioRepository, times(1)).findByPublicacaoIdAndParentIsNullOrderByRelevancia(anyLong(), any());
        verify(comentarioRepository, never()).findByPublicacaoIdAndParentIsNullOrderByDataCriacaoDesc(anyLong(), any());

    }

    @Test
    @DisplayName("Deve listar as respostas de um comentário pai")
    void listarRespostasComentario() {

        Long parentId = 100L;
        String username =  "Isaias";
        Pageable pageable = PageRequest.of(0, 10);

        ComentarioEntity mockRespostaEntity = new ComentarioEntity();
        mockRespostaEntity.setId(101L);
        mockRespostaEntity.setTexto("Esta é uma resposta");

        ComentarioResponseDTO mockRespostaDTO = new ComentarioResponseDTO();
        mockRespostaDTO.setId(101L);

        Page<ComentarioEntity> mockPageDeRespostas = new PageImpl<>(List.of(mockRespostaEntity), pageable, 1);
        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockUsuario));
        when(comentarioRepository.findByParentIdOrderByDataCriacaoAsc(parentId, pageable))
                .thenReturn(mockPageDeRespostas);

        when(comentarioMapper.toResponseDTO(mockRespostaEntity, mockUsuario)).thenReturn(mockRespostaDTO);
        Page<ComentarioResponseDTO> result = comentarioService.listarRespostasComentario(parentId, pageable, username);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements()); // A página tem 1 resposta
        assertEquals(101L, result.getContent().get(0).getId()); // O ID da resposta está correto

        verify(comentarioRepository, times(1)).findByParentIdOrderByDataCriacaoAsc(parentId, pageable);
        verify(comentarioMapper, times(1)).toResponseDTO(mockRespostaEntity, mockUsuario);

    }

    @Test
    @DisplayName("garantir o ordenar por RELEVANCIA")
    void listarComentariosPublicacao_DeveRetornarPagina_QuandoOrdenadoPorRelevancia() {

        Long publicacaoId = 10L;
        String username = "Isaias";
        Pageable pageable = PageRequest.of(0, 10);

        List<ComentarioEntity> listaDeComentarios = List.of(mockComentarioEntity);
        Page<ComentarioEntity> mockPage = new PageImpl<>(listaDeComentarios, pageable, 1);

        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.of(mockUsuario));

        when(comentarioRepository.findByPublicacaoIdAndParentIsNullOrderByRelevancia(publicacaoId, pageable)).thenReturn(mockPage);

        when(comentarioMapper.toResponseDTO(mockComentarioEntity, mockUsuario)).thenReturn(mockResponseDTO);

        Page<ComentarioResponseDTO> result = comentarioService.listarComentariosPublicacao(
                publicacaoId, Ordenacao.RELEVANCIA, pageable, username
        );

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());

        verify(comentarioRepository, never()).findByPublicacaoIdAndParentIsNullOrderByDataCriacaoDesc(anyLong(), any());
        verify(comentarioRepository, times(1)).findByPublicacaoIdAndParentIsNullOrderByRelevancia(anyLong(), any());
    }

    @Test
    @DisplayName("Deve adicionar um comentário com menção a um usuário")
    void adicionarComentario_DeveSalvarComUsuarioMencionado() {

        Long idUsuarioMencionado = 5L;
        mockRequestDTO.setUsuarioMencionadoId(idUsuarioMencionado);

        UsuarioEntity mockUsuarioMencionado = new UsuarioEntity();
        mockUsuarioMencionado.setId(idUsuarioMencionado);
        mockUsuarioMencionado.setLogin("maria");

        when(usuarioRepository.findByLogin("Isaias")).thenReturn(Optional.of(mockUsuario));
        when(publicacaoRepository.findById(10L)).thenReturn(Optional.of(mockPublicacao));
        when(comentarioMapper.toEntity(mockRequestDTO)).thenReturn(mockComentarioEntity);
        when(comentarioRepository.save(any(ComentarioEntity.class))).thenReturn(mockComentarioEntity);
        when(comentarioMapper.toResponseDTO(any(ComentarioEntity.class), eq(mockUsuario))).thenReturn(mockResponseDTO);
        when(usuarioRepository.findById(idUsuarioMencionado)).thenReturn(Optional.of(mockUsuarioMencionado));

        comentarioService.adicionarComentario(10L, mockRequestDTO, "Isaias");

        verify(usuarioRepository, times(1)).findById(idUsuarioMencionado);
        assertEquals(mockUsuarioMencionado, mockComentarioEntity.getUsuarioMencionado());
        verify(comentarioRepository, times(1)).save(mockComentarioEntity);
    }

    @Test
    @DisplayName("Deve listar comentários (ordenado por RELEVANCIA) para usuário deslogado")
    void listarComentariosPublicacao_DeveFuncionarComUsuarioNulo() {

        Long publicacaoId = 10L;
        Pageable pageable = PageRequest.of(0, 10);
        Page<ComentarioEntity> mockPage = new PageImpl<>(List.of(mockComentarioEntity), pageable, 1);

        when(comentarioRepository.findByPublicacaoIdAndParentIsNullOrderByRelevancia(publicacaoId, pageable)).thenReturn(mockPage);
        when(comentarioMapper.toResponseDTO(mockComentarioEntity, null)).thenReturn(mockResponseDTO);

        Page<ComentarioResponseDTO> result = comentarioService.listarComentariosPublicacao(
                publicacaoId, Ordenacao.RELEVANCIA, pageable, null
        );

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());

        verify(usuarioRepository, never()).findByLogin(anyString());
        verify(comentarioMapper, times(1)).toResponseDTO(mockComentarioEntity, null);
    }
}