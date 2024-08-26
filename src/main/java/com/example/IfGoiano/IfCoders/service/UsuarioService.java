package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioOutputDTO> findAll();

    UsuarioOutputDTO findById(Long id);

    UsuarioOutputDTO save(UsuarioInputDTO usuario);

    UsuarioOutputDTO update(Long id, UsuarioInputDTO usuarioDetails);

    void delete(Long id);
}
