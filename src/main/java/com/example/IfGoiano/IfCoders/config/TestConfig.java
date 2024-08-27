package com.example.IfGoiano.IfCoders.config;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.PublicacaoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.service.AlunoService;
import com.example.IfGoiano.IfCoders.service.PublicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    AlunoService alunoService;
    @Autowired
    PublicacaoService publicacaoService;

    @Override
    public void run(String... args) throws Exception {
        AlunoInputDTO alunoInputDTO = new AlunoInputDTO();
        alunoInputDTO.setNome("Aluno");
        alunoInputDTO.setLogin("kjfjfhbvgjfn");
        alunoInputDTO.setSenha("jjdhdhdhd");
        alunoInputDTO.setBiografia("uma biografia");
        alunoInputDTO.setMatricula(564465L);

        AlunoOutputDTO alunoOutputDTO= alunoService.save(alunoInputDTO);

        PublicacaoInputDTO publicacaoInputDTO  = new PublicacaoInputDTO();
        publicacaoInputDTO.setText("fgsgsgsgs");

        publicacaoService.save(alunoOutputDTO.getId(),publicacaoInputDTO);



    }
}
