package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;

import java.util.List;

public interface PublicacaoService {
    List<PublicacaoOutputDTO> findAll();

    PublicacaoOutputDTO findById(Long id);

    PublicacaoOutputDTO save(PublicacaoInputDTO publicacao);

    PublicacaoOutputDTO update(Long id, PublicacaoInputDTO publicacaoDetails);

    void delete(Long id);
}
