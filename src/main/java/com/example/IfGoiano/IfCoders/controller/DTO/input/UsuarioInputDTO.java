package com.example.IfGoiano.IfCoders.controller.DTO.input;

import java.util.Objects;

public class UsuarioInputDTO {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private Long matricula;
    private String biografia;
    private ConfigAcblInputDTO configAcessibilidadeEntity;



    public UsuarioInputDTO() {    }

    public UsuarioInputDTO(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcblInputDTO configAcessibilidadeEntity) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.matricula = matricula;
        this.biografia = biografia;
        this.configAcessibilidadeEntity = configAcessibilidadeEntity;

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

    public ConfigAcblInputDTO getConfigAcessibilidadeEntity() {
        return configAcessibilidadeEntity;
    }

    public void setConfigAcessibilidadeEntity(ConfigAcblInputDTO configAcessibilidadeEntity) {
        this.configAcessibilidadeEntity = configAcessibilidadeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioInputDTO that = (UsuarioInputDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(login, that.login) && Objects.equals(senha, that.senha) && Objects.equals(matricula, that.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, login, senha, matricula);
    }
}
