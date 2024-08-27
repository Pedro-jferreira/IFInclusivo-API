package com.example.IfGoiano.IfCoders.config;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AlunoService alunoService;

    @Override
    public void run(String... args) throws Exception {
        AlunoInputDTO a = new AlunoInputDTO();
        a.setNome("Aluno");
        a.setMatricula(12345L);
        a.setLogin("pedrogay");
        a.setSenha("pedrogay123");
        a.setBiografia("uma linda biografia");

        alunoService.save(a);



    }
}
