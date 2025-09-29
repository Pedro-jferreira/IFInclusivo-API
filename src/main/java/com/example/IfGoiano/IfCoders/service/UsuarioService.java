package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.UsuarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Role;

import java.util.List;

public interface UsuarioService {
    List<UsuarioOutputDTO> findAll();

    UsuarioOutputDTO findById(Long id);

    UsuarioOutputDTO save(UsuarioInputDTO usuarioId, Long idConfigAc);

    UsuarioOutputDTO update(UsuarioInputDTO usuarioDaiteils, Long id);
    List<UsuarioOutputDTO> searchUsers(String name, Role role);


    void delete(Long id);

    boolean existsById(Long id);

}
