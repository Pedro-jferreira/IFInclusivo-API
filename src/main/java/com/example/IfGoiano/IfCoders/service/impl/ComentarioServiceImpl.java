package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.*;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioMapper usuarioMapper;



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
        var usuario = usuarioService.findById(idUser);

        ComentarioEntity comentarioEntity = mapper.toComentarioEntity(comentario);

        comentarioEntity.setUsuario(usuarioMapper.toEntity(usuario));
        comentarioEntity.setPublicacao(publicacaoMapper.toPublicacaoEntity(publicacao));

        return findById(repository.save(comentarioEntity).getId());
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
    public Page<ComentarioOutputDTO> findComentarioByPublicacao(Long publicacaoId, int pagina, int tamanho) {
        Pageable pageable = PageRequest.of(pagina,tamanho);
        return repository.findByPublicacao_Id(publicacaoId,pageable).map(mapper::toComentarioOutputDTO);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(mapper.toComentarioEntity(findById(id)));
    }
}