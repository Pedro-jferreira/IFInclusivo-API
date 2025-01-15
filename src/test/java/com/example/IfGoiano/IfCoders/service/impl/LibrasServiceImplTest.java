package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.mapper.mocks.MockLibras;
import com.example.IfGoiano.IfCoders.mapper.mocks.MockUser;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LibrasServiceImplTest {

    MockUser inputUser = new MockUser();

    MockLibras inputLibras = new MockLibras();


    @Mock
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;


    @Mock
    private LibrasRepository repository;

    @Mock
    private LibrasMapper mapper;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private LibrasServiceImpl librasService;


    /**
     * Verifico inicialmente se ao não encontrar um usuario me retorna uma exeção
     */
    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        Long idUser = 1L;
        LibrasInputDTO input = new LibrasInputDTO();
        Mockito.when(usuarioService.findById(idUser)).thenReturn(null);// aqui eu ja espero um retorno null

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> librasService.sugereLibras(input, idUser));

        Mockito.verify(usuarioService).findById(idUser);
        Mockito.verifyNoInteractions(repository, mapper, usuarioMapper);
    }


    //CASE1 Existe um usuario no banco de dados, ESSA LIBRAS AINDA NÂO EXISTE, E SE EXISTE ESTA COM O STATUS-EM-ANALISE
    //LOGO PODE SE SUGERIR

    @Test
    @DisplayName("Should sugest Libras Sucess")
    void shouldCreateNewLibrasWhenNotFound() {

        UsuarioEntity usuarioEntity = this.inputUser.mockUser(1L); //criando um user e mockando
        UsuarioEntity expected = usuarioEntity;
        //when(this.usuarioRepository.save(usuarioEntity)).thenReturn(expected);

        UsuarioOutputDTO usuarioOutputDTO = new UsuarioOutputDTO();
        usuarioOutputDTO.setId(1L);

        LibrasInputDTO input = new LibrasInputDTO();
        input.setPalavra("example");

        LibrasEntity librasEntity = new LibrasEntity();
        librasEntity.setId(1L);
        librasEntity.setStatus(Status.EMANALISE);


        LibrasEntity savedEntity = new LibrasEntity();
        savedEntity.setId(1L);

        LibrasOutputDTO output = new LibrasOutputDTO();
        output.setId(1L);
        output.setStatus(Status.EMANALISE);

        // Configuração dos mocks
        Mockito.when(usuarioService.findById(1L)).thenReturn(usuarioOutputDTO);
        Mockito.when(repository.findByPalavra("example")).thenReturn(Optional.empty());
        Mockito.when(mapper.toLibrasEntity(input)).thenReturn(librasEntity);
        Mockito.when(repository.save(librasEntity)).thenReturn(savedEntity);
        Mockito.when(repository.findById(savedEntity.getId())).thenReturn(Optional.of(librasEntity));
        Mockito.when(mapper.toLibrasOutputDTO(librasEntity)).thenReturn(output);

        // Execução
        LibrasOutputDTO result = librasService.sugereLibras(input, 1L);
        result.setStatus(Status.EMANALISE);

        Mockito.verify(repository).findByPalavra("example");
        Mockito.verify(mapper).toLibrasEntity(input);
        Mockito.verify(repository).save(librasEntity);

        // Verificações
        Assertions.assertEquals(output.getStatus(), result.getStatus());
        System.out.println("Status do outuput:"+output.getStatus()+ "Status do result " + result.getStatus());

    }

}