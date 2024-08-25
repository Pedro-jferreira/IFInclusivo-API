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

    @ManyToMany
    @JoinTable(
            name = "usuario_likes_publicacao",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns =@JoinColumn(name = "publicacao_id") )
    private List<PublicacaoEntity> likes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "usuario_useful_comentario",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "comentario_id")
    )
    private List<ComentarioEntity> useful = new ArrayList<>();

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id, String nome, String login, String senha, Long matricula, String biografia, ConfigAcessibilidadeEntity configAcessibilidadeEntity, List<ComentarioEntity> comentarios, List<PublicacaoEntity> publicacaoEntities, List<PublicacaoEntity> likes, List<ComentarioEntity> useful) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.matricula = matricula;
        this.biografia = biografia;
        this.configAcessibilidadeEntity = configAcessibilidadeEntity;
        this.comentarios = comentarios;
        this.publicacaoEntities = publicacaoEntities;
        this.likes = likes;
        this.useful = useful;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public @NotNull String getLogin() {
        return login;
    }

    public void setLogin(@NotNull String login) {
        this.login = login;
    }

    public @NotNull String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull String senha) {
        this.senha = senha;
    }

    public @NotNull Long getMatricula() {
        return matricula;
    }

    public void setMatricula(@NotNull Long matricula) {
        this.matricula = matricula;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public ConfigAcessibilidadeEntity getConfigAcessibilidadeEntity() {
        return configAcessibilidadeEntity;
    }

    public void setConfigAcessibilidadeEntity(ConfigAcessibilidadeEntity configAcessibilidadeEntity) {
        this.configAcessibilidadeEntity = configAcessibilidadeEntity;
    }

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    public List<PublicacaoEntity> getPublicacaoEntities() {
        return publicacaoEntities;
    }

    public void setPublicacaoEntities(List<PublicacaoEntity> publicacaoEntities) {
        this.publicacaoEntities = publicacaoEntities;
    }


    public List<PublicacaoEntity> getLikes() {
        return likes;
    }

    public void setLikes(List<PublicacaoEntity> likes) {
        this.likes = likes;
    }

    public List<ComentarioEntity> getUseful() {
        return useful;
    }

    public void setUseful(List<ComentarioEntity> useful) {
        this.useful = useful;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) &&
                Objects.equals(login, that.login) && Objects.equals(senha, that.senha) &&
                Objects.equals(matricula, that.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, login, senha, matricula);
    }
}