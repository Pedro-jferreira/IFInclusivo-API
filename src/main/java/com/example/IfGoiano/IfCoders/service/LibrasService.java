package com.example.IfGoiano.IfCoders.service;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface LibrasService {

    List<LibrasOutputDTO> findAll();

    LibrasOutputDTO findById(Long id);

    LibrasOutputDTO save(LibrasInputDTO librasInputDTO);

    LibrasOutputDTO update(LibrasInputDTO librasInputDTO ,Long id);

    void delete(Long id);


}