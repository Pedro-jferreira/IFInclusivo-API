package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.AlunoMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.PublicacaoMapper;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;

import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import com.example.IfGoiano.IfCoders.service.AlunoService;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoServiceImpl implements AlunoService {

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


    @Override
    public List<AlunoOutputDTO> findAll() {
        return alunoRepository.findAll().stream().map(mapper::toAlunoOutputDTO).collect(Collectors.toList());
    }

    @Override
    public AlunoOutputDTO findById(Long id) {
        var aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return mapper.toAlunoOutputDTO(aluno);
    }

    @Override
    @Transactional
    public AlunoOutputDTO save(AlunoInputDTO aluno) {
        return findById( alunoRepository.save(mapper.toAlunoEntity(aluno)).getId());
    }

    @Override
    @Transactional
    public AlunoOutputDTO update(Long id, AlunoInputDTO alunoDetails) {
        var aluno = alunoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        mapper.updateAlunoEntityFromDTO( alunoDetails,aluno);
        return mapper.toAlunoOutputDTO(alunoRepository.save(aluno));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        alunoRepository.delete(mapper.toAlunoEntity(findById(id)));
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
