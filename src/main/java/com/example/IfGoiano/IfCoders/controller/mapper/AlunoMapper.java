package com.example.IfGoiano.IfCoders.controller.mapper;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleAlunoDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.input.AlunoInputDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.output.AlunoOutputDTO;
import com.example.IfGoiano.IfCoders.entity.AlunoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {CursoMapper.class})
public interface AlunoMapper {

    // Mapeamento de AlunoEntity para DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "matricula", target = "matricula")
    @Mapping(source = "curso", target = "cursoDTO")
    SimpleAlunoDTO toSimpleAlunoDTO(AlunoEntity alunoEntity);

    // Mapeamento de DTO para AlunoEntity
    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "matricula", target = "matricula")
    @Mapping(source = "cursoDTO", target = "curso")
    AlunoEntity toAlunoEntity(SimpleAlunoDTO dto);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "matricula", target = "matricula")
    @Mapping(source = "curso", target = "cursoDTO")
    AlunoInputDTO toAlunoInputDTO(AlunoEntity entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "matricula", target = "matricula")
    @Mapping(source = "cursoDTO", target = "curso")
    AlunoEntity toAlunoEntity(AlunoInputDTO alunoInputDTO);
//
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "nome", target = "nome")
//    @Mapping(source = "matricula", target = "matricula")
//    @Mapping(source = "curso", target = "cursoDTO")
//    AlunoOutputDTO toAlunoOutputDTO(AlunoEntity entity);
//
//    // Mapeamento de AlunoOutputDTO para AlunoEntity
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "nome", target = "nome")
//    @Mapping(source = "matricula", target = "matricula")
//    @Mapping(source = "cursoDTO", target = "curso")
//    AlunoEntity toAlunoEntity(AlunoOutputDTO alunoOutputDTO);

}