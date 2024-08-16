package com.example.IfGoiano.IfCoders.model;

import com.example.IfGoiano.IfCoders.model.Enums.TemaCSS;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ConfigAcessibilidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String audicao;
    private TemaCSS tema;
    private String zoom;

    @ManyToOne
    @JoinColumn(name = "configAcessibilidade_id")
    private Usuario usuario;

    public ConfigAcessibilidade(){

    }

    public ConfigAcessibilidade(String audicao, TemaCSS tema, String zoom) {
        this.audicao = audicao;
        this.tema = tema;
        this.zoom = zoom;
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ConfigAcessibilidade that = (ConfigAcessibilidade) o;
        return Objects.equals(id, that.id) && Objects.equals(audicao, that.audicao) && tema == that.tema && Objects.equals(zoom, that.zoom) && Objects.equals(usuario, that.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, audicao, tema, zoom, usuario);
    }
}
