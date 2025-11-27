package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerifyLoginAndRegistrationTest {

    @InjectMocks
    private VerifyLoginAndRegistration verifyService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve retornar TRUE se o Login já existir")
    void existsByLogin_ShouldReturnTrue_WhenLoginExists() {

        String login = "teste@email.com";
        Long matricula = 123L;

        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.of(new UsuarioEntity()));

        boolean result = verifyService.existsByLogin(login, matricula);

        assertTrue(result, "Deveria retornar true pois o login existe");
    }

    @Test
    @DisplayName("Deve retornar TRUE se a Matrícula já existir (mesmo com login novo)")
    void existsByLogin_ShouldReturnTrue_WhenMatriculaExists() {

        String login = "novo@email.com";
        Long matricula = 123L;

        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.empty());
        when(usuarioRepository.findByMatricula(matricula)).thenReturn(Optional.of(new UsuarioEntity()));

        boolean result = verifyService.existsByLogin(login, matricula);

        assertTrue(result, "Deveria retornar true pois a matrícula existe");
    }

    @Test
    @DisplayName("Deve retornar TRUE se AMBOS (Login e Matrícula) já existirem")
    void existsByLogin_ShouldReturnTrue_WhenBothExist() {
        String login = "existente@email.com";
        Long matricula = 123L;

        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.of(new UsuarioEntity()));

        boolean result = verifyService.existsByLogin(login, matricula);

        assertTrue(result);
    }

    @Test
    @DisplayName("Deve retornar FALSE se nem Login nem Matrícula existirem (Caminho Feliz para Cadastro)")
    void existsByLogin_ShouldReturnFalse_WhenNoneExists() {

        String login = "novo@email.com";
        Long matricula = 999L;

        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.empty());
        when(usuarioRepository.findByMatricula(matricula)).thenReturn(Optional.empty());

        boolean result = verifyService.existsByLogin(login, matricula);

        assertFalse(result, "Deveria retornar false pois é um usuário totalmente novo");
    }
}