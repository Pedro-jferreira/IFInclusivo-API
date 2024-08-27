package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.controller.mapper.ComentarioMapper;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.service.AlunoService;
import com.example.IfGoiano.IfCoders.service.ComentarioService;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    private AlunoService alunoService;
    @Autowired
    private AlunoMapper alunoMapper;

    @Autowired
    private PublicacaoService publicacaoService;
    @Autowired
    private PublicacaoMapper publicacaoMapper;

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
        var aluno = alunoService.findById(idUser);
        var publicacao = publicacaoService.findById(idPublicacao);

        if (aluno.getId()!= null && publicacao.getId()!= null){
            ComentarioEntity comentarioEntity = mapper.toComentarioEntity(comentario);
            comentarioEntity.setUsuario(alunoMapper.toAlunoEntity(aluno));
            comentarioEntity.setPublicacaoEntity(publicacaoMapper.toPublicacaoEntity(publicacao));
            return mapper.toComentarioOutputDTO(repository.save(comentarioEntity));
        }else throw  new ResourceNotFoundException(idUser);

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