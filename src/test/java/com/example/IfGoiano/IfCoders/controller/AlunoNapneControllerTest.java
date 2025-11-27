package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoNapneInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.update.AlunoNapneUpdateDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoNapneOutputDTO;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.security.AuthenticationEntryPointImpl;
import com.example.IfGoiano.IfCoders.security.CustomUserDetailsService;
import com.example.IfGoiano.IfCoders.security.TokenService;
import com.example.IfGoiano.IfCoders.service.impl.AlunoNapneServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoNapneController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class AlunoNapneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AlunoNapneServiceImpl alunoNapneService;

    @MockBean private TokenService tokenService;
    @MockBean private CustomUserDetailsService customUserDetailsService;
    @MockBean private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Test
    @DisplayName("GET /alunosNapne - Deve retornar lista de alunos NAPNE com status 200")
    void findAll_ShouldReturnList() throws Exception {
        AlunoNapneOutputDTO dto = new AlunoNapneOutputDTO();
        dto.setId(1L);
        dto.setNome("Aluno Napne 1");
        dto.setCondicao("TDAH");

        when(alunoNapneService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/alunosNapne")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Aluno Napne 1"))
                .andExpect(jsonPath("$[0].condicao").value("TDAH"));
    }

    @Test
    @DisplayName("GET /alunosNapne/{id} - Deve retornar aluno NAPNE por ID com status 200")
    void findById_ShouldReturnAluno() throws Exception {
        Long id = 1L;
        AlunoNapneOutputDTO dto = new AlunoNapneOutputDTO();
        dto.setId(id);
        dto.setNome("Aluno Napne Teste");

        when(alunoNapneService.findById(id)).thenReturn(dto);

        mockMvc.perform(get("/alunosNapne/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("Aluno Napne Teste"));
    }

    @Test
    @DisplayName("GET /alunosNapne/{id} - Deve retornar 404 se não encontrado")
    void findById_WhenNotFound_ShouldReturn404() throws Exception {
        Long id = 99L;
        when(alunoNapneService.findById(id))
                .thenThrow(new ResourceNotFoundException(id));

        mockMvc.perform(get("/alunosNapne/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.details").value("Resource not found"));
    }

    @Test
    @DisplayName("POST /alunosNapne - Deve criar aluno NAPNE e retornar 201 Created")
    void save_ShouldReturnCreated() throws Exception {
        Long idConfigAc = 1L;
        AlunoNapneInputDTO inputDTO = new AlunoNapneInputDTO();
        inputDTO.setNome("Novo Napne");
        inputDTO.setCondicao("Baixa Visão");
        inputDTO.setMatricula(2024001L);
        inputDTO.setSenha("123456");
        inputDTO.setLogin("novo.napne");

        AlunoNapneOutputDTO outputDTO = new AlunoNapneOutputDTO();
        outputDTO.setId(10L);
        outputDTO.setNome("Novo Napne");

        when(alunoNapneService.save(any(AlunoNapneInputDTO.class), eq(idConfigAc))).thenReturn(outputDTO);

        mockMvc.perform(post("/alunosNapne")
                        .param("idConfigAc", String.valueOf(idConfigAc))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.nome").value("Novo Napne"));
    }

    @Test
    @DisplayName("PUT /alunosNapne - Deve retornar 401 se usuário não estiver autenticado")
    void update_WithoutAuth_ShouldReturnUnauthorized() throws Exception {
        AlunoNapneUpdateDTO updateDTO = new AlunoNapneUpdateDTO();
        updateDTO.setNome("Teste Sem Auth");

        mockMvc.perform(put("/alunosNapne")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("PUT /alunosNapne - Deve atualizar com sucesso quando autenticado")
    @WithMockUser(username = "napne.user", roles = {"ALUNO_NAPNE"})
    void update_ShouldReturnOk() throws Exception {
        String username = "napne.user";
        AlunoNapneUpdateDTO updateDTO = new AlunoNapneUpdateDTO();
        updateDTO.setNome("Napne Atualizado");

        AlunoNapneOutputDTO outputDTO = new AlunoNapneOutputDTO();
        outputDTO.setId(1L);
        outputDTO.setNome("Napne Atualizado");

        when(alunoNapneService.update(any(AlunoNapneUpdateDTO.class), eq(username))).thenReturn(outputDTO);

        mockMvc.perform(put("/alunosNapne")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Napne Atualizado"));
    }

    @Test
    @DisplayName("DELETE /alunosNapne/{id} - Deve deletar e retornar 204 No Content")
    void delete_ShouldReturnNoContent() throws Exception {
        Long id = 1L;
        doNothing().when(alunoNapneService).delete(id);

        mockMvc.perform(delete("/alunosNapne/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /alunosNapne/{id} - Deve retornar 404 se aluno não existir")
    void delete_WhenNotFound_ShouldReturn404() throws Exception {
        Long id = 99L;
        doThrow(new ResourceNotFoundException(id)).when(alunoNapneService).delete(id);

        mockMvc.perform(delete("/alunosNapne/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.details").value("Resource not found"));
    }

    @Test
    @DisplayName("POST /alunosNapne - Deve retornar 400 Bad Request se o parâmetro 'idConfigAc' estiver ausente")
    void save_WhenMissingParam_ShouldReturnBadRequest() throws Exception {
        AlunoNapneInputDTO inputDTO = new AlunoNapneInputDTO();
        inputDTO.setNome("Napne Sem Config");

        mockMvc.perform(post("/alunosNapne")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /alunosNapne - Deve retornar 500 Internal Server Error em caso de falha inesperada")
    void findAll_WhenInternalError_ShouldReturn500() throws Exception {
        when(alunoNapneService.findAll()).thenThrow(new RuntimeException("Erro inesperado no banco de dados"));

        mockMvc.perform(get("/alunosNapne")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Something went wrong. Please try again later."))
                .andExpect(jsonPath("$.details").value("Erro inesperado no banco de dados"));
    }
}