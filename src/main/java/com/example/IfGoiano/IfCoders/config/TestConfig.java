package com.example.IfGoiano.IfCoders.config;

import com.example.IfGoiano.IfCoders.controller.DTO.input.*;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.MessageOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.TutorOutputDTO;
import com.example.IfGoiano.IfCoders.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Timestamp;
import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    AlunoService alunoService;
    @Autowired
    TutorService tutorService;
    @Autowired
    AlunoNapneService alunoNapneService;
    @Autowired
    PublicacaoService publicacaoService;
    @Autowired
    MessageService messageService;

    @Override
    public void run(String... args) throws Exception {
        AlunoInputDTO alunoInputDTO = new AlunoInputDTO();
        alunoInputDTO.setNome("Jose da Silva");
        alunoInputDTO.setLogin("jose.silva");
        alunoInputDTO.setSenha("jo56jo56");
        alunoInputDTO.setBiografia("uma biografia");
        alunoInputDTO.setMatricula(564465L);

        AlunoOutputDTO alunoOutputDTO= alunoService.save(alunoInputDTO);

        PublicacaoInputDTO publicacaoInputDTO  = new PublicacaoInputDTO();
        publicacaoInputDTO.setText("fgsgsgsgs");

        publicacaoService.save(alunoOutputDTO.getId(),publicacaoInputDTO);

        AlunoNapneInputDTO aluno1 = new AlunoNapneInputDTO();
        aluno1.setNome("Jose Luiz");
        aluno1.setLogin("jose.luiz");
        aluno1.setSenha("jo96jo96");
        aluno1.setBiografia("uma biografia");
        aluno1.setMatricula(964465L);
        aluno1.setAcompanhamento("surdismo");
        aluno1.setCondicao("surdismo");
        aluno1.setLaudo("001");
        aluno1.setNecessidadeEscolar("acompanhamento");
        aluno1.setNecessidadeEspecial("surdismo");
        aluno1.setSituacao("avançado, precisa acompanhamento");

        TutorInputDTO tutor1 = new TutorInputDTO();
        tutor1.setNome("Francisca José");
        tutor1.setLogin("francisca.jose");
        tutor1.setSenha("fajo65fajo65");
        tutor1.setBiografia("uma biografia");
        tutor1.setMatricula(714465L);
        tutor1.setEspecialidade("libras");

        AlunoNapneOutputDTO aluno1Output= alunoNapneService.save(aluno1);
        TutorOutputDTO tutor1Output= tutorService.save(tutor1);

        MessageInputDTO messageInputDTO = new MessageInputDTO();
        messageInputDTO.setText("meeting today");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        messageInputDTO.setDateTime(timestamp);
        messageInputDTO.setUserEnvia(aluno1);
        messageInputDTO.setUserRecebe(tutor1);
        messageInputDTO.setVideo(false);

        messageService.save(aluno1Output.getId(), tutor1Output.getId(), messageInputDTO);
    }
}
