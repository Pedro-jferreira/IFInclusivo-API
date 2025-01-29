package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import lombok.Data;


@Data
public class RequestAnalisePalavra {

   private String palavra;
   private Categorias categorias;
   private Status status;
   private String Justificativa;
   private String url;
   private String video;
   private String foto;




}
