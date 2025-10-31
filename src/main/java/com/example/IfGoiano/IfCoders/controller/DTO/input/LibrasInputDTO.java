package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LibrasInputDTO {
    private String palavra;
    private String descricao;
    private String url;
    private MultipartFile file;
}
