package com.example.IfGoiano.IfCoders.repository;

import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.LibrasInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.LibrasOutputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.LibrasMapper;
import com.example.IfGoiano.IfCoders.controller.mapper.UsuarioMapper;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;

import static org.junit.jupiter.api.Assertions.*;

import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;
import com.example.IfGoiano.IfCoders.mapper.mocks.MockConfigAcess;
import com.example.IfGoiano.IfCoders.mapper.mocks.MockInterprete;
import com.example.IfGoiano.IfCoders.service.impl.LibrasServiceImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest // indica que e uma classe de teste que vai testa o repositoryJPA
@ActiveProfiles("test") //indica qual arquivo de properties vai ser usado para os teste
class LibrasRepositoryTest {

    MockConfigAcess input = new MockConfigAcess();

    MockInterprete inputInterprete = new MockInterprete();

    @InjectMocks
    private LibrasServiceImpl librasService;

    @Autowired
    LibrasRepository librasRepository;

    @Autowired
    EntityManager entityManager;

    private InterpreteEntity createInterprete(InterpreteInputDTO user) {
        InterpreteEntity interpreteEntity = new InterpreteEntity();
        interpreteEntity.setNome(user.getNome());
        interpreteEntity.setSalary(user.getSalary());
        interpreteEntity.setLogin(user.getLogin());
        interpreteEntity.setMatricula(user.getMatricula());
        interpreteEntity.setBiografia(user.getBiografia());
        interpreteEntity.setEspecialidade(user.getEspecialidade());
        interpreteEntity.setSenha(user.getSenha());
        ConfigAcessibilidadeEntity config = entityManager.merge(input.mockConfigAces(1L));
        this.entityManager.persist(interpreteEntity);// Persistir primeiro
        interpreteEntity.setConfigAcessibilidadeEntity(config); // Associar após persistência
        this.entityManager.persist(interpreteEntity); // Persistir InterpreteEntity

        return interpreteEntity;
    }


    @Test
    @DisplayName("should create Libras successfully from DB")
    void searchLibrasByDeeplySuccess() {
        LibrasEntity librasEntity = new LibrasEntity();
        librasEntity.setId(1L);
        librasEntity.setStatus(Status.APROVADO);
        librasEntity.setFoto("First foto" + 1L);
        librasEntity.setDescricao("First descricao" + 1L);
        librasEntity.setPalavra("First palavra" + 1L);
        librasEntity.setUrl("First url" + 1L);
        librasEntity.setJustificativa("First justificativa" + 1L);
        librasEntity.setVideo("First video" + 1L);
        librasEntity.setCategorias(Categorias.REDES);
        InterpreteInputDTO interpreteInputDTO = this.inputInterprete.interpreteInputDTO(1L);
        librasEntity.getSugeriu().add(this.createInterprete(interpreteInputDTO));

        this.entityManager.merge(librasEntity);

        Page<LibrasEntity> result = this.librasRepository.searchLibrasByDeeply("First", PageRequest.of(0, 1));
        assertThat(result.getTotalElements()).isEqualTo(1);
        assertNotNull(result.getContent());
        assertNotNull(result.getContent().get(0).getInterprete());
        assertEquals("First foto1", result.getContent().get(0).getFoto());

    }

    @Test
    @DisplayName("should create Libras successfully from DB")
    void searchLibrasByDeeplyFailure() {
        LibrasEntity librasEntity = new LibrasEntity();
        librasEntity.setId(1L);
        librasEntity.setStatus(Status.APROVADO);
        librasEntity.setFoto("First foto" + 1L);
        librasEntity.setDescricao("First descricao" + 1L);
        librasEntity.setPalavra("First palavra" + 1L);
        librasEntity.setUrl("First url" + 1L);
        librasEntity.setJustificativa("First justificativa" + 1L);
        librasEntity.setVideo("First video" + 1L);
        librasEntity.setCategorias(Categorias.REDES);
        ;
        librasEntity.getSugeriu().add(null);

        Assertions.assertThrows(NullPointerException.class, () -> librasService.searchLibrasByDeeply
                ("First", PageRequest.of(0, 1)));


    }


}