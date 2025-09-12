package com.example.IfGoiano.IfCoders.controller.DTO.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {
    private String email;
    private String senhaAtual;
    private String novaSenha;
}
