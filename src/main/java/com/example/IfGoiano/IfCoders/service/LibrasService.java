package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTOCreated;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasOutputDTOV2;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasRelacionadasDTO;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface LibrasService {

    Page<LibrasOutputDTO> findAll(Pageable pageable);

    LibrasOutputDTO findById(Long id);

    LibrasOutputDTO save(LibrasInputDTOCreated librasInputDTO, Long idInterprete );

    LibrasOutputDTO sugereLibras(LibrasInputDTO librasInputDTO,Long id );

    LibrasOutputDTO update(LibrasInputDTO librasInputDTO ,Long id);

    Page<LibrasOutputDTO> findByPalavra(String palavra, Pageable pageable);

    Page<LibrasOutputDTO> findByStatus(Status status,Pageable pageable);

    List<String> searchLibrasByDeeply(String search);

    Page<LibrasRelacionadasDTO> findRelatedByCategoria(Long librasId, Pageable pageable);

    LibrasOutputDTO findByPalavra(String palavra);

    void delete(Long id);

    Page<LibrasOutputDTOV2> findByCategoria(Categorias categoria, Pageable pageable);


}