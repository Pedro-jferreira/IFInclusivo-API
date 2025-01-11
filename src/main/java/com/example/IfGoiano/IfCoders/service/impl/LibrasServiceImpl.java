package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import com.example.IfGoiano.IfCoders.service.LibrasService;
import com.example.IfGoiano.IfCoders.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LibrasServiceImpl implements LibrasService {

    @Autowired
    LibrasRepository repository;
    @Autowired
    LibrasMapper mapper;

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private UsuarioMapper usuarioMapper;

    public LibrasOutputDTO findById(Long id) {
        var libras = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Libras not found"));
        return mapper.toLibrasOutputDTO(libras);
    }


    public LibrasOutputDTO update(LibrasInputDTO libras, Long id) {
        var libraAux = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Libras not found"));
        mapper.updateLibrasEntityFromDTO(libras, libraAux);
        return mapper.toLibrasOutputDTO(repository.save(libraAux));
    }

    @Override
    public Page<LibrasOutputDTO> findByPalavra(String palavra, Pageable pageable) {
        return  repository.findByPalavra(palavra,pageable).map(mapper::toLibrasOutputDTO);
    }

    @Override
    public Page<LibrasOutputDTO> findByStatus(Status status, Pageable pageable) {
        return repository.findByStatus(status,pageable).map(mapper::toLibrasOutputDTO);
    }

    @Override
    public Page<LibrasOutputDTO> searchLibrasByDeeply(String search, Pageable pageable) {
       return repository.searchLibrasByDeeply(search,pageable).map(mapper::toLibrasOutputDTO);
    }

    @Override
    public LibrasOutputDTO findByPalavra(String palavra) {
      var libras = repository.findByPalavra(palavra).orElseThrow(() -> new ResourceNotFoundException("Libras not found"));
      return mapper.toLibrasOutputDTO(libras);
    }

    public List<LibrasOutputDTO> findAll(int pag, int itens) {

        return repository.findAll(PageRequest.of(pag, itens)).stream().map(mapper::toLibrasOutputDTO).collect(Collectors.toList());
    }



    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Adicionar regra de negocios nesse metodo de criar libras
    public LibrasOutputDTO save(LibrasInputDTO libras) {

        LibrasEntity librasEntity = mapper.toLibrasEntity(libras);
        this.repository.save(librasEntity);
        return mapper.toLibrasOutputDTO(librasEntity);
    }

    public LibrasOutputDTO sugereLibras(LibrasInputDTO libras, Long idUser) {
        var usuario = usuarioService.findById(idUser);
        if (usuario == null) {
            throw new ResourceNotFoundException("User not found");
        }

        var libra = repository.findByPalavra(libras.getPalavra()).get();
        LibrasEntity librasEntity = mapper.toLibrasEntity(libras);
        if (libra == null) {
            librasEntity.getSugeriu().add(usuarioMapper.toEntity(usuario));
            librasEntity.setStatus(Status.EMANALISE);
            return findById(repository.save(librasEntity).getId());
        }
        if (libra.getStatus() == Status.EMANALISE) {
            libra.getSugeriu().add(usuarioMapper.toEntity(usuario));
            return findById(repository.save(libra).getId());
        } else {
            throw new RuntimeException("Libras existed");
        }


    }

}