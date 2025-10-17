package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoResponseDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PublicacaoService {
    PublicacaoResponseDTO findById(Long id, String username);

    PublicacaoResponseDTO save(PublicacaoRequestDTO publicacao, String username);

    Page<PublicacaoResponseDTO> findAll(
            Set<Categorias> categorias,
            Ordenacao ordenarPor,
            Pageable pageable,
            String username
    );

    @Transactional
    PublicacaoResponseDTO update(Long id, PublicacaoRequestDTO publicacaoDetails, String username);


    @Transactional
    void delete(Long id, String username);

}
