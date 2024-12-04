package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PublicacaoService {
    List<PublicacaoOutputDTO> findAll();

    PublicacaoOutputDTO findById(Long id);

    PublicacaoOutputDTO save(Long IdUser,PublicacaoInputDTO publicacao);

    PublicacaoOutputDTO update(Long id, PublicacaoInputDTO publicacaoDetails);

    Page<PublicacaoOutputDTO> searchPublicacaoByTermQuickly(String termo, int pagina, int tamanho);

    Page<PublicacaoOutputDTO> searchPublicacaoByTermDeeply(String termo, int pagina, int tamanho);

    void delete(Long id);

    void setTopico(PublicacaoOutputDTO publicacaoOutputDTO);

    Page<PublicacaoOutputDTO> findPublicacaobyTopico(Long idTopico, int pagina, int tamanho);
}
