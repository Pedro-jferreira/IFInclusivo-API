package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoDTO;

import java.util.List;

public interface PublicacaoService {
    List<PublicacaoDTO> findAll();

    PublicacaoDTO findById(Long id);

    PublicacaoDTO save(PublicacaoDTO publicacao);

    PublicacaoDTO update(Long id, PublicacaoDTO publicacaoDetails);

    void delete(Long id);
}
