package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;

import java.util.List;

public interface ComentarioService {
    List<ComentarioOutputDTO> findAll();

    ComentarioOutputDTO findById(Long id);

    ComentarioOutputDTO save(Long idUser,Long idPublicacao,ComentarioInputDTO comentarioInputDTO);

    ComentarioOutputDTO update(Long id, ComentarioInputDTO comentarioDeitails);

    void delete(Long id);
}
