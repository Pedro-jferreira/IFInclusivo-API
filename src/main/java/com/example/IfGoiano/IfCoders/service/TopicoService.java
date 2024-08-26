package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;

import java.util.List;

public interface TopicoService {
    TopicoOutputDTO save(TopicoInputDTO topicoInputDTO);

    TopicoOutputDTO update(Long id, TopicoInputDTO topicoDetails);

    void delete(Long id);

    TopicoOutputDTO findById(Long id);

    List<TopicoOutputDTO> findAll();
}
