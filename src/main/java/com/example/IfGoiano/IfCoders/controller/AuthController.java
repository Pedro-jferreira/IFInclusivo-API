package com.example.IfGoiano.IfCoders.controller;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.*;
import com.example.IfGoiano.IfCoders.controller.DTO.output.UsuarioOutputDTO;
import com.example.IfGoiano.IfCoders.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/professor")
    public ResponseEntity<SimpleUsuarioDTO> register(@RequestBody ProfessorInputDTO request) {
        SimpleUsuarioDTO response = authService.register(request);
        return ResponseEntity.ok(response);

    }    @PostMapping("/register/tutor")
    public ResponseEntity<SimpleUsuarioDTO> register(@RequestBody TutorInputDTO request) {
        SimpleUsuarioDTO response = authService.register(request);
        return ResponseEntity.ok(response);

    }    @PostMapping("/register/aluno")
    public ResponseEntity<SimpleUsuarioDTO> register(@RequestBody AlunoInputDTO request) {
        SimpleUsuarioDTO response = authService.register(request);
        return ResponseEntity.ok(response);

    }    @PostMapping("/register/interprete")
    public ResponseEntity<SimpleUsuarioDTO> register(@RequestBody InterpreteInputDTO request) {
        SimpleUsuarioDTO response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        authService.verificationToken(token);
        return ResponseEntity.ok("E-mail confirmado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioOutputDTO> login(@RequestBody LoginRequest loginRequest) {
        UsuarioOutputDTO response = authService.authenticate(loginRequest.getLogin(), loginRequest.getSenha());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        authService.logout();
        return ResponseEntity.ok("Logout efetuado com sucesso.");
    }
}

