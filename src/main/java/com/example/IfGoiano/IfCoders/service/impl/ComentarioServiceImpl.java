package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ComentarioInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.ComentarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.*;
import com.example.IfGoiano.IfCoders.exception.DataBaseException;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.repository.ComentarioRepository;
import com.example.IfGoiano.IfCoders.service.*;
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
    private PublicacaoService publicacaoService;
    @Autowired
    private PublicacaoMapper publicacaoMapper;

    @Autowired
    private AlunoService alunoService;
    @Autowired
    private AlunoMapper alunoMapper;

    @Autowired
    private TutorService tutorService;
    @Autowired
    private TutorMapper tutorMapper;

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired
    private InterpreteService interpreteService;
    @Autowired
    private InterpreteMapper interpreteMapper;

    @Autowired
    private AlunoNapneService alunoNapneService;
    @Autowired
    private AlunoNapneMapper alunoNapneMapper;



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

        var aluno = alunoService.findById(idUser);
        var tutor = tutorService.findById(idUser);
        var professor = professorService.findById(idUser);
        var interprete = interpreteService.findById(idUser);
        var alunoNapne = alunoNapneService.findById(idUser);

        ComentarioEntity comentarioEntity = mapper.toComentarioEntity(comentario);
        if (publicacao.getId() == null) throw new ResourceNotFoundException(idPublicacao);
        else if (publicacao.getId() != null) comentarioEntity.setPublicacaoEntity(publicacaoMapper.toPublicacaoEntity(publicacao));
        else if (aluno.getId()!= null) comentarioEntity.setUsuario(alunoMapper.toAlunoEntity(aluno));
        else if (tutor.getId()!= null) comentarioEntity.setUsuario(tutorMapper.toTutorEntity(tutor));
        else if (professor.getId()!= null) comentarioEntity.setUsuario(professorMapper.toProfessorEntity(professor));
        else if (interprete.getId() != null) comentarioEntity.setUsuario(interpreteMapper.toInterpreteEntity(interprete));
        else if (alunoNapne.getId()!= null) comentarioEntity.setUsuario(alunoNapneMapper.toAlunoNapneEntity(alunoNapne));
        else throw  new ResourceNotFoundException(idPublicacao);

        return mapper.toComentarioOutputDTO(repository.save(comentarioEntity));
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