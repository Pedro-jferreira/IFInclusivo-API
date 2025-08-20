package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;

public interface AuthService {

    UsuarioOutputDTO authenticate(String email, String password);

    SimpleUsuarioDTO register(UsuarioInputDTO user);

    void verificationToken(String token);

    void logout();
}
