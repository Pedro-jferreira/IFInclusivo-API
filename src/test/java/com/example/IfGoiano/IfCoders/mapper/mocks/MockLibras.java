package com.example.IfGoiano.IfCoders.mapper.mocks;

import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Categorias;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;
import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;
import com.example.IfGoiano.IfCoders.entity.LibrasEntity;

public class MockLibras {

    MockInterprete input;

    public LibrasEntity mockLibrasEntity(Long number) {
        LibrasEntity librasEntity = new LibrasEntity();
        librasEntity.setId(number);
        librasEntity.setStatus(Status.APROVADO);
        librasEntity.setFoto("First foto" + number);
        librasEntity.setDescricao("First descricao" + number);
        librasEntity.setPalavra("First palavra" + number);
        librasEntity.setUrl("First url" + number);
        librasEntity.setJustificativa("First justificativa" + number);
        librasEntity.setVideo("First video" + number);
        librasEntity.setCategorias(Categorias.REDES);
        InterpreteEntity interprete = input.mockInterpreteEntity(number);
        librasEntity.getInterprete().add(interprete);
        return librasEntity;
    }
}
