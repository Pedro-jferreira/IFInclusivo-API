package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.*;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.exception.BadRequestException;
import com.example.IfGoiano.IfCoders.security.AuthenticationEntryPointImpl;
import com.example.IfGoiano.IfCoders.security.CustomUserDetailsService;
import com.example.IfGoiano.IfCoders.security.TokenService;
import com.example.IfGoiano.IfCoders.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean private TokenService tokenService;
    @MockBean private CustomUserDetailsService customUserDetailsService;
    @MockBean private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Test
    @DisplayName("POST /auth/register/aluno - Deve registrar aluno e retornar 200 OK")
    void registerAluno_ShouldReturnOk() throws Exception {

        AlunoInputDTO input = new AlunoInputDTO();
        input.setNome("Novo Aluno");
        input.setLogin("aluno.teste");
        input.setSenha("123456");

        SimpleUsuarioDTO output = new SimpleUsuarioDTO();
        output.setNome("Novo Aluno");

        when(authService.register(any(AlunoInputDTO.class))).thenReturn(output);

        mockMvc.perform(post("/auth/register/aluno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Novo Aluno"));
    }

    @Test
    @DisplayName("POST /auth/register/professor - Deve registrar professor e retornar 200 OK")
    void registerProfessor_ShouldReturnOk() throws Exception {
        ProfessorInputDTO input = new ProfessorInputDTO();
        input.setNome("Novo Prof");

        SimpleUsuarioDTO output = new SimpleUsuarioDTO();
        output.setNome("Novo Prof");

        when(authService.register(any(ProfessorInputDTO.class))).thenReturn(output);

        mockMvc.perform(post("/auth/register/professor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Novo Prof"));
    }

    @Test
    @DisplayName("POST /auth/register/tutor - Deve registrar tutor e retornar 200 OK")
    void registerTutor_ShouldReturnOk() throws Exception {
        TutorInputDTO input = new TutorInputDTO();
        input.setNome("Novo Tutor");

        SimpleUsuarioDTO output = new SimpleUsuarioDTO();
        output.setNome("Novo Tutor");

        when(authService.register(any(TutorInputDTO.class))).thenReturn(output);

        mockMvc.perform(post("/auth/register/tutor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /auth/register/interprete - Deve registrar intérprete e retornar 200 OK")
    void registerInterprete_ShouldReturnOk() throws Exception {
        InterpreteInputDTO input = new InterpreteInputDTO();
        input.setNome("Novo Interprete");

        SimpleUsuarioDTO output = new SimpleUsuarioDTO();
        output.setNome("Novo Interprete");

        when(authService.register(any(InterpreteInputDTO.class))).thenReturn(output);

        mockMvc.perform(post("/auth/register/interprete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /auth/login - Deve autenticar e retornar token")
    void login_ShouldReturnToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin("user");
        loginRequest.setSenha("123");

        UsuarioOutputDTO output = new UsuarioOutputDTO();
        output.setToken("jwt-token-exemplo");

        when(authService.authenticate("user", "123")).thenReturn(output);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token-exemplo"));
    }

    @Test
    @DisplayName("GET /auth/verify-email - Deve verificar email com sucesso")
    void verifyEmail_ShouldReturnOk() throws Exception {
        String token = "valid-token";
        doNothing().when(authService).verificationToken(token);

        mockMvc.perform(get("/auth/verify-email")
                        .param("token", token))
                .andExpect(status().isOk())
                .andExpect(content().string("E-mail confirmado com sucesso!"));
    }

    @Test
    @DisplayName("POST /auth/resend - Deve reenviar email de confirmação")
    void resendToken_ShouldReturnOk() throws Exception {
        String token = "expired-token";
        doNothing().when(authService).resendConfirmationEmailFromExpiredToken(token);

        mockMvc.perform(post("/auth/resend")
                        .param("token", token))
                .andExpect(status().isOk())
                .andExpect(content().string("E-mail Reenviado com sucesso."));
    }

    @Test
    @DisplayName("POST /auth/forgot-password - Deve solicitar reset de senha")
    void forgotPassword_ShouldReturnOk() throws Exception {
        ForgotPasswordRequest request = new ForgotPasswordRequest("email@teste.com");
        doNothing().when(authService).forgotPassword("email@teste.com");

        mockMvc.perform(post("/auth/forgot-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("E-mail de redefinição de senha enviado, verifique sua caixa de entrada."));
    }

    @Test
    @DisplayName("POST /auth/reset-password - Deve redefinir a senha")
    void resetPassword_ShouldReturnOk() throws Exception {
        ResetPasswordRequest request = new ResetPasswordRequest("token", "novaSenha123");
        doNothing().when(authService).resetPassword("token", "novaSenha123");

        mockMvc.perform(post("/auth/reset-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Senha redefinida com sucesso."));
    }

    @Test
    @DisplayName("POST /auth/update-password - Deve retornar 401 se usuário não autenticado")
    void updatePassword_WithoutAuth_ShouldReturnUnauthorized() throws Exception {
        UpdatePasswordRequest request = new UpdatePasswordRequest("velha", "nova");

        mockMvc.perform(post("/auth/update-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /auth/update-password - Deve atualizar senha se autenticado")
    @WithMockUser(username = "usuario.logado")
    void updatePassword_WithAuth_ShouldReturnOk() throws Exception {
        String username = "usuario.logado";
        UpdatePasswordRequest request = new UpdatePasswordRequest("senhaAtual", "novaSenha");

        doNothing().when(authService).updatePassword(username, "senhaAtual", "novaSenha");

        mockMvc.perform(post("/auth/update-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Senha atualizada com sucesso."));
    }

    @Test
    @DisplayName("DELETE /auth - Deve deletar conta se autenticado e senha correta")
    @WithMockUser(username = "usuario.logado")
    void deleteAccount_WithAuth_ShouldReturnOk() throws Exception {
        String username = "usuario.logado";
        String password = "senhaCorreta";

        doNothing().when(authService).deleteUser(username, password);

        mockMvc.perform(delete("/auth")
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuário deletado com sucesso"));
    }

    @Test
    @DisplayName("DELETE /auth - Deve retornar erro se senha incorreta (Service lança Exception)")
    @WithMockUser(username = "usuario.logado")
    void deleteAccount_WrongPassword_ShouldReturnError() throws Exception {
        String username = "usuario.logado";
        String password = "senhaErrada";

        doThrow(new ResponseStatusException(HttpStatus.CONFLICT, "Senha incorreta"))
                .when(authService).deleteUser(username, password);

        mockMvc.perform(delete("/auth")
                        .param("password", password))
                .andExpect(status().isConflict())
                .andExpect(content().string("Senha incorreta"));
    }

    @Test
    @DisplayName("POST /auth/logout - Deve fazer logout")
    void logout_ShouldReturnOk() throws Exception {
        doNothing().when(authService).logout();

        mockMvc.perform(post("/auth/logout"))
                .andExpect(status().isOk())
                .andExpect(content().string("Logout efetuado com sucesso."));
    }

    @Test
    @DisplayName("POST /auth/register/aluno - Deve retornar 400 Bad Request se usuário já existir")
    void register_WhenUserExists_ShouldReturnBadRequest() throws Exception {
        AlunoInputDTO input = new AlunoInputDTO();
        input.setNome("Duplicado");

        when(authService.register(any(AlunoInputDTO.class)))
                .thenThrow(new BadRequestException("Usuário já existe."));

        mockMvc.perform(post("/auth/register/aluno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Usuário já existe."));
    }
    @Test
    @DisplayName("POST /auth/update-password - Deve retornar 400 se a senha atual estiver incorreta")
    @WithMockUser(username = "user")
    void updatePassword_WrongCurrentPassword_ShouldReturnBadRequest() throws Exception {
        UpdatePasswordRequest request = new UpdatePasswordRequest("senhaErrada", "novaSenha");

        // Simula erro de Bad Request (400) vindo do serviço
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha atual incorreta"))
                .when(authService).updatePassword(anyString(), eq("senhaErrada"), anyString());

        mockMvc.perform(post("/auth/update-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest()); // Espera 400
    }
    @Test
    @DisplayName("POST /auth/update-password - Deve retornar 409 se a nova senha for igual à atual")
    @WithMockUser(username = "user")
    void updatePassword_SamePassword_ShouldReturnConflict() throws Exception {
        UpdatePasswordRequest request = new UpdatePasswordRequest("senha123", "senha123");

        doThrow(new ResponseStatusException(HttpStatus.CONFLICT, "A nova senha não pode ser igual à senha atual"))
                .when(authService).updatePassword(anyString(), anyString(), anyString());

        mockMvc.perform(post("/auth/update-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("POST /auth/login - Deve falhar se credenciais forem inválidas")
    void login_InvalidCredentials_ShouldFail() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin("user");
        loginRequest.setSenha("senhaErrada");

        when(authService.authenticate("user", "senhaErrada"))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }
}