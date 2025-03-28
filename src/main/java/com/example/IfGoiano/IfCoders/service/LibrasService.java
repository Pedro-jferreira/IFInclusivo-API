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

    LibrasOutputDTO save(LibrasInputDTO librasInputDTO, Long idInterprete );

    LibrasOutputDTO sugereLibras(LibrasInputDTO librasInputDTO,Long id );

    LibrasOutputDTO update(LibrasInputDTO librasInputDTO ,Long id);

    Page<LibrasOutputDTO> findByPalavra(String palavra, Pageable pageable);

    Page<LibrasOutputDTO> findByStatus(Status status,Pageable pageable);

    Page<LibrasOutputDTO> searchLibrasByDeeply(String search, Pageable pageable);

    LibrasOutputDTO findByPalavra(String palavra);

    void delete(Long id);


}