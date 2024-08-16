package com.example.IfGoiano.IfCoders.config;

import com.example.IfGoiano.IfCoders.model.*;
import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
import com.example.IfGoiano.IfCoders.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    CursoRepository cursoRepository;
    @Override
    public void run(String... args) throws Exception {
        Curso curso = new Curso("Informática");
        cursoRepository.save(curso);
        Aluno aluno = new Aluno("Helo", "helo", "1234", 2022101202010074L, "oi eu sou helo", null, curso);
        alunoRepository.save(aluno);

    }
}
