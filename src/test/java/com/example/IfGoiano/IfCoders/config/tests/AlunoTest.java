//package com.example.IfGoiano.IfCoders.config.tests;
//
//import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
//import com.example.IfGoiano.IfCoders.controller.DTO.input.ConfigAcblInputDTO;
//import com.example.IfGoiano.IfCoders.controller.DTO.input.CursoInputDTO;
//import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
//import com.example.IfGoiano.IfCoders.controller.mapper.AlunoMapper;
//import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
//import com.example.IfGoiano.IfCoders.repository.AlunoRepository;
//import com.example.IfGoiano.IfCoders.service.impl.AlunoServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class AlunoTest {
//    @Mock
//    private AlunoRepository repository;
//
//    @Mock
//    private AlunoMapper mapper;
//
//    @InjectMocks
//    private AlunoServiceImpl service;
//    private AlunoInputDTO alunoInputDTO;
//    private AlunoOutputDTO alunoOutputDTO;
//    private AlunoEntity alunoEntity;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        ConfigAcblInputDTO configAcblInputDTO = new ConfigAcblInputDTO();
//        CursoInputDTO cursoInputDTO = new CursoInputDTO(1L, "curso de inglês");
//        alunoInputDTO = new AlunoInputDTO();
//        alunoInputDTO.setMatricula(1L);
//        alunoInputDTO.setId(1L);
//        alunoInputDTO.setNome("José da Silva");
//        alunoInputDTO.setLogin("jose.silva");
//        alunoInputDTO.setBiografia("um cara legal");
//        alunoInputDTO.setSenha("js1js1js1");
//        alunoInputDTO.setConfigAcessibilidadeEntity(configAcblInputDTO);
//        alunoInputDTO.setCursoDTO(cursoInputDTO);
//    }
//
//    @Test
//    void create_Should_Return_AlunoInputDTO_WhenSucessful() {
//        System.out.println((alunoEntity));
//        when(mapper.toAlunoEntity(alunoInputDTO)).thenReturn(alunoEntity);
//        when(repository.save(alunoEntity)).thenReturn(alunoEntity);
//        when(mapper.toAlunoInputDTO(alunoEntity)).thenReturn(alunoInputDTO);
//
//        AlunoOutputDTO result = service.save(alunoInputDTO);
//
//        assertNotNull(result);
//        assertEquals(alunoOutputDTO, result);
//        verify(repository).save(alunoEntity);
//    }
//}
