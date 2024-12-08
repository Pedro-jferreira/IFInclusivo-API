package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
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

    void delete(Long id);


}