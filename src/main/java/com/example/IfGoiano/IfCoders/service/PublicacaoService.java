package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoRequestDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoCompletaDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoDetalhadaDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Ordenacao;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface PublicacaoService {
    PublicacaoCompletaDTO findById(Long id,String username);

    PublicacaoDetalhadaDTO save(PublicacaoRequestDTO publicacao, String username);

    Page<PublicacaoDetalhadaDTO> findAll(
            Set<Categorias> categorias,
            Ordenacao ordenarPor,
            Pageable pageable,
            String username
    );

    @Transactional
    PublicacaoDetalhadaDTO update(Long id, PublicacaoRequestDTO publicacaoDetails, String username);


    @Transactional
    void delete(Long id, String username);

    @Transactional()
    Page<PublicacaoDetalhadaDTO> findFilhosById(
            Long publicacaoId,
            Ordenacao ordenarPor,
            Pageable pageable,
            String username);
}
