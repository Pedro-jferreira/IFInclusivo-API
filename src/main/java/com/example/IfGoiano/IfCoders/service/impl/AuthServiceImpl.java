package com.example.IfGoiano.IfCoders.service.impl;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.*;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.controller.mapper.*;
import com.example.IfGoiano.IfCoders.entity.*;
import com.example.IfGoiano.IfCoders.entity.Enums.Role;
import com.example.IfGoiano.IfCoders.repository.*;
import com.example.IfGoiano.IfCoders.security.CustomUserDetails;
import com.example.IfGoiano.IfCoders.security.TokenService;
import com.example.IfGoiano.IfCoders.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Value("${link.confirme-token}")
    private String linkToken;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    private final TutorRepository tutorRepository;
    private final TutorMapper tutorMapper;

    private final InterpreteRepository interpreteRepository;
    private final InterpreteMapper interpreteMapper;

    private final TokenService tokenService;
    private final EmailService emailService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository, AlunoRepository alunoRepository, AlunoMapper alunoMapper, ProfessorRepository professorRepository, ProfessorMapper professorMapper, TutorRepository tutorRepository, AuthenticationManager authenticationManager, TutorMapper tutorMapper, InterpreteRepository interpreteRepository, PasswordEncoder passwordEncoder, InterpreteMapper interpreteMapper, TokenService tokenService, EmailService emailService) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
        this.tutorRepository = tutorRepository;
        this.authenticationManager = authenticationManager;
        this.tutorMapper = tutorMapper;
        this.interpreteRepository = interpreteRepository;
        this.passwordEncoder = passwordEncoder;
        this.interpreteMapper = interpreteMapper;
        this.tokenService = tokenService;
        this.emailService = emailService;
    }

    @Override
    public UsuarioOutputDTO authenticate(String email, String password) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authToken);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UsuarioEntity usuario = userDetails.getUsuario();
        String token = tokenService.generateAuthToken(usuario);
        UsuarioOutputDTO dto = usuarioMapper.toOutputDTO(usuario);
        dto.setToken(token);
        return dto;
    }

    @Override
    public SimpleUsuarioDTO register(UsuarioInputDTO user) {
        UsuarioEntity usuario = null;

        if (user instanceof ProfessorInputDTO){
            ProfessorEntity professor = professorMapper.toProfessorEntity((ProfessorInputDTO) user);
            professor.getRoles().add(Role.ROLE_PROFESSOR);
            professor.setActive(false);
            professor.setSenha(passwordEncoder.encode(user.getSenha()));
            usuario = professorRepository.save(professor);

        } else if (user instanceof AlunoInputDTO){
            AlunoEntity aluno = alunoMapper.toAlunoEntity((AlunoInputDTO) user);
            aluno.getRoles().add(Role.ROLE_ALUNO);
            aluno.setActive(false);
            aluno.setSenha(passwordEncoder.encode(user.getSenha()));
            usuario = alunoRepository.save(aluno);

        } else if (user instanceof InterpreteInputDTO){
            InterpreteEntity interprete = interpreteMapper.toInterpreteEntity((InterpreteInputDTO) user);
            interprete.getRoles().add(Role.ROLE_INTERPRETE);
            interprete.setActive(false);
            interprete.setSenha(passwordEncoder.encode(user.getSenha()));
            usuario = interpreteRepository.save(interprete);

        } else if (user instanceof TutorInputDTO){
            TutorEntity tutor = tutorMapper.toTutorEntity((TutorInputDTO) user);
            tutor.getRoles().add(Role.ROLE_TUTOR);
            tutor.setActive(false);
            tutor.setSenha(passwordEncoder.encode(user.getSenha()));
            usuario = tutorRepository.save(tutor);
        }

        if (usuario == null) {
            throw new IllegalArgumentException("Tipo de usuário não suportado.");
        }


        String token = tokenService.generateEmailVerificationToken(usuario);
        String link = linkToken + token;

        emailService.send(
                usuario.getLogin(),
                "Confirmação de E-mail",
                "Clique no link para confirmar seu cadastro: " + link
        );

        return usuarioMapper.toSimpleDTO(usuario);
    }

    @Override
    @Transactional
    public void verificationToken(String token) {
        if (!tokenService.isTokenValid(token)) {
            throw new RuntimeException("Token inválido ou expirado");
        }

        String type = tokenService.extractTokenType(token);
        if (!"EMAIL_VERIFICATION".equals(type)) {
            throw new RuntimeException("Token não é do tipo de confirmação de email");
        }


        String username = tokenService.extractUsername(token);


        UsuarioEntity usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        usuario.setActive(true);
        usuarioRepository.save(usuario);
    }


    @Override
    public void logout() {

    }
}
