package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.Enums.TemaCSS;

import java.util.Objects;

public class ConfigAcblInputDTO {
    private Long id;
    private String audicao;
    private TemaCSS tema;
    private String zoom;

    public ConfigAcblInputDTO() {}

    public ConfigAcblInputDTO(Long id, String audicao, TemaCSS tema, String zoom) {
        this.id = id;
        this.audicao = audicao;
        this.tema = tema;
        this.zoom = zoom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAudicao() {
        return audicao;
    }

    public void setAudicao(String audicao) {
        this.audicao = audicao;
    }

    public TemaCSS getTema() {
        return tema;
    }

    public void setTema(TemaCSS tema) {
        this.tema = tema;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigAcblInputDTO that = (ConfigAcblInputDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
