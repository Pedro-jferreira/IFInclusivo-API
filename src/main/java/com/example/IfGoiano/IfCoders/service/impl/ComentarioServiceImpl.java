package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.*;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.service.*;
import com.example.IfGoiano.IfCoders.utils.UsuarioFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository repository;
    @Autowired
    private ComentarioMapper mapper;


    @Autowired
    private PublicacaoService publicacaoService;
    @Autowired
    private PublicacaoMapper publicacaoMapper;
    @Autowired
    private UsuarioFinder usuarioFinder;



    @Override
    @Transactional
    public List<ComentarioOutputDTO> findAll() {
        return repository.findAll().stream().map(mapper::toComentarioOutputDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ComentarioOutputDTO findById(Long id) {
        Optional<ComentarioEntity> comentario = repository.findById(id);
        if (comentario.isPresent()) return mapper.toComentarioOutputDTO(comentario.get());
        else throw  new ResourceNotFoundException(id);
    }

    @Override
    @Transactional
    public ComentarioOutputDTO save(Long idUser,Long idPublicacao, ComentarioInputDTO comentario) {
        var publicacao = publicacaoService.findById(idPublicacao);
        var usuario = usuarioFinder.findUsuarioById(idUser);

        ComentarioEntity comentarioEntity = mapper.toComentarioEntity(comentario);
        comentarioEntity.setUsuario(usuario);
        comentarioEntity.setPublicacao(publicacaoMapper.toPublicacaoEntity(publicacao));
        comentarioEntity=repository.save(comentarioEntity);

        return findById(comentarioEntity.getId());
    }

    @Override
    @Transactional
    public ComentarioOutputDTO update(Long id, ComentarioInputDTO comentarioDetails) {
        Optional<ComentarioEntity> comentario = repository.findById(id);
        if (comentario.isPresent()) {
            ComentarioEntity comentario1 = comentario.get();
            mapper.updateComentarioEntityFromDTO(comentarioDetails,comentario1);
            return mapper.toComentarioOutputDTO(repository.save(comentario1));
        }else throw  new ResourceNotFoundException(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(mapper.toComentarioEntity(findById(id)));
    }
}