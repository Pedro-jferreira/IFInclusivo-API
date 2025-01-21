package com.example.IfGoiano.IfCoders.mapper.mocks;

import com.example.IfGoiano.IfCoders.controller.DTO.input.ConfigAcblInputDTO;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.Enums.TemaCSS;

public class MockConfigAcess {


    public ConfigAcessibilidadeEntity mockConfigAces(){
        return mockConfigAces(0L);
    }

    public ConfigAcessibilidadeEntity mockConfigAces(Long number){
        ConfigAcessibilidadeEntity configAcessibilidadeEntity = new ConfigAcessibilidadeEntity();
        configAcessibilidadeEntity.setId(number);
        configAcessibilidadeEntity.setTema(TemaCSS.TEMA1);
        configAcessibilidadeEntity.setZoom("First Zoom"+ number);
        configAcessibilidadeEntity.setAudicao("First Audicao"+ number);
        return configAcessibilidadeEntity;
    }

    public ConfigAcblInputDTO mockConfigAcDTO(Long number){
        ConfigAcblInputDTO configAcblInputDTO = new ConfigAcblInputDTO();
        configAcblInputDTO.setTema(TemaCSS.TEMA1);
        configAcblInputDTO.setZoom("First Zoom"+ number);
        configAcblInputDTO.setAudicao("First Audicao"+ number);
        return configAcblInputDTO;

    }
}
