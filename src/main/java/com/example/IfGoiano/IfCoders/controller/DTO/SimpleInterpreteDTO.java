package com.example.IfGoiano.IfCoders.controller.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleInterpreteDTO extends SimpleTutorDTO {
    private Double salary;

}
