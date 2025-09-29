package com.example.IfGoiano.IfCoders.controller.DTO.output;

import lombok.Data;

import java.util.List;

@Data
public class PublicacaoCompletaDTO {
    private PublicacaoDetalhadaDTO atual;
    private List<PublicacaoDetalhadaDTO> pais;
}
