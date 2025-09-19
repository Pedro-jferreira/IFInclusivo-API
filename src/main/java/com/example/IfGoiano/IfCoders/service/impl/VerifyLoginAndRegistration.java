package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class VerifyLoginAndRegistration {

    private final UsuarioRepository repository;

    public VerifyLoginAndRegistration(UsuarioRepository repository) {
        this.repository = repository;
    }

    public boolean existsByLogin(String login, Long matricula) {
       if(this.repository.findByLogin(login).isPresent() || this.repository.findByMatricula(matricula).isPresent()){
          return true;
       }
       return false;
    }
}
