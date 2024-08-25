package com.example.IfGoiano.IfCoders.entity;


import com.example.IfGoiano.IfCoders.entity.Enums.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "libras")
public class LibrasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String palavra;
    private String descricao;
    private String url;
    private String video;
    private String foto;
    private String justificativa;
    private Status status;
    //private Usuario usuarioSugere;
    @ManyToMany
    private List<InterpreteEntity> interpreteAnalise = new ArrayList<>();

    public LibrasEntity() {}

    public LibrasEntity(Long id, String palavra, String descricao, String url, String video, String foto, String justificativa, Status status) {
        this.id = id;
        this.palavra = palavra;
        this.descricao = descricao;
        this.url = url;
        this.video = video;
        this.foto = foto;
        this.justificativa = justificativa;
        this.status = status;
    }

    public String getPalavra() {
        return palavra;
    }


    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<InterpreteEntity> getInterpreteAnalise() {
        return interpreteAnalise;
    }

    public void setInterpreteAnalise(List<InterpreteEntity> interpreteAnalise) {
        this.interpreteAnalise = interpreteAnalise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }


}
