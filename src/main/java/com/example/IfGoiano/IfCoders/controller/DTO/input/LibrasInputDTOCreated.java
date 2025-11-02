package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import jakarta.mail.Multipart;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LibrasInputDTOCreated {
    private Long id;
    private String palavra;
    private String descricao;
    private String url;
    private String justificativa;
    private Status status;
    private Categorias categorias;
}
