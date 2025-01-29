package com.example.IfGoiano.IfCoders.service.impl;


import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PublicacaoServiceImplTest {


    @Mock
    private PublicacaoRepositoy repositoy;

    @Mock
    private PublicacaoMapper mapper;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private PublicacaoServiceImpl service;

    private PublicacaoEntity publicacaoEntity;
    private PublicacaoOutputDTO publicacaoOutputDTO;
    private PublicacaoInputDTO publicacaoInputDTO;
    private UsuarioEntity usuarioEntity;

    @BeforeEach
    void setUp() {
        publicacaoEntity = new PublicacaoEntity();

        publicacaoEntity.setId(1L);

        publicacaoOutputDTO = new PublicacaoOutputDTO();

        publicacaoInputDTO = new PublicacaoInputDTO();

        usuarioEntity = new UsuarioEntity();
    }

    @Test
    void findAll_ShouldReturnListOfPublicacoes() {
        when(repositoy.findAll()).thenReturn(List.of(publicacaoEntity));
        when(mapper.toPublicacaoOutputDTO(any())).thenReturn(publicacaoOutputDTO);

        List<PublicacaoOutputDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repositoy, times(1)).findAll();
    }

    @Test
    void findById_WhenFound_ShouldReturnPublicacao() {
        when(repositoy.findById(1L)).thenReturn(Optional.of(publicacaoEntity));
        when(mapper.toPublicacaoOutputDTO(any())).thenReturn(publicacaoOutputDTO);

        PublicacaoOutputDTO result = service.findById(1L);

        assertNotNull(result);
        verify(repositoy, times(1)).findById(1L);
    }

    @Test
    void findById_WhenNotFound_ShouldThrowException() {
        when(repositoy.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void save_ShouldReturnSavedPublicacao() {
        when(usuarioService.findById(any())).thenReturn(any());
        when(mapper.toPublicacaoEntity((SimplePublicacaoDTO) any())).thenReturn(publicacaoEntity);
        when(repositoy.save(any())).thenReturn(publicacaoEntity);
        when(mapper.toPublicacaoOutputDTO(any())).thenReturn(publicacaoOutputDTO);

        PublicacaoOutputDTO result = service.save(1L, publicacaoInputDTO);

        assertNotNull(result);
        verify(repositoy, times(1)).save(any());
    }

    @Test
    void delete_ShouldDeletePublicacao() {
        when(repositoy.findById(1L)).thenReturn(Optional.of(publicacaoEntity));
        when(mapper.toPublicacaoEntity((SimplePublicacaoDTO) any())).thenReturn(publicacaoEntity);

        service.delete(1L);

        verify(repositoy, times(1)).delete(any());
    }

    @Test
    void searchPublicacaoByTermQuickly_ShouldReturnPagedResult() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PublicacaoEntity> page = new PageImpl<>(List.of(publicacaoEntity));

        when(repositoy.searchPublicacaoByTermQuickly(anyString(), any())).thenReturn(page);
        when(mapper.toPublicacaoOutputDTO(any())).thenReturn(publicacaoOutputDTO);

        Page<PublicacaoOutputDTO> result = service.searchPublicacaoByTermQuickly("test", 0, 10);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void update_WhenFound_ShouldReturnUpdatedPublicacao() {
        when(repositoy.findById(1L)).thenReturn(Optional.of(publicacaoEntity));
        when(mapper.toPublicacaoOutputDTO(any())).thenReturn(publicacaoOutputDTO);
        when(repositoy.save(any())).thenReturn(publicacaoEntity);

        PublicacaoOutputDTO result = service.update(1L, publicacaoInputDTO);

        assertNotNull(result);
        verify(repositoy, times(1)).save(any());
    }

    @Test
    void update_WhenNotFound_ShouldThrowException() {
        when(repositoy.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.update(1L, publicacaoInputDTO));
    }
}