package com.example.IfGoiano.IfCoders.mapper.mocks;

import com.example.IfGoiano.IfCoders.controller.DTO.input.InterpreteInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.InterpreteOutputDTO;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.InterpreteEntity;

public class MockInterprete {

    MockConfigAcess inputAcess;

    public InterpreteEntity mockInterpreteEntity(Long number) {
        InterpreteEntity interpreteEntity = new InterpreteEntity();
        interpreteEntity.setId(number);
        interpreteEntity.setNome("First Name " + number);
        interpreteEntity.setSenha("First Senha" + number);
        interpreteEntity.setSalary(number.doubleValue());
        interpreteEntity.setBiografia("My biografia " + number);
        interpreteEntity.setLogin("First Login " + number);
        interpreteEntity.setEspecialidade("My especialidade " + number);
        ConfigAcessibilidadeEntity config = inputAcess.mockConfigAces(number);
        interpreteEntity.setConfigAcessibilidadeEntity(config);
        return interpreteEntity;
    }

    public InterpreteOutputDTO interpreteOutputDTO(Long number) {
        InterpreteOutputDTO interpreteOutputDTO = new InterpreteOutputDTO();
        interpreteOutputDTO.setId(number);
        interpreteOutputDTO.setNome("First Name " + number);
        interpreteOutputDTO.setSenha("First Senha" + number);
        interpreteOutputDTO.setSalary(number.doubleValue());
        interpreteOutputDTO.setBiografia("My biografia " + number);
        interpreteOutputDTO.setLogin("First Login " + number);
        interpreteOutputDTO.setEspecialidade("My especialidade " + number);
        return interpreteOutputDTO;

    }

    public InterpreteInputDTO interpreteInputDTO(Long number) {
        InterpreteInputDTO interpreteInputDTO = new InterpreteInputDTO();
        interpreteInputDTO.setNome("First Name " + number);
        interpreteInputDTO.setSenha("First Senha" + number);
        interpreteInputDTO.setSalary(number.doubleValue());
        interpreteInputDTO.setBiografia("My biografia " + number);
        interpreteInputDTO.setLogin("First Login " + number);
        interpreteInputDTO.setEspecialidade("My especialidade " + number);
        interpreteInputDTO.setMatricula(number);

        return interpreteInputDTO;

    }

}
