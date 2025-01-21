package com.example.IfGoiano.IfCoders.mapper.mocks;

import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;

public class MockUser {

    public UsuarioEntity mockUser(Long number) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setLogin("Firs login" + number);
        usuario.setSenha("Firs senha" + number);
        usuario.setNome("Firs nome" + number);
        usuario.setId(number);
        usuario.setBiografia("Firs biografia" + number);
        usuario.setMatricula(number);

        return usuario;
    }
}
