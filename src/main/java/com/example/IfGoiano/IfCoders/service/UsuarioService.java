package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
    public List<UsuarioEntity> findAll();

    public UsuarioEntity findById(Long id);

    public UsuarioEntity save(UsuarioEntity usuario);

    public UsuarioEntity update(Long id, UsuarioEntity usuarioDetails);

    void delete(Long id);
}
