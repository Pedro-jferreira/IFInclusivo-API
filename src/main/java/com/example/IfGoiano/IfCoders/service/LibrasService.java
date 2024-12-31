package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface LibrasService {

    List<LibrasOutputDTO> findAll(int pag,int itens);

    LibrasOutputDTO findById(Long id);

    LibrasOutputDTO save(LibrasInputDTO librasInputDTO );

    LibrasOutputDTO sugereLibras(LibrasInputDTO librasInputDTO,Long id );

    LibrasOutputDTO update(LibrasInputDTO librasInputDTO ,Long id);

    Page<LibrasOutputDTO> findByPalavra(String palavra, int pag, int itens);

    Page<LibrasOutputDTO> findByStatus(Status status, int pag, int itens);

    Page<LibrasOutputDTO> searchLibrasByDeeply(String search, int pag, int itens);

    LibrasOutputDTO findByPalavra(String palavra);

    void delete(Long id);


}