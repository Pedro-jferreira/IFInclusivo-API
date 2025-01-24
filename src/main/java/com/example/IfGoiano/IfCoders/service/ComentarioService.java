package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.PublicacaoOutputDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ComentarioService {
    List<ComentarioOutputDTO> findAll();

    ComentarioOutputDTO findById(Long id);

    ComentarioOutputDTO save(Long idUser,Long idPublicacao,Long idComentarioPai,ComentarioInputDTO comentarioInputDTO);

    ComentarioOutputDTO update(Long id, ComentarioInputDTO comentarioDeitails);

    Page<ComentarioOutputDTO> findComentarioByPublicacao(Long publicacaoId, int pagina, int tamanho);

    void delete(Long id);
}
