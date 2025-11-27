package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.AlunoUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.security.AuthenticationEntryPointImpl;
import com.example.IfGoiano.IfCoders.security.AuthenticationFilter;
import com.example.IfGoiano.IfCoders.security.CustomUserDetailsService;
import com.example.IfGoiano.IfCoders.security.TokenService;
import com.example.IfGoiano.IfCoders.service.impl.AlunoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AlunoServiceImpl alunoService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Test
    @DisplayName("GET /alunos - Deve retornar lista de alunos com status 200")
    void findAll_ShouldReturnList() throws Exception {
        AlunoOutputDTO alunoDTO = new AlunoOutputDTO();
        alunoDTO.setId(1L);
        alunoDTO.setNome("João Silva");

        when(alunoService.findAll()).thenReturn(List.of(alunoDTO));

        mockMvc.perform(get("/alunos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("João Silva"));
    }

    @Test
    @DisplayName("PUT /alunos - Deve atualizar aluno com sucesso quando autenticado")
    @WithMockUser(username = "usuario.teste", roles = {"ALUNO"})
    void update_ShouldReturnOk() throws Exception {

        String username = "usuario.teste";
        AlunoUpdateDTO updateDTO = new AlunoUpdateDTO();
        updateDTO.setNome("Aluno Atualizado");

        AlunoOutputDTO outputDTO = new AlunoOutputDTO();
        outputDTO.setId(1L);
        outputDTO.setNome("Aluno Atualizado");

        when(alunoService.update(eq(username), any(AlunoUpdateDTO.class))).thenReturn(outputDTO);

        mockMvc.perform(put("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Aluno Atualizado"));
    }


    @Test
    @DisplayName("GET /alunos/{id} - Deve retornar um aluno por ID com status 200")
    void findById_ShouldReturnAluno() throws Exception {
        Long id = 1L;
        AlunoOutputDTO alunoDTO = new AlunoOutputDTO();
        alunoDTO.setId(id);
        alunoDTO.setNome("Maria Souza");

        when(alunoService.findById(id)).thenReturn(alunoDTO);

        mockMvc.perform(get("/alunos/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Maria Souza"));
    }

    @Test
    @DisplayName("POST /alunos - Deve criar aluno e retornar 201 Created com Location")
    void save_ShouldReturnCreated() throws Exception {
        Long idConfigAc = 5L;
        AlunoInputDTO inputDTO = new AlunoInputDTO();
        inputDTO.setNome("Novo Aluno");
        inputDTO.setMatricula(2023100L);
        inputDTO.setSenha("123456");
        inputDTO.setLogin("novo.aluno");

        AlunoOutputDTO outputDTO = new AlunoOutputDTO();
        outputDTO.setId(10L);
        outputDTO.setNome("Novo Aluno");

        when(alunoService.save(any(AlunoInputDTO.class), eq(idConfigAc))).thenReturn(outputDTO);

        mockMvc.perform(post("/alunos")
                        .param("idConfigAc", String.valueOf(idConfigAc))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.nome").value("Novo Aluno"));
    }

    @Test
    @DisplayName("PUT /alunos - Deve retornar 401 Unauthorized se usuário não autenticado (filtro desativado, UserDetails null)")
    void update_WithoutAuth_ShouldReturnUnauthorized() throws Exception {

        AlunoUpdateDTO updateDTO = new AlunoUpdateDTO();
        updateDTO.setNome("Teste");

        mockMvc.perform(put("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("DELETE /alunos/{id} - Deve deletar e retornar 204 No Content")
    void delete_ShouldReturnNoContent() throws Exception {
        Long id = 1L;
        doNothing().when(alunoService).delete(id);

        mockMvc.perform(delete("/alunos/{id}", id)
                        .header("Authorization", "Bearer token-falso"))
                .andExpect(status().isNoContent());
    }
    @Test
    @DisplayName("GET /alunos/{id} - Deve retornar 404 Not Found quando id não existir")
    void findById_WhenNotFound_ShouldReturn404() throws Exception {

        Long idInexistente = 99L;

        when(alunoService.findById(idInexistente)).thenThrow(new ResourceNotFoundException(idInexistente));

        mockMvc.perform(get("/alunos/{id}", idInexistente)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()) // Espera erro 404
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("DELETE /alunos/{id} - Deve retornar 404 Not Found se o aluno não existir")
    void delete_WhenNotFound_ShouldReturn404() throws Exception {
        Long idInexistente = 99L;

        doThrow(new ResourceNotFoundException(idInexistente))
                .when(alunoService).delete(idInexistente);

        mockMvc.perform(delete("/alunos/{id}", idInexistente)
                        .header("Authorization", "Bearer token-falso"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.details").value("Resource not found"));
    }
    @Test
    @DisplayName("PUT /alunos - Deve retornar 404 se o usuário logado não for encontrado no banco")
    @WithMockUser(username = "usuario.fantasma", roles = {"ALUNO"})
    void update_WhenUserNotFound_ShouldReturn404() throws Exception {

        String username = "usuario.fantasma";
        AlunoUpdateDTO updateDTO = new AlunoUpdateDTO();
        updateDTO.setNome("Tentativa de Update");

        when(alunoService.update(eq(username), any(AlunoUpdateDTO.class)))
                .thenThrow(new ResourceNotFoundException("User not found"));

        mockMvc.perform(put("/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

}