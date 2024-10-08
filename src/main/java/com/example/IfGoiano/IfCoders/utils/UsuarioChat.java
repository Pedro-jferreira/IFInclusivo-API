package com.example.IfGoiano.IfCoders.utils;

import com.example.IfGoiano.IfCoders.controller.mapper.*;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioChat {
    private final TutorService tutorService;
    private final TutorMapper tutorMapper;
    private final ProfessorService professorService;
    private final ProfessorMapper professorMapper;
    private final InterpreteService interpreteService;
    private final InterpreteMapper interpreteMapper;
    private final AlunoNapneService alunoNapneService;
    private final AlunoNapneMapper alunoNapneMapper;

    @Autowired
    public UsuarioChat(TutorService tutorService, TutorMapper tutorMapper,
                         ProfessorService professorService, ProfessorMapper professorMapper,
                         InterpreteService interpreteService, InterpreteMapper interpreteMapper,
                         AlunoNapneService alunoNapneService, AlunoNapneMapper alunoNapneMapper) {
        this.tutorService = tutorService;
        this.tutorMapper = tutorMapper;
        this.professorService = professorService;
        this.professorMapper = professorMapper;
        this.interpreteService = interpreteService;
        this.interpreteMapper = interpreteMapper;
        this.alunoNapneService = alunoNapneService;
        this.alunoNapneMapper = alunoNapneMapper;
    }

    public UsuarioEntity findUsuarioById(Long idUser) {
        System.out.println(idUser);
        if (tutorService.existsById(idUser)) {
            return tutorMapper.toTutorEntity(tutorService.findById(idUser));
        } else if (professorService.existsById(idUser)) {
            return professorMapper.toProfessorEntity(professorService.findById(idUser));
        } else if (interpreteService.existsById(idUser)) {
            return interpreteMapper.toInterpreteEntity(interpreteService.findById(idUser));
        } else if (alunoNapneService.existsById(idUser)) {
            return alunoNapneMapper.toAlunoNapneEntity(alunoNapneService.findById(idUser));
        } else {
            throw new ResourceNotFoundException(idUser);
        }
    }
}
