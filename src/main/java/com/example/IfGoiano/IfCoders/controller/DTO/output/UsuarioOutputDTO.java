package com.example.IfGoiano.IfCoders.controller.DTO.output;

import com.example.IfGoiano.IfCoders.controller.DTO.SimpleComentarioDTO;
import com.example.IfGoiano.IfCoders.controller.DTO.SimplePublicacaoDTO;
import com.example.IfGoiano.IfCoders.entity.ComentarioEntity;
import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import com.example.IfGoiano.IfCoders.entity.PublicacaoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioOutputDTO {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private Long matricula;
    private String biografia;



    private ConfigAcessibilidadeEntity configAcessibilidadeEntity;

    private List<SimpleComentarioDTO> comentarios = new ArrayList<>();

    private List<SimplePublicacaoDTO> publicacaoEntities = new ArrayList<>();

    private List<ConfigAcessibilidadeEntity> config = new ArrayList<>();

    public UsuarioOutputDTO() {    }

    public UsuarioOutputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<SimpleComentarioDTO> comentarios, List<SimplePublicacaoDTO> publicacaoEntities, List<ConfigAcessibilidadeEntity> config) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.matricula = matricula;
        this.biografia = biografia;
        this.configAcessibilidadeEntity = configAcessibilidadeEntity;
        this.comentarios = comentarios;
        this.publicacaoEntities = publicacaoEntities;
        this.config = config;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public ConfigAcessibilidadeEntity getConfigAcessibilidade() {
        return configAcessibilidadeEntity;
    }

    public void setConfigAcessibilidade(ConfigAcessibilidadeEntity configAcessibilidadeEntity) {
        this.configAcessibilidadeEntity = configAcessibilidadeEntity;
    }

    public List<SimpleComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<SimpleComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public List<SimplePublicacaoDTO> getPublicacaos() {
        return publicacaoEntities;
    }

    public void setPublicacaos(List<SimplePublicacaoDTO> publicacaoEntities) {
        this.publicacaoEntities = publicacaoEntities;
    }

    public List<ConfigAcessibilidadeEntity> getConfig() {
        return config;
    }

    public void setConfig(List<ConfigAcessibilidadeEntity> config) {
        this.config = config;
    }

    public ConfigAcessibilidadeEntity getConfigAcessibilidadeEntity() {
        return configAcessibilidadeEntity;
    }

    public void setConfigAcessibilidadeEntity(ConfigAcessibilidadeEntity configAcessibilidadeEntity) {
        this.configAcessibilidadeEntity = configAcessibilidadeEntity;
    }

    public List<SimplePublicacaoDTO> getPublicacaoEntities() {
        return publicacaoEntities;
    }

    public void setPublicacaoEntities(List<SimplePublicacaoDTO> publicacaoEntities) {
        this.publicacaoEntities = publicacaoEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioOutputDTO that = (UsuarioOutputDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(login, that.login) &&
                Objects.equals(senha, that.senha) && Objects.equals(matricula, that.matricula) &&
                Objects.equals(biografia, that.biografia) &&
                Objects.equals(configAcessibilidadeEntity, that.configAcessibilidadeEntity) &&
                Objects.equals(comentarios, that.comentarios) &&
                Objects.equals(publicacaoEntities, that.publicacaoEntities) && Objects.equals(config, that.config);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity, comentarios, publicacaoEntities, config);
    }
}
