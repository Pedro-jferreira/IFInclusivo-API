package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.TemaCSS;


public class ConfigAcblOutputDTO {
    private Long id;
    private String audicao;
    private TemaCSS tema;
    private String zoom;
    private SimpleUsuarioDTO usuario;

    public ConfigAcblOutputDTO(Long id, String audicao, TemaCSS tema, String zoom, SimpleUsuarioDTO usuario) {
        this.id = id;
        this.audicao = audicao;
        this.tema = tema;
        this.zoom = zoom;
        this.usuario = usuario;
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

    public SimpleUsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(SimpleUsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
