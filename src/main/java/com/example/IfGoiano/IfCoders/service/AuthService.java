package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import jakarta.transaction.Transactional;

public interface AuthService {

    UsuarioOutputDTO authenticate(String email, String password);

    SimpleUsuarioDTO register(UsuarioInputDTO user);

    void verificationToken(String token);

    void forgotPassword(String email);

    @Transactional
    void resetPassword(String token, String novaSenha);

    @Transactional
    void updatePassword(String email, String senhaAtual, String novaSenha);

    @Transactional
    void deleteUser(String email, String password);

    void logout();
    void resendConfirmationEmailFromExpiredToken(String expiredToken);
}
