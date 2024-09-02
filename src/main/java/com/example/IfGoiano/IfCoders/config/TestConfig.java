package com.example.IfGoiano.IfCoders.config;

import com.example.IfGoiano.IfCoders.controller.DTO.input.*;
import com.example.IfGoiano.IfCoders.controller.DTO.output.*;
import com.example.IfGoiano.IfCoders.service.*;
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
    TutorService tutorService;
    @Autowired
    AlunoNapneService alunoNapneService;
    @Autowired
    InterpreteService interpreteService;
    @Autowired
    ProfessorService professorService;
    @Autowired
    PublicacaoService publicacaoService;

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

        ProfessorInputDTO professor1 = new ProfessorInputDTO();
        professor1.setNome("Cristiane Souza");
        professor1.setLogin("cristiane.souza");
        professor1.setSenha("cr88so88");
        professor1.setBiografia("uma biografia");
        professor1.setMatricula(884465L);

        InterpreteInputDTO interprete1 = new InterpreteInputDTO();
        interprete1.setSalary(2000.00);
        interprete1.setEspecialidade("libras");
        interprete1.setBiografia("uma biografia");
        interprete1.setNome("Tatiana Silva");
        interprete1.setLogin("tatiana.silva");
        interprete1.setMatricula(559966L);
        interprete1.setSenha("ta559si559");

        AlunoNapneOutputDTO aluno1Output= alunoNapneService.save(aluno1);
        TutorOutputDTO tutor1Output= tutorService.save(tutor1);
        ProfessorOutputDTO professorOutputDTO = professorService.save(professor1);
        InterpreteOutputDTO interpreteOutputDTO = interpreteService.save(interprete1);
    }
}
