package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.ComentarioMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;

import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoMapper mapper;
    @Autowired
    PublicacaoService publicacaoService;
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    PublicacaoMapper publicacaomapper;


    public List<AlunoOutputDTO> findAll() {
        try {
            return alunoRepository.findAll().stream().map(mapper::toAlunoOutputDTO).collect(Collectors.toList());
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching all alunos: " + e);
        }
    }

    public AlunoOutputDTO findById(Long id) {
        try {
            var aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            return mapper.toAlunoOutputDTO(aluno);
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while fetching user: " + e);
        }
    }

    @Transactional
    public AlunoOutputDTO save(AlunoInputDTO aluno) {
        try {

             return mapper.toAlunoOutputDTO(alunoRepository.save(mapper.toAlunoEntity(aluno)));
        } catch (DataBaseException e) {
            throw new DataBaseException("Database error occurred while saving new aluno: " + e);
        }
    }

    @Transactional
    public AlunoOutputDTO update(Long id, AlunoInputDTO alunoDetails) {
        try {
            var aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            mapper.updateAlunoEntityFromDTO( alunoDetails,aluno);
            return mapper.toAlunoOutputDTO(alunoRepository.save(aluno));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while updating the aluno: " + e);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            alunoRepository.delete(mapper.toAlunoEntity(findById(id)));
        } catch (DataAccessException e) {
            throw new DataBaseException("Database error occurred while deleting the aluno: " + e);
        }
    }
    public ComentarioOutputDTO createComentario(Long idUsuario , Long idPublicacao, ComentarioInputDTO comentarioInputDTO) {
        var usuario = mapper.toAlunoEntity(findById(idUsuario));
        var publicacao = publicacaomapper.toPublicacaoEntity(publicacaoService.findById(idPublicacao));
        ComentarioEntity comentarioEntity = toComentarioEntity(comentarioInputDTO);
        comentarioEntity.setUsuario(usuario);
        comentarioEntity.setPublicacaoEntity(publicacao);
        ComentarioEntity savedComentario = comentarioRepository.save(comentarioEntity);
        return toComentarioOutputDTO(savedComentario);
    }

    private ComentarioEntity toComentarioEntity(ComentarioInputDTO comentarioInputDTO) {
        ComentarioEntity comentarioEntity = new ComentarioEntity();
        comentarioEntity.setId(comentarioInputDTO.getId());
        comentarioEntity.setContent(comentarioInputDTO.getContent());
        if (comentarioInputDTO.getComentarioPai() != null && comentarioInputDTO.getComentarioPai().getId() != null){
            ComentarioEntity simpleComentarioDTO = new ComentarioEntity();
            simpleComentarioDTO.setId(comentarioInputDTO.getComentarioPai().getId());
            simpleComentarioDTO.setContent(comentarioInputDTO.getComentarioPai().getContent());
            comentarioEntity.setComentarioPai(simpleComentarioDTO);
        }
        return comentarioEntity;
    }

    private ComentarioOutputDTO toComentarioOutputDTO(ComentarioEntity comentarioEntity) {
        ComentarioOutputDTO comentarioOutputDTO = new ComentarioOutputDTO();
        comentarioOutputDTO.setId(comentarioEntity.getId());
        comentarioOutputDTO.setContent(comentarioEntity.getContent());
        comentarioOutputDTO.setDataCriacao(comentarioEntity.getDataCriacao());
        SimpleAlunoDTO simpleAlunoDTO = new SimpleAlunoDTO();
        simpleAlunoDTO.setId(comentarioEntity.getUsuario().getId());
        simpleAlunoDTO.setNome(comentarioEntity.getUsuario().getNome());
        simpleAlunoDTO.setMatricula(comentarioEntity.getUsuario().getMatricula());
        comentarioOutputDTO.setUsuario(simpleAlunoDTO);
        return comentarioOutputDTO;
    }

}
