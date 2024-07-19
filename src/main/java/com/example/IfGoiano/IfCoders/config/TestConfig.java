package com.example.IfGoiano.IfCoders.config;

import com.example.IfGoiano.IfCoders.model.Comentario;
import com.example.IfGoiano.IfCoders.model.Publicacao;
import com.example.IfGoiano.IfCoders.service.ComentarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    ComentarioService comentarioService;
    @Override
    public void run(String... args) throws Exception {
        Comentario comentario = new Comentario("oii",new Publicacao(),null,null,null);
        comentarioService.save(comentario);

    }
}
