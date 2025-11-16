package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao tentar salvar com título nulo ou vazio")
    void save_ShouldThrowIllegalArgument_WhenTitleIsBlank() {

        mockRequestDTO.setTitulo(""); // Título vazio
        when(usuarioRepository.findByLogin("Isaias")).thenReturn(Optional.of(mockUsuario));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.save(mockRequestDTO, "Isaias");
        });

        assertEquals("O título é obrigatório para uma nova publicação.", exception.getMessage());

        verify(repositoy, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao tentar atualizar publicação inexistente")
    void update_ShouldThrowResourceNotFound_WhenPublicationDoesNotExist() {

        String username = "Isaias";
        Long pubId = 99L;

        when(repositoy.findById(pubId)).thenReturn(Optional.empty()); // Publicação não encontrada

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(pubId, mockRequestDTO, username);
        });

        verify(usuarioRepository, never()).findByLogin(anyString());
        verify(repositoy, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao tentar curtir publicação inexistente")
    void toggleLike_ShouldThrowResourceNotFound_WhenPublicationDoesNotExist() {

        String username = "Isaias";
        Long pubId = 99L;

        when(repositoy.findById(pubId)).thenReturn(Optional.empty()); // Simula que a publicação NÃO existe

        assertThrows(ResourceNotFoundException.class, () -> {
            service.toggleLike(pubId, username);
        });

        verify(repositoy, never()).save(any(PublicacaoEntity.class));
        verify(usuarioRepository, never()).save(any(UsuarioEntity.class));
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao buscar publicações de usuário inexistente")
    void findPublicacoesByUserId_ShouldThrowResourceNotFound_WhenUserDoesNotExist() {

        Long userId = 99L; // usuário Inexistente
        Pageable pageable = PageRequest.of(0, 10);

        when(usuarioRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.findPublicacoesByUserId(pageable, userId);
        });

        verify(repositoy, never()).findPublicacaoEntitiesByUsuarioOrderByDataCriacaoDesc(any(), any());
    }

    @Test
    @DisplayName("Deve deletar publicação e limpar likes de outros usuários")
    void delete_ShouldSucceed_WhenPublicationHasLikes() {

        String usernameDono = "Isaias";
        Long pubId = 10L;

        UsuarioEntity outroUsuarioComLike = new UsuarioEntity();
        outroUsuarioComLike.setId(2L);
        outroUsuarioComLike.setLikes(new HashSet<>());

        mockPublicacaoEntity.getLikeBy().add(outroUsuarioComLike);
        outroUsuarioComLike.getLikes().add(mockPublicacaoEntity);

        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));
        when(usuarioRepository.findByLogin(usernameDono)).thenReturn(Optional.of(mockUsuario));

        assertDoesNotThrow(() -> {
            service.delete(pubId, usernameDono);
        });

        assertTrue(outroUsuarioComLike.getLikes().isEmpty(), "O like não foi removido do outro usuário");
        assertTrue(mockPublicacaoEntity.getLikeBy().isEmpty(), "O like não foi removido da publicação");
        // Verifica se a publicação foi deletada
        verify(repositoy, times(1)).delete(mockPublicacaoEntity);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao tentar ATUALIZAR com usuário inexistente")
    void update_ShouldThrowResourceNotFound_WhenUserDoesNotExist() {

        String username = "usuario.fantasma";
        Long pubId = 10L;

        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));// A publicação EXISTE
        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.empty());// Mas o usuário NÃO EXISTE

        assertThrows(ResourceNotFoundException.class, () -> {
            service.update(pubId, mockRequestDTO, username);
        });
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao tentar DELETAR com usuário inexistente")
    void delete_ShouldThrowResourceNotFound_WhenUserDoesNotExist() {

        String username = "usuario.fantasma";
        Long pubId = 10L;

        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));
        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(pubId, username);
        });
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ao tentar CURTIR com usuário inexistente")
    void toggleLike_ShouldThrowResourceNotFound_WhenUserDoesNotExist() {

        String username = "usuario.fantasma";
        Long pubId = 10L;

        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));
        when(usuarioRepository.findByLogin(username)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.toggleLike(pubId, username);
        });
    }

    @Test
    @DisplayName("Deve retornar publicações ordenadas por MAIS_RECENTE")
    void findAll_ShouldSortByMaisRecente() {
        Pageable pageable = PageRequest.of(0, 10);

        Pageable pageableEsperado = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "dataCriacao"));

        when(repositoy.findAll(any(Specification.class), eq(pageableEsperado)))
                .thenReturn(new PageImpl<>(List.of(mockPublicacaoEntity)));

        service.findAll(null, Ordenacao.MAIS_RECENTE, pageable, null, null);

        verify(repositoy).findAll(any(Specification.class), eq(pageableEsperado));
    }

    @Test
    @DisplayName("Deve retornar publicações para um usuário anônimo (username null)")
    void findAll_ShouldWorkForAnonymousUser() {
        Pageable pageable = PageRequest.of(0, 10);
        when(repositoy.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(mockPublicacaoEntity)));
        when(mapper.toDetalhadaDTO(mockPublicacaoEntity, null)).thenReturn(mockResponseDTO);//usuário deslogado chamando publi

        Page<PublicacaoResponseDTO> result = null;

        result = service.findAll(null, Ordenacao.RELEVANCIA, pageable, null, null); // username é null

        assertNotNull(result);
        assertFalse(result.isEmpty());

        verify(usuarioRepository, never()).findByLogin(any());// Verifica se o findByLogin nao foi chamado
        verify(mapper).toDetalhadaDTO(mockPublicacaoEntity, null);
    }

    @Test
    @DisplayName("Deve retornar página vazia se usuário não tiver publicações")
    void findPublicacoesByUserId_ShouldReturnEmptyPage_WhenUserHasNoPublications() {

        Long userId = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(mockUsuario));
        when(repositoy.findPublicacaoEntitiesByUsuarioOrderByDataCriacaoDesc(mockUsuario, pageable))
                .thenReturn(Page.empty());// retorna uma PÁGINA VAZIA

        Page<PublicacaoResponseDTO> result = service.findPublicacoesByUserId(pageable, userId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.getTotalElements());
    }
    @Test
    @DisplayName("Deve ordenar por RELEVANCIA corretamente (testando calcularRelevancia)")
    void findAll_ShouldSortByRelevanciaCorrectly() {

        Pageable pageable = PageRequest.of(0, 10);

        // Publicação antiga, resolvida, 1 like
        PublicacaoEntity pubAntiga = new PublicacaoEntity();
        pubAntiga.setId(1L);
        pubAntiga.setTipo(com.example.IfGoiano.IfCoders.entity.Enums.TipoPublicacao.DICA);
        pubAntiga.setLikeBy(Set.of(mockOutroUsuario)); // 1 like
        pubAntiga.setComentarios(List.of()); // 0 comentários
        pubAntiga.setDataCriacao(java.time.LocalDateTime.now().minusDays(5)); // Antiga

        // Publicação nova, dúvida pendente, 2 likes, 1 comentário
        PublicacaoEntity pubNova = new PublicacaoEntity();
        pubNova.setId(2L);
        pubNova.setTipo(com.example.IfGoiano.IfCoders.entity.Enums.TipoPublicacao.DUVIDA);
        pubNova.setStatus(com.example.IfGoiano.IfCoders.entity.Enums.StatusPublicacao.PENDENTE);
        pubNova.setLikeBy(Set.of(mockUsuario, mockOutroUsuario)); // 2 likes

        ComentarioEntity mockComentario = mock(ComentarioEntity.class);
        pubNova.setComentarios(List.of(mockComentario)); // 1 comentário
        pubNova.setDataCriacao(java.time.LocalDateTime.now().minusHours(1)); // Recente

        // Lista na ordem errada (antiga, nova)
        List<PublicacaoEntity> publicacoesDesordenadas = List.of(pubAntiga, pubNova);
        Page<PublicacaoEntity> mockPage = new PageImpl<>(publicacoesDesordenadas, pageable, 2);


        when(repositoy.findAll(any(Specification.class), any(Pageable.class))).thenReturn(mockPage);
        when(mapper.toDetalhadaDTO(eq(pubAntiga), any())).thenReturn(new PublicacaoResponseDTO());
        when(mapper.toDetalhadaDTO(eq(pubNova), any())).thenReturn(new PublicacaoResponseDTO());

        Page<PublicacaoResponseDTO> result = service.findAll(null, Ordenacao.RELEVANCIA, pageable, null, null);

        PublicacaoResponseDTO dtoAntiga = new PublicacaoResponseDTO();
        dtoAntiga.setId(1L);
        PublicacaoResponseDTO dtoNova = new PublicacaoResponseDTO();
        dtoNova.setId(2L);

        when(mapper.toDetalhadaDTO(pubAntiga, null)).thenReturn(dtoAntiga);
        when(mapper.toDetalhadaDTO(pubNova, null)).thenReturn(dtoNova);

        result = service.findAll(null, Ordenacao.RELEVANCIA, pageable, null, null);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        // Garante que a publicação Nova veio antes da antiga
        assertEquals(2L, result.getContent().get(0).getId());
        assertEquals(1L, result.getContent().get(1).getId());
    }
    @Test
    @DisplayName("Deve retornar publicacao para usuário anônimo")
    void findById_ShouldWorkForAnonymousUser() {

        Long pubId = 10L;

        when(repositoy.findById(pubId)).thenReturn(Optional.of(mockPublicacaoEntity));
        when(mapper.toDetalhadaDTO(mockPublicacaoEntity, null)).thenReturn(mockResponseDTO);

        PublicacaoResponseDTO result = service.findById(pubId, null); // username é null

        assertNotNull(result);
        assertEquals(10L, result.getId());

        verify(usuarioRepository, never()).findByLogin(any());
        verify(mapper).toDetalhadaDTO(mockPublicacaoEntity, null);
    }

    @Test
    @DisplayName("Deve sugerir titulos filtrando por query e categorias")
    void sugerirTitulos_ShouldFilterByQueryAndCategories() {

        String query = "teste";
        Set<Categorias> categorias = Set.of(Categorias.WEB, Categorias.PROGRAMACAO);
        mockPublicacaoEntity.setTitulo("Este é um título de teste");

        when(repositoy.findByTituloAndOptionalCategorias(query, categorias)).thenReturn(List.of(mockPublicacaoEntity));

        List<String> result = service.sugerirTitulos(query, categorias);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Este é um título de teste", result.get(0));

        verify(repositoy).findByTituloAndOptionalCategorias(query, categorias);
    }

    @Test
    @DisplayName("Deve filtrar por query E categorias no método findAll")
    void findAll_ShouldFilterByQueryAndCategories() {

        Pageable pageable = PageRequest.of(0, 10);
        String query = "teste";
        Set<Categorias> categorias = Set.of(Categorias.WEB);

        // Simula o repositório retornando uma página
        when(repositoy.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(mockPublicacaoEntity)));

        service.findAll(categorias, Ordenacao.MAIS_RECENTE, pageable, query, null);

        verify(repositoy, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }
    @Test
    @DisplayName("Deve retornar lista vazia ao sugerir títulos e não encontrar nada")
    void sugerirTitulos_ShouldReturnEmptyList_WhenNotFound() {
        String query = "query.inexistente";

        when(repositoy.findByTituloAndOptionalCategorias(query, null)).thenReturn(List.of()); // Retorna lista vazia

        List<String> result = service.sugerirTitulos(query, null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    @DisplayName("Deve filtrar por query E ordenar por MAIS_RECENTE corretamente")
    void findAll_ShouldFilterByQueryAndSortByMaisRecente() {

        Pageable pageable = PageRequest.of(0, 10); // Pageable original
        String query = "teste";

        Pageable pageableEsperado = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "dataCriacao"));

        when(repositoy.findAll(any(Specification.class), eq(pageableEsperado))).thenReturn(new PageImpl<>(List.of(mockPublicacaoEntity)));

        service.findAll(null, Ordenacao.MAIS_RECENTE, pageable, query, null);

        // Verifica se o repositório foi chamado com o Pageable COM SORT
        verify(repositoy, times(1)).findAll(any(Specification.class), eq(pageableEsperado));
    }

    @Test
    @DisplayName("Deve retornar página vazia no findAll se nenhuma publicação for encontrada")
    void findAll_ShouldReturnEmptyPage_WhenNoPublicationsMatch() {

        Pageable pageable = PageRequest.of(0, 10);

        // retornando uma PÁGINA VAZIA
        when(repositoy.findAll(any(Specification.class), any(Pageable.class))).thenReturn(Page.empty());

        Page<PublicacaoResponseDTO> result = service.findAll(null, Ordenacao.MAIS_RECENTE, pageable, null, null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.getTotalElements());
        verify(mapper, never()).toDetalhadaDTO(any(), any());
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException no findById para usuário anônimo se publicação não existir")
    void findById_ShouldThrowResourceNotFound_WhenPublicationDoesNotExistAndUserIsAnonymous() {

        Long pubId = 99L;
        String username = null; // Usuário anônimo

        when(repositoy.findById(pubId)).thenReturn(Optional.empty()); // Publicação não existe

        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(pubId, username);
        });

        verify(usuarioRepository, never()).findByLogin(any()); // Confirma que não tentou logar
    }
}