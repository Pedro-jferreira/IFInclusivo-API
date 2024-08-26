package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleInterpreteDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimpleUsuarioDTO;
import com.example.IfGoiano.IfCoders.entity.Enums.Status;

import java.util.List;

public class LibrasOutputDTO {
    private Long id;
    private String palavra;
    private String descricao;
    private String url;
    private String video;
    private String foto;
    private String justificativa;
    private Status status;
    private SimpleUsuarioDTO sugeriu;
    private List<SimpleInterpreteDTO> interpreteAnalise;
    public LibrasOutputDTO() {

    }

    public LibrasOutputDTO(Long id, String palavra, String descricao, String url, String video, String foto, String justificativa, Status status, SimpleUsuarioDTO sugeriu, List<SimpleInterpreteDTO> interpreteAnalise) {
        this.id = id;
        this.palavra = palavra;
        this.descricao = descricao;
        this.url = url;
        this.video = video;
        this.foto = foto;
        this.justificativa = justificativa;
        this.status = status;
        this.sugeriu = sugeriu;
        this.interpreteAnalise = interpreteAnalise;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SimpleUsuarioDTO getSugeriu() {
        return sugeriu;
    }

    public void setSugeriu(SimpleUsuarioDTO sugeriu) {
        this.sugeriu = sugeriu;
    }

    public List<SimpleInterpreteDTO> getInterpreteAnalise() {
        return interpreteAnalise;
    }

    public void setInterpreteAnalise(List<SimpleInterpreteDTO> interpreteAnalise) {
        this.interpreteAnalise = interpreteAnalise;
    }
}
