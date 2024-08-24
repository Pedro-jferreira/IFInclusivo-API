package com.example.IfGoiano.IfCoders.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull    @Column(nullable = false)
    private String nome;
    @NotNull    @Column(nullable = false)
    private String login;
    @NotNull    @Column(nullable = false)
    private String senha;
    @NotNull    @Column(nullable = false)
    private Long matricula;
    private String biografia;


    @OneToOne
    private ConfigAcessibilidadeEntity configAcessibilidadeEntity;



    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ComentarioEntity> comentarios = new ArrayList<>();



    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<PublicacaoEntity> publicacaoEntities = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ConfigAcessibilidadeEntity> config = new ArrayList<>();


    public UsuarioEntity() {    }

    public UsuarioEntity(String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity) {
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

    public ConfigAcessibilidadeEntity getConfigAcessibilidade() {
        return configAcessibilidadeEntity;
    }

    public void setConfigAcessibilidade(ConfigAcessibilidadeEntity configAcessibilidadeEntity) {
        this.configAcessibilidadeEntity = configAcessibilidadeEntity;
    }


    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }


    public List<PublicacaoEntity> getPublicacaos() {
        return publicacaoEntities;
    }

    public void setPublicacaos(List<PublicacaoEntity> publicacaoEntities) {
        this.publicacaoEntities = publicacaoEntities;
    }

    public List<ConfigAcessibilidadeEntity> getConfig() {
        return config;
    }

    public void setConfig(List<ConfigAcessibilidadeEntity> config) {
        this.config = config;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity usuario = (UsuarioEntity) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha) && Objects.equals(matricula, usuario.matricula) && Objects.equals(biografia, usuario.biografia) && Objects.equals(configAcessibilidadeEntity, usuario.configAcessibilidadeEntity)  && Objects.equals(comentarios, usuario.comentarios)  && Objects.equals(publicacaoEntities, usuario.publicacaoEntities) && Objects.equals(config, usuario.config);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, login, senha, matricula, biografia, configAcessibilidadeEntity,comentarios, publicacaoEntities, config);
    }
}
