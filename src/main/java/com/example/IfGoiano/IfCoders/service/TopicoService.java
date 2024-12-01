package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.TopicoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TopicoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.TopicoEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TopicoService {
    List<TopicoOutputDTO> findAll();

    TopicoOutputDTO findById(Long id);

    TopicoOutputDTO save(TopicoInputDTO topicoInputDTO);

    TopicoOutputDTO update(Long id, TopicoInputDTO topicoDetails);

    Page<TopicoOutputDTO> findByCategoria(Categorias categoria, int pagina, int tamanho);

    Page<TopicoOutputDTO> searchTopicByTermQuickly(String termo, int pagina, int tamanho);

    Page<TopicoOutputDTO> searchTopicByTermDeeply(String termo, int pagina, int tamanho);
    void delete(Long id);
}
