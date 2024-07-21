package com.example.IfGoiano.IfCoders.config;

import com.example.IfGoiano.IfCoders.model.*;
import com.example.IfGoiano.IfCoders.repository.ProfessorRepository;
import com.example.IfGoiano.IfCoders.repository.PublicacaoRepositoy;
import com.example.IfGoiano.IfCoders.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    PublicacaoRepositoy publicacaoRepositoy;
    @Autowired
    ProfessorRepository professorRepository;
    @Override
    public void run(String... args) throws Exception {
        ProfessorEntity professor = new ProfessorEntity("professor","login0","senha", 11111L, " eee ","ldldldl");
        professorRepository.save(professor);
        Publicacao publicacao = new Publicacao("uma publicaçao","kjhfjkdshfksh","ksksks",professor);
        publicacaoRepositoy.save(publicacao);
        Comentario comentario = new Comentario("sssss",publicacao,professor);
        comentarioService.save(comentario);

    }
}
