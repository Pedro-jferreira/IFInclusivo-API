package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasOutputDTOV2;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.LibrasRepository;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import com.example.IfGoiano.IfCoders.service.LibrasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LibrasServiceImpl implements LibrasService {

    @Autowired
    LibrasRepository repository;
    @Autowired
    LibrasMapper mapper;

    @Autowired
    private CreateLibras createLibras;
    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepository userRepository;

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

    @Override
    public Page<LibrasOutputDTOV2> findByCategoria(Categorias categoria, Pageable pageable) {
        return repository.findByCategorias(categoria, pageable).map(mapper::toLibrasOutputDTOV2);
    }

    public Page<LibrasOutputDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toLibrasOutputDTO);
    }



    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Adicionar regra de negocios nesse metodo de criar libras
    public LibrasOutputDTO save(LibrasInputDTO libras, Long idInterprete) {
      return this.createLibras.createLibras(libras, idInterprete);
    }

    @Transactional
    public LibrasOutputDTO sugereLibras(LibrasInputDTO libras, Long idUser) {
        var usuario = this.userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));


        var libra = repository.findByPalavra(libras.getPalavra());
        LibrasEntity librasEntity = mapper.toLibrasEntity(libras);
        if (libra.isEmpty()) {
            librasEntity.getSugeriu().add(usuario);
            usuario.getLibrasEntities().add(librasEntity);
            librasEntity.setStatus(Status.EMANALISE);

            this.repository.save(librasEntity);
            this.userRepository.save(usuario);


            return findById(librasEntity.getId());
        }
        if (libra.get().getStatus() == Status.EMANALISE) {
            libra.get().getSugeriu().add(usuario);
            return findById(repository.save(libra.get()).getId());
        } else {
            throw new RuntimeException("Libras existed");
        }


    }

}