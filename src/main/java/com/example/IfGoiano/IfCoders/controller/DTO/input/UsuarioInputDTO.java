package com.example.IfGoiano.IfCoders.controller.DTO.input;

import com.example.IfGoiano.IfCoders.entity.ConfigAcessibilidadeEntity;
import java.util.Objects;

public class UsuarioInputDTO {
    private String nome;
    private String login;
    private String senha;
    private Long matricula;
    private String biografia;
    private ConfigAcessibilidadeEntity configAcessibilidadeEntity;


    public UsuarioInputDTO() {    }

    public UsuarioInputDTO(String nome, String login, String senha, Long matricula, String biografia,
                           ConfigAcessibilidadeEntity configAcessibilidadeEntity) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.matricula = matricula;
        this.biografia = biografia;
        this.configAcessibilidadeEntity = configAcessibilidadeEntity;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioInputDTO that = (UsuarioInputDTO) o;
        return Objects.equals(nome, that.nome) && Objects.equals(login, that.login) &&
                Objects.equals(senha, that.senha) && Objects.equals(matricula, that.matricula) &&
                Objects.equals(biografia, that.biografia) &&
                Objects.equals(configAcessibilidadeEntity, that.configAcessibilidadeEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, login, senha, matricula, biografia, configAcessibilidadeEntity);
    }
}
