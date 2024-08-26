package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioOutputDTO save(UsuarioInputDTO topicoInputDTO);

    UsuarioOutputDTO update(Long id, UsuarioInputDTO topicoDetails);

    void delete(Long id);

    UsuarioOutputDTO findById(Long id);

    List<UsuarioOutputDTO> findAll();
}
