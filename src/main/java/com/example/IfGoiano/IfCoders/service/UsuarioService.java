package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioOutputDTO> findAll();

    UsuarioOutputDTO findById(Long id);

    UsuarioOutputDTO save(UsuarioInputDTO usuarioId);

    UsuarioOutputDTO update(UsuarioInputDTO usuarioDaiteils, Long id);

    void delete(Long id);

    boolean existsById(Long id);

}
