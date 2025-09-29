package com.example.IfGoiano.IfCoders.controller.DTO.input;


import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PublicacaoRequestDTO {


    @Size(max = 255, message = "O título não pode exceder 255 caracteres.")
    private String titulo;

    @NotBlank(message = "O texto da publicação não pode estar vazio.")
    private String texto;

    @NotEmpty(message = "A publicação deve ter pelo menos uma categoria.")
    private Set<Categorias> categorias;

    private Long parentId;

}