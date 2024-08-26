package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;

import java.util.List;

public interface PublicacaoService {

    PublicacaoOutputDTO save(PublicacaoInputDTO publicacao);

    PublicacaoOutputDTO update(Long id, PublicacaoInputDTO publicacaoDetails);

    void delete(Long id);

    PublicacaoOutputDTO findById(Long id);

    List<PublicacaoOutputDTO> findAll();

}
