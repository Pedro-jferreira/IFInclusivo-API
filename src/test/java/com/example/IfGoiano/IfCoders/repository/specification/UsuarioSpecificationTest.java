package com.example.IfGoiano.IfCoders.repository.specification;

import com.example.IfGoiano.IfCoders.entity.Enums.Role;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioSpecificationTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        // Limpa o banco antes de cada teste para garantir isolamento
        usuarioRepository.deleteAll();

        // 1. Cria Usuário ALUNO
        UsuarioEntity aluno = new UsuarioEntity();
        aluno.setNome("João da Silva");
        aluno.setLogin("joao.aluno");
        aluno.setSenha("123");
        aluno.setMatricula(1001L);
        aluno.getRoles().add(Role.ROLE_ALUNO);

        // 2. Cria Usuário PROFESSOR
        UsuarioEntity professor = new UsuarioEntity();
        professor.setNome("Maria Pereira");
        professor.setLogin("maria.prof");
        professor.setSenha("123");
        professor.setMatricula(2001L);
        professor.getRoles().add(Role.ROLE_PROFESSOR);

        // 3. Cria Usuário com nome parecido
        UsuarioEntity aluno2 = new UsuarioEntity();
        aluno2.setNome("José Silva");
        aluno2.setLogin("jose.silva");
        aluno2.setSenha("123");
        aluno2.setMatricula(1002L);
        aluno2.getRoles().add(Role.ROLE_ALUNO);

        usuarioRepository.saveAll(List.of(aluno, professor, aluno2));
    }

    @Test
    @DisplayName("Deve filtrar usuários por parte do nome (case insensitive)")
    void nameContains_ShouldReturnMatchingUsers() {
        // Busca por "silva" (deve achar João da Silva e José Silva)
        Specification<UsuarioEntity> spec = UsuarioSpecification.nameContains("silva");
        List<UsuarioEntity> results = usuarioRepository.findAll(spec);

        assertThat(results).hasSize(2);
        assertThat(results).extracting(UsuarioEntity::getNome)
                .containsExactlyInAnyOrder("João da Silva", "José Silva");
    }

    @Test
    @DisplayName("Deve retornar vazio se o nome não existir")
    void nameContains_ShouldReturnEmpty_WhenNoMatch() {
        Specification<UsuarioEntity> spec = UsuarioSpecification.nameContains("XyzWk");
        List<UsuarioEntity> results = usuarioRepository.findAll(spec);

        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Deve filtrar usuários pela Role")
    void hasRole_ShouldReturnUsersWithSpecificRole() {
        // Busca apenas quem tem ROLE_PROFESSOR
        Specification<UsuarioEntity> spec = UsuarioSpecification.hasRole(Role.ROLE_PROFESSOR);
        List<UsuarioEntity> results = usuarioRepository.findAll(spec);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getNome()).isEqualTo("Maria Pereira");
    }

    @Test
    @DisplayName("Deve combinar filtros: Nome E Role")
    void shouldFilterByNameAndRole() {
        // Busca quem tem "Silva" no nome E é "ROLE_ALUNO"
        Specification<UsuarioEntity> spec = Specification
                .where(UsuarioSpecification.nameContains("Silva"))
                .and(UsuarioSpecification.hasRole(Role.ROLE_ALUNO));

        List<UsuarioEntity> results = usuarioRepository.findAll(spec);

        // Deve achar os dois Silvas (pois ambos são alunos)
        assertThat(results).hasSize(2);
    }
}