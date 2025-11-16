package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.ProfessorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.TutorInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.*;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import com.example.IfGoiano.IfCoders.entity.ProfessorEntity;
import com.example.IfGoiano.IfCoders.entity.TutorEntity;
import com.example.IfGoiano.IfCoders.entity.UsuarioEntity;
import com.example.IfGoiano.IfCoders.exception.BadRequestException;
import com.example.IfGoiano.IfCoders.exception.ResourceNotFoundException;
import com.example.IfGoiano.IfCoders.exception.TokenExpiredException;
import com.example.IfGoiano.IfCoders.exception.TokenInvalidException;
import com.example.IfGoiano.IfCoders.repository.*;
import com.example.IfGoiano.IfCoders.security.CustomUserDetails;
import com.example.IfGoiano.IfCoders.security.TokenService;
import com.example.IfGoiano.IfCoders.service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ProfessorRepository professorRepository;
    @Mock
    private AlunoRepository alunoRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private InterpreteRepository interpreteRepository;

    @Mock
    private UsuarioMapper usuarioMapper;
    @Mock
    private ProfessorMapper professorMapper;
    @Mock
    private AlunoMapper alunoMapper;
    @Mock
    private TutorMapper tutorMapper;
    @Mock
    private InterpreteMapper interpreteMapper;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TokenService tokenService;
    @Mock
    private EmailService emailService;
    @Mock
    private VerifyLoginAndRegistration verifyLogin;
    @Mock
    private AuthenticationManager authenticationManager;

    private ProfessorInputDTO mockProfessorInput;
    private UsuarioEntity mockUsuario;
    private ProfessorEntity mockProfessorEntity;

    @BeforeEach
    void setUp() {

        mockProfessorInput = new ProfessorInputDTO();
        mockProfessorInput.setNome("Prof. Teste");
        mockProfessorInput.setLogin("prof.teste@email.com");
        mockProfessorInput.setSenha("senha123");
        mockProfessorInput.setMatricula(12345L);

        mockUsuario = new UsuarioEntity();
        mockUsuario.setId(1L);
        mockUsuario.setNome("Prof. Teste");
        mockUsuario.setLogin("prof.teste@email.com");
        mockUsuario.setSenha("senhaHasheada123");
        mockUsuario.setActive(false);

        mockProfessorEntity = new ProfessorEntity();
        mockProfessorEntity.setId(1L);
        mockProfessorEntity.setNome("Prof. Teste");
        mockProfessorEntity.setLogin("prof.teste@email.com");
        mockProfessorEntity.setActive(false);
    }

    @Test
    @DisplayName("Deve autenticar com sucesso e retornar DTO com token")
    void authenticate() {

        String email = "prof.teste@email.com";
        String password = "senha123";
        String jwtToken = "mock.jwt.token.123";

        Authentication mockAuthentication = mock(Authentication.class);
        CustomUserDetails mockUserDetails = mock(CustomUserDetails.class);
        UsuarioOutputDTO mockUsuarioOutputDTO = new UsuarioOutputDTO();
        mockUsuarioOutputDTO.setId(1L);
        mockUsuarioOutputDTO.setLogin(email);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuthentication);
        when(mockAuthentication.getPrincipal()).thenReturn(mockUserDetails);
        when(mockUserDetails.getUsuario()).thenReturn(mockUsuario);
        when(tokenService.generateAuthToken(mockUsuario)).thenReturn(jwtToken);
        when(usuarioMapper.toOutputDTO(mockUsuario)).thenReturn(mockUsuarioOutputDTO);

        UsuarioOutputDTO result = authService.authenticate(email, password);

        assertNotNull(result);
        assertEquals(email, result.getLogin());
        assertEquals(jwtToken, result.getToken());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenService).generateAuthToken(mockUsuario);
    }

    @Test
    @DisplayName("Deve lançar AuthenticationException ao autenticar com credenciais inválidas")
    void authenticate_ShouldThrowException_WhenCredentialsAreInvalid() {

        String email = "prof.teste@email.com";
        String password = "senhaErrada";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("Credenciais ruins") {});

        assertThrows(AuthenticationException.class, () -> {
            authService.authenticate(email, password);
        });

        verify(tokenService, never()).generateAuthToken(any());
        verify(usuarioMapper, never()).toOutputDTO(any());
    }


    @Test
    @DisplayName("Deve registrar um novo Professor com sucesso")
    void register_ShouldRegisterProfessorSuccessfully() {

        when(verifyLogin.existsByLogin(anyString(), anyLong())).thenReturn(false);
        when(professorMapper.toProfessorEntity(any(ProfessorInputDTO.class))).thenReturn(mockProfessorEntity);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaHasheada");
        when(tokenService.generateEmailVerificationToken(any(UsuarioEntity.class))).thenReturn("mock.token.123");
        when(professorRepository.save(any(ProfessorEntity.class))).thenReturn(mockProfessorEntity);
        when(usuarioMapper.toSimpleDTO(any(UsuarioEntity.class))).thenReturn(new SimpleUsuarioDTO());

        authService.register(mockProfessorInput);

        verify(verifyLogin).existsByLogin("prof.teste@email.com", 12345L);
        verify(passwordEncoder).encode("senha123");
        assertEquals("senhaHasheada", mockProfessorEntity.getSenha());
        assertFalse(mockProfessorEntity.isActive());
        verify(professorRepository).save(mockProfessorEntity);
        verify(tokenService).generateEmailVerificationToken(mockProfessorEntity);
        verify(emailService).send(eq("prof.teste@email.com"), eq("Confirmação de E-mail"), anyString());
    }
    @Test
    @DisplayName("Deve lançar BadRequestException ao tentar registrar usuário duplicado")
    void register_ShouldThrowBadRequest_WhenUserAlreadyExists() {
        when(verifyLogin.existsByLogin("prof.teste@email.com", 12345L)).thenReturn(true);

        assertThrows(BadRequestException.class, () -> {
            authService.register(mockProfessorInput);
        });

        verify(professorRepository, never()).save(any());
        verify(emailService, never()).send(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Deve verificar token e ativar usuário com sucesso")
    void verificationToken() {
        String validToken = "token.valido.123";
        String email = "prof.teste@email.com";
        assertFalse(mockUsuario.isActive());

        when(tokenService.isTokenValid(validToken)).thenReturn(true);
        when(tokenService.extractTokenType(validToken)).thenReturn("EMAIL_VERIFICATION");
        when(tokenService.extractUsername(validToken)).thenReturn(email);
        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.of(mockUsuario));

        authService.verificationToken(validToken);

        assertTrue(mockUsuario.isActive());
        verify(usuarioRepository).save(mockUsuario);
    }

    @Test
    @DisplayName("Deve lançar TokenExpiredException se o token expirou")
    void verificationToken_ShouldThrowTokenExpiredException_WhenTokenIsExpired() {
        String expiredToken = "token.expirado.123";

        when(tokenService.isTokenValid(expiredToken)).thenThrow(new TokenExpiredException("Token expired"));

        assertThrows(TokenExpiredException.class, () -> {
            authService.verificationToken(expiredToken);
        });

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar TokenInvalidException se o tipo do token for errado")
    void verificationToken_ShouldThrowTokenInvalidException_WhenTokenTypeIsWrong() {
        String wrongTypeToken = "token.tipo.errado.123";

        when(tokenService.isTokenValid(wrongTypeToken)).thenReturn(true);
        when(tokenService.extractTokenType(wrongTypeToken)).thenReturn("RESET_PASSWORD");

        TokenInvalidException exception = assertThrows(TokenInvalidException.class, () -> {
            authService.verificationToken(wrongTypeToken);
        });

        assertEquals("Token não é do tipo de confirmação de email", exception.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve enviar email de 'forgotPassword' se o usuário existir")
    void forgotPassword() {
        String email = "prof.teste@email.com";
        String resetToken = "reset.token.456";

        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.of(mockUsuario));
        when(tokenService.generatePasswordResetToken(mockUsuario)).thenReturn(resetToken);

        authService.forgotPassword(email);

        verify(usuarioRepository).findByLogin(email);
        verify(tokenService).generatePasswordResetToken(mockUsuario);
        verify(emailService).send(
                eq(email),
                eq("Redefinição de Senha"),
                anyString()
        );
    }
    @Test
    @DisplayName("Deve lançar ResourceNotFoundException no 'forgotPassword' se usuário não existir")
    void forgotPassword_ShouldThrowNotFound_WhenUserDoesNotExist() {
        String email = "nao.existe@email.com";
        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            authService.forgotPassword(email);
        });

        verify(tokenService, never()).generatePasswordResetToken(any());
        verify(emailService, never()).send(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Deve redefinir a senha com sucesso se o token for válido")
    void resetPassword() {
        String resetToken = "reset.token.456";
        String novaSenha = "novaSenha123";
        String email = "prof.teste@email.com";

        when(tokenService.isTokenValid(resetToken)).thenReturn(true);
        when(tokenService.extractTokenType(resetToken)).thenReturn("RESET_PASSWORD");
        when(tokenService.extractUsername(resetToken)).thenReturn(email);
        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.encode(novaSenha)).thenReturn("novaSenhaHasheada");

        authService.resetPassword(resetToken, novaSenha);

        verify(passwordEncoder).encode(novaSenha);
        verify(usuarioRepository).save(mockUsuario);
        assertEquals("novaSenhaHasheada", mockUsuario.getSenha());
    }
    @Test
    @DisplayName("Deve lançar TokenInvalidException no 'resetPassword' se token for inválido")
    void resetPassword_ShouldThrowException_WhenTokenIsInvalid() {
        String invalidToken = "token.invalido";
        when(tokenService.isTokenValid(invalidToken)).thenThrow(new TokenInvalidException("Token Invalido"));

        assertThrows(TokenInvalidException.class, () -> {
            authService.resetPassword(invalidToken, "novaSenha123");
        });

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve atualizar a senha com sucesso se a senha atual estiver correta")
    void updatePassword() {
        String email = "prof.teste@email.com";
        String senhaAtual = "senha123";
        String novaSenha = "novaSenha123";

        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.matches(senhaAtual, "senhaHasheada123")).thenReturn(true);
        when(passwordEncoder.matches(novaSenha, "senhaHasheada123")).thenReturn(false);
        when(passwordEncoder.encode(novaSenha)).thenReturn("novaSenhaHasheada456");

        authService.updatePassword(email, senhaAtual, novaSenha);

        verify(passwordEncoder).encode(novaSenha);
        verify(usuarioRepository).save(mockUsuario);
        assertEquals("novaSenhaHasheada456", mockUsuario.getSenha());
        verify(emailService).send(eq(email), eq("Senha atualizada com sucesso"), anyString());
    }

    @Test
    @DisplayName("Deve lançar ResponseStatusException (Bad Request) se 'updatePassword' tiver senha atual incorreta")
    void updatePassword_ShouldThrowBadRequest_WhenCurrentPasswordIsWrong() {
        String email = "prof.teste@email.com";
        String senhaAtualErrada = "senhaErrada";
        String novaSenha = "novaSenha123";

        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.matches(senhaAtualErrada, "senhaHasheada123")).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authService.updatePassword(email, senhaAtualErrada, novaSenha);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar ResponseStatusException (Conflict) se 'updatePassword' tiver nova senha igual à antiga")
    void updatePassword_ShouldThrowConflict_WhenNewPasswordIsSameAsOld() {
        String email = "prof.teste@email.com";
        String senhaAtual = "senha123";
        String novaSenhaIgual = "senha123";

        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.matches(senhaAtual, "senhaHasheada123")).thenReturn(true);
        when(passwordEncoder.matches(novaSenhaIgual, "senhaHasheada123")).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authService.updatePassword(email, senhaAtual, novaSenhaIgual);
        });

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve reenviar email de confirmação se usuário ainda não estiver ativo")
    void resendConfirmationEmailFromExpiredToken() {
        String expiredToken = "token.expirado.123";
        String email = "prof.teste@email.com";
        String novoToken = "novo.token.789";

        mockUsuario.setActive(false);

        when(tokenService.extractUsernameFromExpiredToken(expiredToken)).thenReturn(email);
        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.of(mockUsuario));
        when(tokenService.generateEmailVerificationToken(mockUsuario)).thenReturn(novoToken);

        authService.resendConfirmationEmailFromExpiredToken(expiredToken);

        verify(tokenService).generateEmailVerificationToken(mockUsuario);
        verify(emailService).send(
                eq(email),
                eq("Confirmação de E-mail"),
                anyString() // Verifica se enviou email
        );
    }

    @Test
    @DisplayName("Deve lançar RuntimeException no 'resend' se usuário já estiver ativo")
    void resendConfirmationEmail_ShouldThrowException_WhenUserIsAlreadyActive() {
        String expiredToken = "token.expirado.123";
        String email = "prof.teste@email.com";

        mockUsuario.setActive(true);

        when(tokenService.extractUsernameFromExpiredToken(expiredToken)).thenReturn(email);
        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.of(mockUsuario));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.resendConfirmationEmailFromExpiredToken(expiredToken);
        });

        assertEquals("Usuário já confirmado.", exception.getMessage());
        verify(tokenService, never()).generateEmailVerificationToken(any());
        verify(emailService, never()).send(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Deve deletar usuário se a senha estiver correta")
    void deleteUser() {
        String email = "prof.teste@email.com";
        String senhaCorreta = "senha123";

        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.matches(senhaCorreta, "senhaHasheada123")).thenReturn(true);

        authService.deleteUser(email, senhaCorreta);

        verify(usuarioRepository).delete(mockUsuario);
        verify(emailService).send(eq(email), eq("Conta Apagada com sucesso"), anyString());
    }

    @Test
    @DisplayName("Deve lançar ResponseStatusException (Conflict) no 'deleteUser' se senha estiver incorreta")
    void deleteUser_ShouldThrowConflict_WhenPasswordIsIncorrect() {
        String email = "prof.teste@email.com";
        String senhaErrada = "senhaErrada";

        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.of(mockUsuario));
        when(passwordEncoder.matches(senhaErrada, "senhaHasheada123")).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authService.deleteUser(email, senhaErrada);
        });

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        verify(usuarioRepository, never()).delete(any(UsuarioEntity.class));
    }


    @Test
    @DisplayName("Teste de logout (apenas para cobertura)")
    void logout() {

        assertDoesNotThrow(() -> {
            authService.logout();
        });
    }

    @Test
    @DisplayName("Deve registrar um novo Aluno com sucesso")
    void register_ShouldRegisterAlunoSuccessfully() {

        AlunoInputDTO mockAlunoInput = new AlunoInputDTO();
        mockAlunoInput.setLogin("aluno.teste@email.com");
        mockAlunoInput.setSenha("senha123");
        mockAlunoInput.setMatricula(54321L);

        AlunoEntity mockAlunoEntity = new AlunoEntity();
        mockAlunoEntity.setLogin("aluno.teste@email.com");

        when(verifyLogin.existsByLogin(anyString(), anyLong())).thenReturn(false);
        when(alunoMapper.toAlunoEntity(any(AlunoInputDTO.class))).thenReturn(mockAlunoEntity);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaHasheada");
        when(tokenService.generateEmailVerificationToken(any(UsuarioEntity.class))).thenReturn("mock.token.123");
        when(alunoRepository.save(any(AlunoEntity.class))).thenReturn(mockAlunoEntity);
        when(usuarioMapper.toSimpleDTO(any(UsuarioEntity.class))).thenReturn(new SimpleUsuarioDTO());

        authService.register(mockAlunoInput);

        verify(verifyLogin).existsByLogin("aluno.teste@email.com", 54321L);
        verify(passwordEncoder).encode("senha123");
        verify(alunoRepository).save(mockAlunoEntity);
        assertTrue(mockAlunoEntity.getRoles().contains(com.example.IfGoiano.IfCoders.entity.Enums.Role.ROLE_ALUNO));
        assertEquals("aluno", mockAlunoEntity.getUserType());
        verify(emailService).send(eq("aluno.teste@email.com"), eq("Confirmação de E-mail"), anyString());
    }

    @Test
    @DisplayName("Deve registrar um novo Tutor com sucesso")
    void register_ShouldRegisterTutorSuccessfully() {

        TutorInputDTO mockTutorInput = new TutorInputDTO();
        mockTutorInput.setLogin("tutor.teste@email.com");
        mockTutorInput.setSenha("senha123");
        mockTutorInput.setMatricula(67890L);

        TutorEntity mockTutorEntity = new TutorEntity();
        mockTutorEntity.setLogin("tutor.teste@email.com");

        when(verifyLogin.existsByLogin(anyString(), anyLong())).thenReturn(false);
        when(tutorMapper.toTutorEntity(any(TutorInputDTO.class))).thenReturn(mockTutorEntity);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaHasheada");
        when(tokenService.generateEmailVerificationToken(any(UsuarioEntity.class))).thenReturn("mock.token.123");
        when(tutorRepository.save(any(TutorEntity.class))).thenReturn(mockTutorEntity);
        when(usuarioMapper.toSimpleDTO(any(UsuarioEntity.class))).thenReturn(new SimpleUsuarioDTO());

        authService.register(mockTutorInput);

        verify(tutorRepository).save(mockTutorEntity);
        assertTrue(mockTutorEntity.getRoles().contains(com.example.IfGoiano.IfCoders.entity.Enums.Role.ROLE_TUTOR));
        assertEquals("tutor", mockTutorEntity.getUserType());
        verify(emailService).send(eq("tutor.teste@email.com"), eq("Confirmação de E-mail"), anyString());
    }

    @Test
    @DisplayName("Deve registrar um novo Interprete com sucesso")
    void register_ShouldRegisterInterpreteSuccessfully() {

        InterpreteInputDTO mockInterpreteInput = new InterpreteInputDTO();
        mockInterpreteInput.setLogin("interprete.teste@email.com");
        mockInterpreteInput.setSenha("senha123");
        mockInterpreteInput.setMatricula(11111L);

        InterpreteEntity mockInterpreteEntity = new InterpreteEntity();
        mockInterpreteEntity.setLogin("interprete.teste@email.com");

        when(verifyLogin.existsByLogin(anyString(), anyLong())).thenReturn(false);
        when(interpreteMapper.toInterpreteEntity(any(InterpreteInputDTO.class))).thenReturn(mockInterpreteEntity);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaHasheada");
        when(tokenService.generateEmailVerificationToken(any(UsuarioEntity.class))).thenReturn("mock.token.123");
        when(interpreteRepository.save(any(InterpreteEntity.class))).thenReturn(mockInterpreteEntity);
        when(usuarioMapper.toSimpleDTO(any(UsuarioEntity.class))).thenReturn(new SimpleUsuarioDTO());

        authService.register(mockInterpreteInput);

        verify(interpreteRepository).save(mockInterpreteEntity);
        assertTrue(mockInterpreteEntity.getRoles().contains(com.example.IfGoiano.IfCoders.entity.Enums.Role.ROLE_INTERPRETE));
        assertEquals("interprete", mockInterpreteEntity.getUserType());
        verify(emailService).send(eq("interprete.teste@email.com"), eq("Confirmação de E-mail"), anyString());
    }

    @Test
    @DisplayName("Deve lançar ResponseStatusException (Not Found) no 'updatePassword' se usuário não existir")
    void updatePassword_ShouldThrowNotFound_WhenUserDoesNotExist() {

        String email = "fantasma@email.com";

        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authService.updatePassword(email, "senhaAtual", "novaSenha");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getReason().contains("Usuário não encontrado"));
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar ResponseStatusException (Not Found) no 'deleteUser' se usuário não existir")
    void deleteUser_ShouldThrowNotFound_WhenUserDoesNotExist() {

        String email = "fantasma@email.com";

        when(usuarioRepository.findByLogin(email)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authService.deleteUser(email, "senha");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertTrue(exception.getReason().contains("Usuário não encontrado"));
        verify(passwordEncoder, never()).matches(anyString(), anyString());
        verify(usuarioRepository, never()).delete(any(UsuarioEntity.class));
    }




}