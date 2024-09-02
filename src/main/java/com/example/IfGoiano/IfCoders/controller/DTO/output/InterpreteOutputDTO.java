package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleLibrasDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterpreteOutputDTO extends TutorOutputDTO{
    private Double salary;
    private List<SimpleLibrasDTO> libras;
}
